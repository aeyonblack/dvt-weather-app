package com.tanya.dvtweatherapp.di;

import androidx.lifecycle.ViewModelProvider;

import com.tanya.dvtweatherapp.viewmodel.ViewModelProviderFactory;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ViewModelFactoryModule {

    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory providerFactory);

}
