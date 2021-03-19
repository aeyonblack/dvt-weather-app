package com.tanya.dvtweatherapp.di.main;

import android.app.Application;

import com.tanya.dvtweatherapp.data.remote.WeatherApi;
import com.tanya.dvtweatherapp.ui.main.forecast.ForecastRecyclerAdapter;
import com.tanya.dvtweatherapp.ui.main.locations.LocationsRecyclerAdapter;

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
    static ForecastRecyclerAdapter provideForecastAdapter(Application application) {
        return new ForecastRecyclerAdapter(application);
    }

    @Provides
    LocationsRecyclerAdapter provideLocationsAdapter() {
        return new LocationsRecyclerAdapter();
    }

    @Provides
    static String provideMsg() {
        return "MainActivity: ViewModel working from here";
    }

}
