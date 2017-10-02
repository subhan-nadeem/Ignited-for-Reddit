package com.subhan_nadeem.ignite;

import io.realm.Realm;

public class App extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
    }
}
