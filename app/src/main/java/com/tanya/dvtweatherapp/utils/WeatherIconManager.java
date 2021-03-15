package com.tanya.dvtweatherapp.utils;

import com.tanya.dvtweatherapp.R;

/**
 * returns image icon for respective weather condition
 */
public class WeatherIconManager {

    public static int getIcon(String weatherIcon, String weatherDescription) {
        int icon;
        switch (weatherIcon) {
            case "01d":
                icon = R.drawable.sunny_light_color_96dp;
                break;
            case "01n":
                icon = R.drawable.clear_night_light_color_96dp;
                break;
            case "02d":
                icon = R.drawable.mostly_sunny_light_color_96dp;
                break;
            case "02n":
                icon = R.drawable.mostly_clear_night_light_color_96dp;
                break;
            case "03d":
                icon = R.drawable.partly_cloudy_light_color_96dp;
                break;
            case "03n":
                icon = R.drawable.partly_cloudy_night_light_color_96dp;
                break;
            case "04d":
                icon = R.drawable.cloudy_light_color_96dp;
                if (weatherDescription.contains("broken")) {
                    icon = R.drawable.mostly_cloudy_day_light_color_96dp;
                }
                break;
            case "04n":
                icon = R.drawable.cloudy_light_color_96dp;
                if (weatherDescription.contains("broken")) {
                    icon = R.drawable.mostly_cloudy_night_light_color_96dp;
                }
                break;
            case "09d":
            case "09n":
                icon = R.drawable.showers_rain_light_color_96dp;
                break;
            case "10d":
                icon = R.drawable.scattered_showers_day_light_color_96dp;
                break;
            case "10n":
                icon = R.drawable.scattered_showers_night_light_color_96dp;
                break;
            case "11d":
            case "11n":
                icon = R.drawable.strong_tstorms_light_color_96dp;
                break;
            case "13d":
            case "13n":
                icon = R.drawable.snow_showers_snow_light_color_96dp;
                break;
            case "50d":
            case "50n":
                icon = R.drawable.haze_fog_dust_smoke_light_color_96dp;
                break;
            default:
                icon = R.drawable.wintry_mix_rain_snow_light_color_96dp;
        }
        return icon;
    }

}
