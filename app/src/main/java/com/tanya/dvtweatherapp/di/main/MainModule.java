package com.tanya.dvtweatherapp.di.main;

import com.tanya.dvtweatherapp.data.remote.WeatherApi;
import com.tanya.dvtweatherapp.ui.main.forecast.ForecastRecyclerAdapter;

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
    static ForecastRecyclerAdapter providesForecastAdapter() {
        return new ForecastRecyclerAdapter();
    }

    @Provides
    static String provideMsg() {
        return "Test";
    }

}
