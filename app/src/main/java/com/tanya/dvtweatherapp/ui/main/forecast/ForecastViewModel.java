package com.tanya.dvtweatherapp.ui.main.forecast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import com.tanya.dvtweatherapp.data.WeatherRepository;
import com.tanya.dvtweatherapp.models.Forecast;
import com.tanya.dvtweatherapp.data.remote.WeatherApi;
import com.tanya.dvtweatherapp.data.Resource;

import javax.inject.Inject;

import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Performs query for the 5 day forecast weather data
 */
public class ForecastViewModel extends ViewModel {

    private final WeatherRepository weatherRepository;

    @Inject
    public ForecastViewModel(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    public LiveData<Resource<Forecast>> getWeatherForecast(int id, boolean isConnected) {
        return weatherRepository.getWeatherForecast(id, isConnected);
    }

    public LiveData<Resource<Forecast>> getWeatherForecast(String cityName, boolean connected) {
        return weatherRepository.getWeatherForecast(cityName, connected);
    }

}
