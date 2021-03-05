package com.tanya.dvtweatherapp.ui.main.today;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.tanya.dvtweatherapp.network.WeatherApi;

import javax.inject.Inject;

public class TodayViewModel extends ViewModel {

    private final WeatherApi weatherApi;

    private final String msg;

    @Inject
    public TodayViewModel(WeatherApi weatherApi, String msg) {
        this.weatherApi = weatherApi;

        if (this.weatherApi != null) {
            this.msg = msg;
        } else {
            this.msg = "Weather API is null";
        }
    }

    public String getMessage() {
        return msg;
    }

}
