package com.subhan_nadeem.ignite.posts;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.subhan_nadeem.ignite.models.Post;
import com.subhan_nadeem.ignite.models.SubredditAddition;
import com.subhan_nadeem.ignite.network.APIUtils;
import com.subhan_nadeem.ignite.network.RedditService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Subhan Nadeem on 2017-06-14.
 * ViewModel for Posts list
 */

public class PostsViewModel extends AndroidViewModel {


    private final RedditService mRedditService;
    private final MutableLiveData<List<Post>> mObservablePosts;
    private String TAG = "PostsViewModel";
    private SubredditAddition mSubreddit;

    public PostsViewModel(Application application) {
        super(application);

        mRedditService = APIUtils.getRedditService();

        mObservablePosts = new MutableLiveData<>();
    }

    private void loadPosts(String subredditName) {
        mRedditService.getPosts(subredditName).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(@NonNull Call<JsonObject> call, @
                    NonNull Response<JsonObject> response) {

                // Retrieve appropriate JsonArray that holds all posts
                JsonArray postsArray =
                        response.body().getAsJsonObject("data").getAsJsonArray("children");

                List<Post> posts = new ArrayList<>();

                Gson gson = new Gson();
                for (int i = 0; i < postsArray.size(); ++i) {
                    JsonObject postObject = postsArray.get(i).getAsJsonObject()
                            .getAsJsonObject("data");

                    // Convert each post to POJO
                    posts.add(gson.fromJson(postObject, Post.class));
                }

                mObservablePosts.setValue(posts);
            }

            @Override
            public void onFailure(@NonNull Call<JsonObject> call, @NonNull Throwable t) {
// TODO implement failure case
            }
        });
    }

    LiveData<List<Post>> getPosts() {
        return mObservablePosts;
    }


    public void setSubreddit(SubredditAddition subreddit) {
        mSubreddit = subreddit;
        loadPosts(mSubreddit.getSubredditName());
    }
}
