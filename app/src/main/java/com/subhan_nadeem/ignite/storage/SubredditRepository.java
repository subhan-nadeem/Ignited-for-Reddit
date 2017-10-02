package com.subhan_nadeem.ignite.storage;

import android.support.annotation.NonNull;

import com.subhan_nadeem.ignite.models.SubredditAddition;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Subhan Nadeem on 2017-09-30.
 * Realm repo handling storage of subreddits
 */

public class SubredditRepository {

    public void addSubreddit(final SubredditAddition subredditAddition) {
        Realm realm = null;
        try { // I could use try-with-resources here
            realm = Realm.getDefaultInstance();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(@NonNull Realm realm) {
                    realm.insertOrUpdate(subredditAddition);
                }
            });
        } finally {
            if (realm != null) {
                realm.close();
            }
        }
    }

    public void deleteSubredditByName(final String name) {
        Realm realm = null;
        try { // I could use try-with-resources here
            realm = Realm.getDefaultInstance();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(@NonNull Realm realm) {
                    realm.where(SubredditAddition.class).equalTo("subredditName", name).findFirstAsync().deleteFromRealm();
                }
            });
        } finally {
            if (realm != null) {
                realm.close();
            }
        }
    }

    public void getSubredditByName(final String subreddit,
                                   @NonNull final SubredditFoundCallback callback) {
        Realm realm = null;
        try { // I could use try-with-resources here
            realm = Realm.getDefaultInstance();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    SubredditAddition subredditAddition =
                            realm.where(SubredditAddition.class)
                                    .equalTo("mSubredditName", subreddit)
                                    .findFirstAsync();
                    callback.onSubredditFound(subredditAddition);
                }
            });
        } finally {
            if (realm != null) {
                realm.close();
            }
        }
    }

    public void getAllSubreddits(@NonNull final AllSubredditsFoundCallback callback) {
        Realm realm = null;
        try { // I could use try-with-resources here
            realm = Realm.getDefaultInstance();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    RealmResults results = realm.where(SubredditAddition.class).findAll();
                    callback.onAllSubredditsFound(results);
                }
            });
        } finally {
            if (realm != null) {
                realm.close();
            }
        }
    }
}
