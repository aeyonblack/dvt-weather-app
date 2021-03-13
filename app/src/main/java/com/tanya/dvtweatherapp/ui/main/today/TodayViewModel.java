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

    public LiveData<Resource<CurrentWeather>> getCurrentWeather(int id, boolean isConnected) {
        return weatherRepository.getCurrentWeather(id, isConnected);
    }

    public void saveWeatherLocation(CurrentWeather currentWeather) {
        weatherRepository.saveWeatherLocation(currentWeather);
    }

    //TODO: Remove this method
    public String getMsg() {
        if (weatherRepository != null) {
            return weatherRepository.getMsg();
        }
        else {
            return "Weather repository is null";
        }
    }

}
