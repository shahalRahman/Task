package com.shahal.assignmentproject;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

/**
 * Created by shahal on 24-04-2018.
 */

public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
