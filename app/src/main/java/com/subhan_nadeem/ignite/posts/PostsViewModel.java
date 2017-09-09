package com.subhan_nadeem.ignite.posts;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.subhan_nadeem.ignite.models.Post;

import java.util.List;

/**
 * Created by Subhan Nadeem on 2017-06-14.
 */

public class PostsViewModel extends AndroidViewModel {


    public LiveData<List<Post>> getPosts() {
        return mObservablePosts;
    }

    private LiveData<List<Post>> mObservablePosts;

    public PostsViewModel(Application application) {
        super(application);

        mObservablePosts = queryPosts();
    }

    private LiveData<List<Post>> queryPosts() {

        return null;
    }
}
