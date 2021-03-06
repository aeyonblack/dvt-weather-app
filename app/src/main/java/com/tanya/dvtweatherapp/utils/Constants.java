package com.tanya.dvtweatherapp.utils;

public class Constants {

    // API Key for OpenWeatherMap
    public static final String OPEN_WEATHER_API_KEY = "392dacf20be20e6d845849645c242968";

    // OpenWeatherMap REST API Base Url used by retrofit
    public static final String BASE_URL = "https://api.openweathermap.org/data/2.5/";

    // Used to get current weather data
    public static final String CURRENT_WEATHER_REQUEST = "weather?units=metric&appid=" +
            OPEN_WEATHER_API_KEY;

}
