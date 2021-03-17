package com.tanya.dvtweatherapp.data.remote;

import androidx.lifecycle.LiveData;

import com.tanya.dvtweatherapp.models.CurrentWeather;
import com.tanya.dvtweatherapp.models.Forecast;
import com.tanya.dvtweatherapp.network.ApiResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Queries weather data from OpenWeatherMap
 */
public interface WeatherApi {

    /**
     * Get current weather data
     * @param id - location id
     * @return Current weather data for specified location with id = id
     */
    @GET("weather?appid=392dacf20be20e6d845849645c242968&units=metric")
    LiveData<ApiResponse<CurrentWeather>> getCurrentWeather(
            @Query("id") int id
    );

    /**
     * Get 5 day forecast
     * @param id - location id
     * @return 5 Day forecast for specified location
     */
    @GET("forecast?appid=392dacf20be20e6d845849645c242968&units=metric")
    LiveData<ApiResponse<Forecast>> getFiveDayForecast(
            @Query("id") int id
    );

}
