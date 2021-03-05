package com.tanya.dvtweatherapp.di.main;

import com.tanya.dvtweatherapp.ui.main.forecast.ForecastFragment;
import com.tanya.dvtweatherapp.ui.main.locations.LocationsFragment;
import com.tanya.dvtweatherapp.ui.main.today.TodayFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainFragmentBuilderModule {

    @ContributesAndroidInjector
    abstract TodayFragment contributeTodayFragment();

    @ContributesAndroidInjector
    abstract ForecastFragment contributeForecastFragment();

    @ContributesAndroidInjector
    abstract LocationsFragment contributeLocationsFragment();

}
