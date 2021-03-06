package com.tanya.dvtweatherapp.ui.main.today;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.tanya.dvtweatherapp.models.CurrentWeather;
import com.tanya.dvtweatherapp.network.WeatherApi;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TodayViewModel extends ViewModel {

    private static final String TAG = "TodayViewModel";

    private final WeatherApi weatherApi;


    @Inject
    public TodayViewModel(WeatherApi weatherApi) {
        this.weatherApi = weatherApi;
    }

}
