package com.tanya.dvtweatherapp.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.tanya.dvtweatherapp.data.local.dao.WeatherDao;
import com.tanya.dvtweatherapp.models.CurrentWeather;
import com.tanya.dvtweatherapp.models.FavouriteLocation;
import com.tanya.dvtweatherapp.models.Forecast;
import com.tanya.dvtweatherapp.persistence.ListConverter;

/**
 * The local database responsible for persisting weather data
 */
@Database(entities = {CurrentWeather.class, Forecast.class, FavouriteLocation.class},
        version = 2, exportSchema = false)
@TypeConverters({ListConverter.class})
public abstract class WeatherDatabase extends RoomDatabase {
    public abstract WeatherDao weatherDao();
}
