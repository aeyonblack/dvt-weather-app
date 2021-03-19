package com.tanya.dvtweatherapp.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.tanya.dvtweatherapp.models.CurrentWeather;
import com.tanya.dvtweatherapp.models.FavouriteLocation;
import com.tanya.dvtweatherapp.models.Forecast;

import java.util.List;

/**
 * Provides methods that the rest of the app uses to interact
 * with weather data in the current_weather, favourite_locations and weather_forecast tables
 */
@Dao
public interface WeatherDao {

    /*Current Weather Data*/

    // Load using id
    @Query("SELECT * FROM current_weather WHERE current_weather_id = :id")
    LiveData<CurrentWeather> loadCurrentWeather(int id);

    // Load using city name
    @Query("SELECT * FROM current_weather WHERE LOWER(name) = :cityName")
    LiveData<CurrentWeather> loadCurrentWeather(String cityName);

    // Load using geographic coordinates
    @Query("SELECT * FROM current_weather WHERE lat = :lat AND lon = :lon")
    LiveData<CurrentWeather> loadCurrentWeather(double lat, double lon);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveCurrentWeather(CurrentWeather currentWeather);

    /*Weather forecast data*/

    // Load using id
    @Query("SELECT * FROM weather_forecast WHERE cityId = :id")
    LiveData<Forecast> loadWeatherForecast(int id);

    // Load using city name
    @Query("SELECT * FROM weather_forecast WHERE LOWER(name) = :cityName")
    LiveData<Forecast> loadWeatherForecast(String cityName);

    // Load using geographic coordinates
    @Query("SELECT * FROM weather_forecast WHERE lat = :lat AND lon = :lon")
    LiveData<Forecast> loadWeatherForecast(double lat, double lon);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveWeatherForecast(Forecast forecast);

    @Query("DELETE FROM weather_forecast WHERE cityId = :id")
    void deleteOldForecast(int id);

    @Query("DELETE FROM weather_forecast WHERE name = :cityName")
    void deleteOldForecast(String cityName);

    @Query("DELETE FROM weather_forecast WHERE lat = :lat AND lon = :lon")
    void deleteOldForecast(double lat, double lon);

    /*Favourite locations data*/

    @Query("SELECT * FROM favourite_locations")
    LiveData<List<FavouriteLocation>> loadFavouriteLocations();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveLocationAsFavourite(FavouriteLocation location);

}
