package com.subhan_nadeem.ignite.posts;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.subhan_nadeem.ignite.R;
import com.subhan_nadeem.ignite.databinding.FragmentPostsBinding;

/**
 * Created by Subhan Nadeem on 2017-06-14.
 * Fragment that handles
 */

public class PostsFragment extends Fragment {

    private PostsViewModel mViewModel;
    private FragmentPostsBinding mViewDataBinding;

    public static PostsFragment newInstance() {

        Bundle args = new Bundle();

        PostsFragment fragment = new PostsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        mViewModel.start();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mViewDataBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_posts, container, false);

        mViewDataBinding.setView(this);

        setHasOptionsMenu(true);

        mViewDataBinding.setViewmodel(mViewModel);
        return mViewDataBinding.getRoot();
    }


    public void setViewModel(PostsViewModel viewModel) {
        mViewModel = viewModel;
    }

}
