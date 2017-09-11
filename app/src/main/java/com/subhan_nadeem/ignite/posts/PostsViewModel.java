package com.subhan_nadeem.ignite.posts;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.subhan_nadeem.ignite.data.APIUtils;
import com.subhan_nadeem.ignite.data.RedditService;
import com.subhan_nadeem.ignite.models.Post;

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
    private final LiveData<List<Post>> mObservablePosts;
    private String TAG = "PostsViewModel";

    public PostsViewModel(Application application) {
        super(application);

        mRedditService = APIUtils.getRedditService();

        mObservablePosts = new RedditPostsLiveData();
    }

    public LiveData<List<Post>> getPosts() {
        return mObservablePosts;
    }

    private class RedditPostsLiveData extends LiveData<List<Post>> {

        RedditPostsLiveData() {
            loadData();
        }

        private void loadData() {
            mRedditService.getPosts("nba").enqueue(new Callback<JsonObject>() {
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

                    setValue(posts);
                }

                @Override
                public void onFailure(@NonNull Call<JsonObject> call, @NonNull Throwable t) {

                }
            });
        }
    }
}
