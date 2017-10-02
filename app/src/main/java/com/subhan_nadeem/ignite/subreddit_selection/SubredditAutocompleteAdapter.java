package com.subhan_nadeem.ignite.subreddit_selection;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.subhan_nadeem.ignite.network.APIUtils;
import com.subhan_nadeem.ignite.network.RedditService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

/**
 * Created by Subhan Nadeem on 2017-09-13.
 * Adapter for the Subreddit autocomplete
 */

class SubredditAutocompleteAdapter extends BaseAdapter implements Filterable {

    private static final int MAX_RESULTS = 5;
    private static final String TAG = "SubredditAutocomplete";
    private Context mContext;
    private List<String> mResults = new ArrayList<>();

    SubredditAutocompleteAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return mResults.size();
    }

    @Override
    public String getItem(int index) {
        return mResults.get(index);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {

            // Inflate TextView for search result
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
        }
        ((TextView) convertView.findViewById(android.R.id.text1)).setText(getItem(position));
        return convertView;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if (constraint != null) {
                    List<String> subreddits = searchSubreddits(constraint.toString());

                    // Assign the data to the FilterResults
                    filterResults.values = subreddits;
                    filterResults.count = subreddits.size();
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results != null && results.count > 0) {
                    mResults = (List<String>) results.values;
                    notifyDataSetChanged();
                } else {
                    notifyDataSetInvalidated();
                }
            }
        };
    }

    /**
     * Returns a search result for the given book title.
     */
    private List<String> searchSubreddits(String subredditString) {

        List<String> foundSubreddits = new ArrayList<>();

        subredditString = subredditString + "*"; // Append * symbol for regex

        RedditService redditService = APIUtils.getRedditService();

        try {
            Call<JsonObject> searchCall = redditService.searchSubreddits(subredditString);
            JsonObject response = searchCall.execute().body();

            Log.d(TAG, "Search response with subreddit string " + subredditString
                    + " and url " + searchCall.request().url().toString()  + ": \n" + response);
            if (response != null) {
                JsonArray searchArray =
                        response.getAsJsonObject("data").getAsJsonArray("children");

                for (int i = 0; i < searchArray.size(); ++i) {
                    String subredditName = searchArray.get(i).getAsJsonObject()
                            .get("data").getAsJsonObject()
                            .get("display_name").getAsString();
                    foundSubreddits.add(subredditName);
                }
            }

        } catch (IOException e) {
            Log.e(TAG, "Subreddit search with " + subredditString + " failed, exception: "
                    + e.toString());
        }

        return foundSubreddits;
    }
}
