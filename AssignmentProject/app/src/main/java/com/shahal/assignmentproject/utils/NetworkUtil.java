package com.shahal.assignmentproject.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by shahal on 23-04-2018.
 */

public class NetworkUtil {

    public static boolean isNetworkConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = null;
        if (cm != null) {
            netInfo = cm.getActiveNetworkInfo();
        }
        //should check null because in airplane mode it will be null
        return (netInfo != null && netInfo.isAvailable() && netInfo.isConnected());
    }
}
