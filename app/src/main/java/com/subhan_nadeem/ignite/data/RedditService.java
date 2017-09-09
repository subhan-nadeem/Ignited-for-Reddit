package com.subhan_nadeem.ignite.data;

import com.subhan_nadeem.ignite.models.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Subhan Nadeem on 2017-06-28.
 */

public interface RedditService {
    @GET("/r/{subreddit}/hot/.json")
    Call<List<Post>> getPosts(@Path("subreddit") String subreddit);
}
