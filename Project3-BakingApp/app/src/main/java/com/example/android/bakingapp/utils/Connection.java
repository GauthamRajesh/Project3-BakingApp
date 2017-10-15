package com.example.android.bakingapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;

public class Connection {
    public static boolean checkConnection(Context c) {
        return ((ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo() != null;
    }
}
