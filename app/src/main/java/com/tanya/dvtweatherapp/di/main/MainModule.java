package com.tanya.dvtweatherapp.di.main;

import com.tanya.dvtweatherapp.network.WeatherApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Contains all dependencies for MainActivity.
 * The dependencies provided here will only be available
 * for use by MainActivity.
 */
@Module
public class MainModule {

    @Provides
    static WeatherApi provideWeatherAPI(Retrofit retrofit) {
        return retrofit.create(WeatherApi.class);
    }

    @Provides
    static String provideSuccessMessage() {
        return "Weather API is not null";
    }

}
