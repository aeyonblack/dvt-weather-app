package com.tanya.dvtweatherapp.network;

import com.tanya.dvtweatherapp.models.CurrentWeather;
import com.tanya.dvtweatherapp.utils.Constants;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Queries weather data from OpenWeatherMap
 */
public interface WeatherApi {

    @GET(Constants.CURRENT_WEATHER_REQUEST)
    Flowable<CurrentWeather> getCurrentWeather(
            @Query("id") int cityId
    );

}
