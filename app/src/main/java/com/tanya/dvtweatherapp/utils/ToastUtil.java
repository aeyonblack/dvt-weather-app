package com.tanya.dvtweatherapp.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Handle displaying toast messages
 */
public class ToastUtil {
    public static void toast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
