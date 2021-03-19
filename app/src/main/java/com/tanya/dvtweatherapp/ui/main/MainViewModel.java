package com.tanya.dvtweatherapp.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

public class MainViewModel extends ViewModel {

    private final MutableLiveData<String> searchQuery = new MutableLiveData<>();

    private final String msg;

    @Inject
    public MainViewModel(String msg) {
        // Default constructor
        this.msg = msg;
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery.setValue(searchQuery);
    }

    public LiveData<String> getSearchQuery() {
        return searchQuery;
    }

    public String getMsg() {
        return msg;
    }

}
