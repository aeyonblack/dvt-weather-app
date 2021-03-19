package com.tanya.dvtweatherapp.data.remote;

import androidx.lifecycle.LiveData;

import com.tanya.dvtweatherapp.models.CurrentWeather;
import com.tanya.dvtweatherapp.models.Forecast;
import com.tanya.dvtweatherapp.network.ApiResponse;
import com.tanya.dvtweatherapp.utils.Constants;

import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Queries weather data from OpenWeatherMap
 */
public interface WeatherApi {

    /**
     * Get current weather data using location id
     * @param id - location id
     * @return Current weather data for specified location with id = id
     */
    @GET("weather?appid=" + Constants.APP_ID + "&units=metric")
    LiveData<ApiResponse<CurrentWeather>> getCurrentWeather(
            @Query("id") int id
    );

    /**
     * Query weather data using city name
     */
    @GET("weather?appid=" + Constants.APP_ID + "&units=metric")
    LiveData<ApiResponse<CurrentWeather>> getCurrentWeather(
            @Query("q") String cityName
    );

    /**
     * Query weather data using coordinates
     */
    @GET("weather?appid=" + Constants.APP_ID + "&units=metric")
    LiveData<ApiResponse<CurrentWeather>> getCurrentWeather(
            @Query("lat") double lat,
            @Query("lon") double lon
    );

    /**
     * Get 5 day forecast
     * @param id - location id
     * @return 5 Day forecast for specified location
     */
    @GET("forecast?appid="+ Constants.APP_ID +"&units=metric&cnt=40")
    LiveData<ApiResponse<Forecast>> getFiveDayForecast(
            @Query("id") int id
    );

    /**
     * Get forecast using city name
     */
    @GET("forecast?appid="+ Constants.APP_ID +"&units=metric&cnt=40")
    LiveData<ApiResponse<Forecast>> getFiveDayForecast(
            @Query("q") String cityName
    );

    /**
     * Get forecast using coordinates
     */
    @GET("forecast?appid="+ Constants.APP_ID +"&units=metric&cnt=40")
    LiveData<ApiResponse<Forecast>> getFiveDayForecast(
            @Query("lat") double lat,
            @Query("lon") double lon
    );

}
