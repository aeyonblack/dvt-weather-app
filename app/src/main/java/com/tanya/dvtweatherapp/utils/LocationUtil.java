package com.tanya.dvtweatherapp.utils;

import android.content.Context;
import android.location.LocationManager;

public class LocationUtil {

    public static boolean isLocationEnabled(Context context) {
        LocationManager manager = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
        return manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

}
