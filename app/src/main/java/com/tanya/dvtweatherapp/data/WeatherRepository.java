package com.tanya.dvtweatherapp.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.tanya.dvtweatherapp.data.local.dao.WeatherDao;
import com.tanya.dvtweatherapp.data.remote.WeatherApi;
import com.tanya.dvtweatherapp.models.CurrentWeather;
import com.tanya.dvtweatherapp.models.FavouriteLocation;
import com.tanya.dvtweatherapp.network.ApiResponse;
import com.tanya.dvtweatherapp.utils.AppExecutors;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class WeatherRepository {

    private final WeatherDao weatherDao;
    private final WeatherApi weatherApi;

    private String msg;

    @Inject
    public WeatherRepository(WeatherDao weatherDao, WeatherApi weatherApi) {
        this.weatherDao = weatherDao;
        this.weatherApi = weatherApi;
    }

    public LiveData<Resource<CurrentWeather>> getCurrentWeather(int id, boolean isConnected) {
        msg = isConnected ? "Internet connection available " : "No internet connection ";
        return new NetworkBoundResource<CurrentWeather, CurrentWeather>(AppExecutors.getInstance()) {
            @Override
            protected void saveCallResult(@NonNull CurrentWeather item) {
                // TODO: Set timestamp here
                msg += " [saving call result]: " + item.getName();
                weatherDao.saveCurrentWeather(item);
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

    public void saveWeatherLocation(CurrentWeather currentWeather) {
        FavouriteLocation location = new FavouriteLocation();
        location.setLocationId(currentWeather.getCurrentWeatherId());
        location.setLocationName(currentWeather.getName());
        location.setLat(currentWeather.getCoord().getLat());
        location.setLon(currentWeather.getCoord().getLon());
        weatherDao.saveLocationAsFavourite(location);
    }

    public LiveData<List<FavouriteLocation>> getFavouriteLocations() {
        return weatherDao.loadFavouriteLocations();
    }

    public String getMsg() {
        return msg;
    }

}
