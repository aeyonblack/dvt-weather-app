package com.tanya.dvtweatherapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Checks internet connectivity
 */
public class NetworkUtil {

    /**
     * Does the device have an internet connection
     * @param context - context
     * @return true if device has internet connection
     */
    public static boolean isConnected(Context context) {
        ConnectivityManager manager = (ConnectivityManager)context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        return manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() ==
                NetworkInfo.State.CONNECTED || manager
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                .getState() == NetworkInfo.State.CONNECTED;
    }

}
