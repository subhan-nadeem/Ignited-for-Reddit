package com.subhan_nadeem.ignite.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.concurrent.TimeUnit;

/**
 * Created by Subhan Nadeem on 2017-06-28.
 * Data model for reddit posts
 */

public class Post {
    @SerializedName("domain")
    @Expose
    private String domain;

    @SerializedName("subreddit")
    @Expose
    private String subreddit;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("score")
    @Expose
    private String score;


    @SerializedName("created_utc")
    @Expose
    private long createdUTC;

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getSubreddit() {
        return subreddit;
    }

    public void setSubreddit(String subreddit) {
        this.subreddit = subreddit;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }


    public String getTimeSincePosted() {
        long currentTime = System.currentTimeMillis();

        long timeSincePostedMillis = currentTime - createdUTC;

        return TimeUnit.MILLISECONDS.toMinutes(timeSincePostedMillis) + " min";
    }
}
