package com.subhan_nadeem.ignite.posts;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.subhan_nadeem.ignite.R;
import com.subhan_nadeem.ignite.databinding.CardPostBinding;
import com.subhan_nadeem.ignite.models.Post;

import java.util.List;

/**
 * Created by Subhan Nadeem on 2017-09-09.
 * RecyclerView adapter for
 */

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {
    private final PostClickCallback mCallback;
    private List<Post> mPosts;

    public void setPosts(final List<Post> posts) {
            mPosts = posts;
            notifyItemRangeInserted(0, posts.size());
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public PostsAdapter(PostClickCallback callback) {
        mCallback = callback;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public PostsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {

        CardPostBinding binding =  DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.card_post,
                        parent, false);

        binding.setCallback(mCallback);

        return new ViewHolder(binding);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mBinding.setPost(mPosts.get(position));
        holder.mBinding.executePendingBindings();
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mPosts.size();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    static class ViewHolder extends RecyclerView.ViewHolder {

        private final CardPostBinding mBinding;

        ViewHolder(CardPostBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }
}
