package com.tanya.dvtweatherapp.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.tanya.dvtweatherapp.models.CurrentWeather;
import com.tanya.dvtweatherapp.models.Forecast;

/**
 * Provides methods that the rest of the app uses to interact
 * with weather data in the current_weather and weather_forecast tables
 */
@Dao
public interface WeatherDao {

    /*Current Weather Data*/

    @Query("SELECT * FROM current_weather WHERE current_weather_id = :id")
    LiveData<CurrentWeather> loadCurrentWeather(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveCurrentWeather(CurrentWeather currentWeather);

    /*Weather forecast data*/

    @Query("SELECT * FROM weather_forecast")
    LiveData<Forecast> loadWeatherForecast();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveWeatherForecast(Forecast forecast);

}
