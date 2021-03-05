package com.tanya.dvtweatherapp.ui.main.today;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

public class TodayViewModel extends ViewModel {

    private static final String TAG = "TodayViewModel";

    @Inject
    public TodayViewModel() {
        Log.d(TAG, "TodayViewModel: ViewModel is working");
    }

}
