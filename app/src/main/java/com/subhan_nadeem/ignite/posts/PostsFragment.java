package com.subhan_nadeem.ignite.posts;

import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.subhan_nadeem.ignite.R;
import com.subhan_nadeem.ignite.databinding.FragmentPostsBinding;
import com.subhan_nadeem.ignite.models.Post;
import com.subhan_nadeem.ignite.models.SubredditAddition;
import com.subhan_nadeem.ignite.storage.SubredditFoundCallback;
import com.subhan_nadeem.ignite.storage.SubredditRepository;

import java.util.List;

/**
 * Created by Subhan Nadeem on 2017-06-14.
 * Fragment that handles
 */

public class PostsFragment extends LifecycleFragment implements PostClickCallback {

    private static final String KEY_SUBREDDIT_NAME = "SUBREDDITNAME";
    private FragmentPostsBinding mBinding;
    private SubredditAddition mSubredditAddition;
    private PostsAdapter mPostsAdapter;

    public static PostsFragment newInstance(String subredditName) {

        Bundle args = new Bundle();

        PostsFragment fragment = new PostsFragment();
        args.putString(KEY_SUBREDDIT_NAME, subredditName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SubredditRepository repository = new SubredditRepository();

        String subredditName = getArguments().getString(KEY_SUBREDDIT_NAME);
        repository.getSubredditByName(subredditName, new SubredditFoundCallback() {
            @Override
            public void onSubredditFound(SubredditAddition subredditAddition) {
                final PostsViewModel viewModel =
                        ViewModelProviders.of(PostsFragment.this).get(PostsViewModel.class);

                viewModel.setSubreddit(subredditAddition);
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final PostsViewModel viewModel =
                ViewModelProviders.of(this).get(PostsViewModel.class);

        subScribeUI(viewModel);
    }


    /**
     * Subscribe the ViewModel to the posts UI
     */
    private void subScribeUI(PostsViewModel viewModel) {
        viewModel.getPosts().observe(this, new Observer<List<Post>>() {
            @Override
            public void onChanged(@Nullable List<Post> posts) {
                if (posts != null) {
                    mPostsAdapter.setPosts(posts);
                }
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        mBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_posts, container, false);

        mPostsAdapter = new PostsAdapter(this);
        mBinding.postsRecyclerView.setAdapter(mPostsAdapter);

        return mBinding.getRoot();
    }

    @Override
    public void onClick(Post post) {

    }
}
