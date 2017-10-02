package com.subhan_nadeem.ignite.network;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Subhan Nadeem on 2017-06-28.
 * Reddit API Retrofit Interface
 */

public interface RedditService {
    @GET("/r/{subreddit}/hot/.json")
    Call<JsonObject> getPosts(@Path("subreddit") String subreddit);

    @GET("/subreddits/search/.json")
    Call<JsonObject> searchSubreddits(@Query(value = "q") String subredditString);
}
