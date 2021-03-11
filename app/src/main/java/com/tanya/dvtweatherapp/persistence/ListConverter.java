package com.tanya.dvtweatherapp.persistence;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tanya.dvtweatherapp.models.Weather;
import com.tanya.dvtweatherapp.models.WeatherList;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class ListConverter {

    @TypeConverter
    public static List<WeatherList> stringToWeatherLists(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<WeatherList>>() {}.getType();
        return new Gson().fromJson(data, listType);
    }

    @TypeConverter
    public static String weatherListsToString(List<WeatherList> weatherLists) {
        return new Gson().toJson(weatherLists);
    }

    @TypeConverter
    public static List<Weather> stringToWeather(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<Weather>>() {}.getType();
        return new Gson().fromJson(data, listType);
    }

    @TypeConverter
    public static String weatherToString(List<Weather> someObjects) {
        return new Gson().toJson(someObjects);
    }


}
