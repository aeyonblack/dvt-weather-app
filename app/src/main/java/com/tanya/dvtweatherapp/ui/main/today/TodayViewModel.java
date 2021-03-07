package com.tanya.dvtweatherapp.ui.main.today;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.tanya.dvtweatherapp.models.CurrentWeather;
import com.tanya.dvtweatherapp.network.WeatherApi;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class TodayViewModel extends ViewModel {

    private final WeatherApi weatherApi;

    private final MediatorLiveData<CurrentWeather> currentWeather = new MediatorLiveData<>();

    @Inject
    public TodayViewModel(WeatherApi weatherApi) {
        this.weatherApi = weatherApi;
    }

    public void getCurrentWeather(String name) {
        final LiveData<CurrentWeather> source = LiveDataReactiveStreams.fromPublisher(
                weatherApi.getCurrentWeather(name)
                .subscribeOn(Schedulers.io())
        );

        currentWeather.addSource(source, currentWeather -> {
            this.currentWeather.setValue(currentWeather);
            this.currentWeather.removeSource(source);
        });
    }

    public LiveData<CurrentWeather> observeCurrentWeather() {
        return currentWeather;
    }

}
