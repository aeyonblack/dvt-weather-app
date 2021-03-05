package com.tanya.dvtweatherapp.di;

import com.tanya.dvtweatherapp.utils.Constants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Contains all application level dependencies, these are essentially
 * dependencies of anything that will remain constant throughout the app's lifecycle
 * and will be be accessed globally, for example, the retrofit instance
 */
@Module
public class AppModule {

    /**
     * Defines a Retrofit dependency that persists while app
     * is alive (throughout app's lifecycle)
     * @return retrofit instance
     */
    @Singleton
    @Provides
    static Retrofit provideRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}
