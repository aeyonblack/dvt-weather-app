package com.tanya.dvtweatherapp.di;

import com.tanya.dvtweatherapp.di.main.MainFragmentBuilderModule;
import com.tanya.dvtweatherapp.di.main.MainViewModelsModule;
import com.tanya.dvtweatherapp.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Creates SubComponents for different activities
 */
@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(
            modules = {
                    MainFragmentBuilderModule.class,
                    MainViewModelsModule.class,
            }
    )
    abstract MainActivity contributeMainActivity();

}
