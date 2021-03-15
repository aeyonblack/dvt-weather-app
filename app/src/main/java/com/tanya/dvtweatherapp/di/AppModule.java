package com.tanya.dvtweatherapp.di;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.tanya.dvtweatherapp.R;
import com.tanya.dvtweatherapp.data.local.DatabaseMigration;
import com.tanya.dvtweatherapp.data.local.WeatherDatabase;
import com.tanya.dvtweatherapp.data.local.dao.WeatherDao;
import com.tanya.dvtweatherapp.utils.Constants;
import com.tanya.dvtweatherapp.utils.LiveDataCallAdapterFactory;
import com.tanya.dvtweatherapp.utils.NetworkUtil;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
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
     * Defines the OkHttpClient for use with retrofit
     * @return OkHttpClient
     */
    @Singleton
    @Provides
    static OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.connectTimeout(Constants.CONNECTION_TIMEOUT, TimeUnit.SECONDS);
        client.readTimeout(Constants.READ_TIMEOUT, TimeUnit.SECONDS);
        client.writeTimeout(Constants.WRITE_TIMEOUT, TimeUnit.SECONDS);
        client.retryOnConnectionFailure(false);
        return client.build();
    }

    /**
     * Defines a Retrofit dependency that persists while app
     * is alive (throughout app's lifecycle)
     * @return retrofit instance
     */
    @Singleton
    @Provides
    static Retrofit provideRetrofitInstance(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     * Provides a persistent reference to the weather database
     * @param application - the application
     * @return weather database instance
     * TODO: Remove allowMainThreadQueries, use RxJava to move queries off the main thread
     */
    @Singleton
    @Provides
    static WeatherDatabase provideWeatherDatabase(Application application) {
        return Room.databaseBuilder(application, WeatherDatabase.class, "weather_data.db")
                .addMigrations(DatabaseMigration.MIGRATION_1_2)
                .allowMainThreadQueries()
                .build();
    }

    @Singleton
    @Provides
    static WeatherDao provideWeatherDao(WeatherDatabase weatherDatabase) {
        return weatherDatabase.weatherDao();
    }

    @Singleton
    @Provides
    static RequestOptions provideRequestOptions() {
        return RequestOptions
                .placeholderOf(R.drawable.ic_baseline_cloud_off_24)
                .error(R.drawable.ic_baseline_cloud_off_24);

    }

    @Singleton
    @Provides
    static RequestManager provideGlideInstance(Application application, RequestOptions options) {
        return Glide.with(application)
                .setDefaultRequestOptions(options);
    }

}
