package com.subhan_nadeem.ignite.storage;

import com.subhan_nadeem.ignite.models.SubredditAddition;

/**
 * Created by Subhan Nadeem on 2017-10-02.
 */

public interface SubredditFoundCallback {
    public void onSubredditFound(SubredditAddition subredditAddition);
}
