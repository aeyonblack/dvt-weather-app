package com.tanya.dvtweatherapp.di.main;

import androidx.lifecycle.ViewModel;

import com.tanya.dvtweatherapp.di.ViewModelKey;
import com.tanya.dvtweatherapp.ui.main.forecast.ForecastViewModel;
import com.tanya.dvtweatherapp.ui.main.locations.LocationsViewModel;
import com.tanya.dvtweatherapp.ui.main.today.TodayViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class MainViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(TodayViewModel.class)
    public abstract ViewModel bindTodayViewModel(TodayViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ForecastViewModel.class)
    public abstract ViewModel bindForecastViewModel(ForecastViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(LocationsViewModel.class)
    public abstract ViewModel bindLocationsViewModel(LocationsViewModel viewModel);

}
