package com.tanya.dvtweatherapp.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.tanya.dvtweatherapp.data.local.dao.WeatherDao;
import com.tanya.dvtweatherapp.data.remote.WeatherApi;
import com.tanya.dvtweatherapp.models.CurrentWeather;
import com.tanya.dvtweatherapp.network.ApiResponse;
import com.tanya.dvtweatherapp.utils.AppExecutors;

import javax.inject.Inject;

public class WeatherRepository {

    private final WeatherDao weatherDao;
    private final WeatherApi weatherApi;

    private String msg;

    // Check if there's an internet connection
    private final boolean isConnected;

    @Inject
    public WeatherRepository(WeatherDao weatherDao, WeatherApi weatherApi, boolean isConnected) {
        this.weatherDao = weatherDao;
        this.weatherApi = weatherApi;
        this.isConnected = isConnected;

        if (isConnected)
            msg = "Internet connection available ";
        else
            msg = "No internet connection ";
    }

    public LiveData<Resource<CurrentWeather>> getCurrentWeather(int id) {
        return new NetworkBoundResource<CurrentWeather, CurrentWeather>(AppExecutors.getInstance()) {
            @Override
            protected void saveCallResult(@NonNull CurrentWeather item) {
                if (item != null) {
                    // TODO: Set timestamp here
                    msg += " [saving call result]: " + item.getName();
                    weatherDao.saveCurrentWeather(item);
                }
            }

            @Override
            protected boolean shouldFetch(@Nullable CurrentWeather currentWeather) {
                msg += "[should fetch]:" + isConnected;
                return isConnected;
            }

            @NonNull
            @Override
            protected LiveData<CurrentWeather> loadFromDb() {
                msg += "[loadFromDb]: ";
                return weatherDao.loadCurrentWeather(id);
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<CurrentWeather>> createCall() {
                msg += "[createCall]:";
                return weatherApi.getCurrentWeather(id);
            }
        }
        .getAsLiveData();
    }

    public String getMsg() {
        return msg;
    }

}
