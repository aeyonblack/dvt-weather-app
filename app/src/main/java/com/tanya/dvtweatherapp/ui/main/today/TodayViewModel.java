package com.tanya.dvtweatherapp.ui.main.today;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.tanya.dvtweatherapp.data.Resource;
import com.tanya.dvtweatherapp.data.WeatherRepository;
import com.tanya.dvtweatherapp.models.CurrentWeather;

import javax.inject.Inject;

public class TodayViewModel extends ViewModel {

    private final WeatherRepository weatherRepository;

    @Inject
    public TodayViewModel(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    // Get current weather using city id
    public LiveData<Resource<CurrentWeather>> getCurrentWeather(int id, boolean isConnected) {
        return weatherRepository.getCurrentWeather(id, isConnected);
    }

    // Get current weather using city name
    public LiveData<Resource<CurrentWeather>> getCurrentWeather(String cityName, boolean isConnected) {
        return weatherRepository.getCurrentWeather(cityName, isConnected);
    }

    public void saveWeatherLocation(CurrentWeather currentWeather) {
        weatherRepository.saveWeatherLocation(currentWeather);
    }

}
