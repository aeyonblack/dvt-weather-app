package com.tanya.dvtweatherapp.ui.main.locations;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tanya.dvtweatherapp.data.WeatherRepository;
import com.tanya.dvtweatherapp.models.FavouriteLocation;

import java.util.List;

import javax.inject.Inject;

public class LocationsViewModel extends ViewModel {

    private final WeatherRepository weatherRepository;

    @Inject
    public LocationsViewModel(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    public LiveData<List<FavouriteLocation>> getFavouriteLocations() {
        return weatherRepository.getFavouriteLocations();
    }

}
