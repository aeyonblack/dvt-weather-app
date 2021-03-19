package com.tanya.dvtweatherapp.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

/**
 * Pass data from MainActivity to child fragments
 */
public class MainViewModel extends ViewModel {

    private final MutableLiveData<String> searchQuery = new MutableLiveData<>();

    private final MutableLiveData<double[]> coordinates = new MutableLiveData<>();

    @Inject
    public MainViewModel() {
        // Default constructor
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery.setValue(searchQuery);
    }

    public LiveData<String> getSearchQuery() {
        return searchQuery;
    }

    public void setCoordinates(double[] coordinates) {
        this.coordinates.setValue(coordinates);
    }

    public LiveData<double[]> getCoordinates() {
        return coordinates;
    }

}
