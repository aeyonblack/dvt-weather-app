package com.tanya.dvtweatherapp.di;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.room.Room;

import com.tanya.dvtweatherapp.data.local.WeatherDatabase;
import com.tanya.dvtweatherapp.data.local.dao.WeatherDao;
import com.tanya.dvtweatherapp.utils.Constants;
import com.tanya.dvtweatherapp.utils.LiveDataCallAdapterFactory;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

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
                .addCallAdapterFactory(new LiveDataCallAdapterFactory()) // TODO: Maybe change this to use RxJava2
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    static WeatherDatabase provideWeatherDatabase(Application application) {
        return Room.databaseBuilder(application, WeatherDatabase.class, "weather_data.db")
                .build();
    }

    @Singleton
    @Provides
    static WeatherDao provideWeatherDao(WeatherDatabase weatherDatabase) {
        return weatherDatabase.weatherDao();
    }

    @Singleton
    @Provides
    static boolean provideNetworkState(Application application) {
        ConnectivityManager manager = (ConnectivityManager)application
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        return manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() ==
                NetworkInfo.State.CONNECTED || manager
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                .getState() == NetworkInfo.State.CONNECTED;
    }

}
