package com.tanya.dvtweatherapp.utils;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

/**
 * Handle displaying toast messages
 */
public class Util {

    public static void toast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void showSnackbar(View container, int textId, int actionId,
                                    View.OnClickListener listener) {
        Snackbar.make(container, textId, Snackbar.LENGTH_INDEFINITE)
                .setAction(actionId, listener).show();
    }

}
