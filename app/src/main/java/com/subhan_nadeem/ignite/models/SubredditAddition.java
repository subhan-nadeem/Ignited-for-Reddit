package com.subhan_nadeem.ignite.models;

import com.subhan_nadeem.ignite.helpers.RealmObservableArrayList;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Subhan Nadeem on 2017-09-12.
 * <p>
 * Instance class of user's preferred subreddits to ignite
 */

public class SubredditAddition extends RealmObject {

    @PrimaryKey
    private String mSubredditName;

    @Ignore
    private RealmObservableArrayList<Keyword> mKeywords = new RealmObservableArrayList<>();

    public RealmObservableArrayList<Keyword> getKeywords() {
        return mKeywords;
    }

    public String getSubredditName() {
        return mSubredditName;
    }

    public void setSubredditName(String subredditName) {
        mSubredditName = subredditName;
    }

}
