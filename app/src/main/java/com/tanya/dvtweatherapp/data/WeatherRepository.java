package com.tanya.dvtweatherapp.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.tanya.dvtweatherapp.data.local.dao.WeatherDao;
import com.tanya.dvtweatherapp.data.remote.WeatherApi;
import com.tanya.dvtweatherapp.models.CurrentWeather;
import com.tanya.dvtweatherapp.models.FavouriteLocation;
import com.tanya.dvtweatherapp.models.Forecast;
import com.tanya.dvtweatherapp.network.ApiResponse;
import com.tanya.dvtweatherapp.utils.AppExecutors;

import java.util.List;

import javax.inject.Inject;

/**
 * Implements methods to either fetch data from REST API
 * or fetch from local database
 */
public class WeatherRepository {

    private final WeatherDao weatherDao;
    private final WeatherApi weatherApi;

    /**
     * The weather data access object and weather api are global
     * dependencies and the weather repository simple gets references
     * to their instances by constructor injection
     */
    @Inject
    public WeatherRepository(WeatherDao weatherDao, WeatherApi weatherApi) {
        this.weatherDao = weatherDao;
        this.weatherApi = weatherApi;
    }

    /*QUERY CURRENT WEATHER*/

    // Get current weather using city id
    public LiveData<Resource<CurrentWeather>> getCurrentWeather(int id, boolean isConnected) {
        return new NetworkBoundResource<CurrentWeather, CurrentWeather>(AppExecutors.getInstance()) {
            @Override
            protected void saveCallResult(@NonNull CurrentWeather item) {
                item.setTimeStamp(System.currentTimeMillis());
                weatherDao.saveCurrentWeather(item);
            }

            @Override
            protected boolean shouldFetch(@Nullable CurrentWeather currentWeather) {
                return isConnected;
            }

            @NonNull
            @Override
            protected LiveData<CurrentWeather> loadFromDb() {
                return weatherDao.loadCurrentWeather(id);
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<CurrentWeather>> createCall() {
                return weatherApi.getCurrentWeather(id);
            }
        }
        .getAsLiveData();
    }
    
    // Get current weather using city name
    public LiveData<Resource<CurrentWeather>> getCurrentWeather(String cityName, boolean isConnected) {
        return new NetworkBoundResource<CurrentWeather, CurrentWeather>(AppExecutors.getInstance()) {
            @Override
            protected void saveCallResult(@NonNull CurrentWeather item) {
                item.setTimeStamp(System.currentTimeMillis());
                weatherDao.saveCurrentWeather(item);
            }

            @Override
            protected boolean shouldFetch(@Nullable CurrentWeather currentWeather) {
                return isConnected;
            }

            @NonNull
            @Override
            protected LiveData<CurrentWeather> loadFromDb() {
                return weatherDao.loadCurrentWeather(cityName);
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<CurrentWeather>> createCall() {
                return weatherApi.getCurrentWeather(cityName);
            }
        }
        .getAsLiveData();
    }

    // Get current weather using location coordinates
    public LiveData<Resource<CurrentWeather>> getCurrentWeather(double[] coordinates, boolean isConnected) {
        return new NetworkBoundResource<CurrentWeather, CurrentWeather>(AppExecutors.getInstance()) {
            @Override
            protected void saveCallResult(@NonNull CurrentWeather item) {
                item.setTimeStamp(System.currentTimeMillis());
                weatherDao.saveCurrentWeather(item);
            }

            @Override
            protected boolean shouldFetch(@Nullable CurrentWeather currentWeather) {
                return isConnected;
            }

            @NonNull
            @Override
            protected LiveData<CurrentWeather> loadFromDb() {
                return weatherDao.loadCurrentWeather(coordinates[0], coordinates[1]);
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<CurrentWeather>> createCall() {
                return weatherApi.getCurrentWeather(coordinates[0], coordinates[1]);
            }
        }
        .getAsLiveData();
    }

    /*QUERY FIVE DAY FORECAST*/

    // Get weather forecast using city id
    public LiveData<Resource<Forecast>> getWeatherForecast(int id, boolean isConnected) {
        return new NetworkBoundResource<Forecast, Forecast>(AppExecutors.getInstance()) {
            @Override
            protected void saveCallResult(@NonNull Forecast item) {
                weatherDao.deleteOldForecast(id);
                weatherDao.saveWeatherForecast(item);
            }

            @Override
            protected boolean shouldFetch(@Nullable Forecast forecast) {
                return isConnected;
            }

            @NonNull
            @Override
            protected LiveData<Forecast> loadFromDb() {
                return weatherDao.loadWeatherForecast(id);
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<Forecast>> createCall() {
                return weatherApi.getFiveDayForecast(id);
            }
        }
        .getAsLiveData();
    }

    // Get weather forecast using city name
    public LiveData<Resource<Forecast>> getWeatherForecast(String cityName, boolean isConnected) {
        return new NetworkBoundResource<Forecast, Forecast>(AppExecutors.getInstance()) {

            @Override
            protected void saveCallResult(@NonNull Forecast item) {
                weatherDao.deleteOldForecast(cityName);
                weatherDao.saveWeatherForecast(item);
            }

            @Override
            protected boolean shouldFetch(@Nullable Forecast forecast) {
                return isConnected;
            }

            @NonNull
            @Override
            protected LiveData<Forecast> loadFromDb() {
                return weatherDao.loadWeatherForecast(cityName);
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<Forecast>> createCall() {
                return weatherApi.getFiveDayForecast(cityName);
            }
        }
        .getAsLiveData();
    }

    // Get forecast using location coordinates
    public LiveData<Resource<Forecast>> getWeatherForecast(double[] coordinates, boolean isConnected) {
        return new NetworkBoundResource<Forecast, Forecast>(AppExecutors.getInstance()) {

            @Override
            protected void saveCallResult(@NonNull Forecast item) {
                weatherDao.deleteOldForecast(coordinates[0], coordinates[1]);
                weatherDao.saveWeatherForecast(item);
            }

            @Override
            protected boolean shouldFetch(@Nullable Forecast forecast) {
                return isConnected;
            }

            @NonNull
            @Override
            protected LiveData<Forecast> loadFromDb() {
                return weatherDao.loadWeatherForecast(coordinates[0],coordinates[1]);
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<Forecast>> createCall() {
                return weatherApi.getFiveDayForecast(coordinates[0], coordinates[1]);
            }
        }
        .getAsLiveData();
    }

    /**
     * Saves a weather location as favourite
     * Should use RxJava to remove database queries off the main thread
     */
    public void saveWeatherLocation(CurrentWeather currentWeather) {
        FavouriteLocation location = new FavouriteLocation();
        location.setLocationId(currentWeather.getCurrentWeatherId());
        location.setLocationName(currentWeather.getName());
        location.setLat(currentWeather.getCoord().getLat());
        location.setLon(currentWeather.getCoord().getLon());
        weatherDao.saveLocationAsFavourite(location);
    }

    /**
     * Get list of favourite locations
     */
    public LiveData<List<FavouriteLocation>> getFavouriteLocations() {
        return weatherDao.loadFavouriteLocations();
    }

}
