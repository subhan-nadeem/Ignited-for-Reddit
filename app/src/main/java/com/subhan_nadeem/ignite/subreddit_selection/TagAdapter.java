package com.subhan_nadeem.ignite.subreddit_selection;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.subhan_nadeem.ignite.R;
import com.subhan_nadeem.ignite.databinding.ItemTagBinding;
import com.subhan_nadeem.ignite.models.Keyword;

import java.util.Collections;
import java.util.List;

/**
 * Created by Subhan Nadeem on 2017-09-18.
 * RecyclerView adapter for displayed tags of a specific subreddit
 */

class TagAdapter extends RecyclerView.Adapter<TagAdapter.ViewHolder> {

    private List<Keyword> mKeywords = Collections.emptyList();

    public TagAdapter(List<Keyword> keywords) {
        mKeywords = keywords;
    }

    public void setKeywords(List<Keyword> keywords) {
        mKeywords = keywords;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemTagBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.item_tag,
                        parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mBinding.setTag(mKeywords.get(position));
        holder.mBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mKeywords.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final ItemTagBinding mBinding;

        ViewHolder(ItemTagBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }
}
