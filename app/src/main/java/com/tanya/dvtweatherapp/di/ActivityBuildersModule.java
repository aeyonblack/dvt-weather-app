package com.tanya.dvtweatherapp.di;

import com.tanya.dvtweatherapp.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Manages all dependencies related to activities
 */
@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector
    abstract MainActivity contributeMainActivity();

}
