package com.subhan_nadeem.ignite.helpers;

import android.databinding.ObservableArrayList;

import io.realm.RealmModel;

/**
 * Created by Subhan Nadeem on 2017-09-30.
 * Workaround class to ensure list can be stored in Realm
 */

public class RealmObservableArrayList<T> extends ObservableArrayList implements RealmModel {

}