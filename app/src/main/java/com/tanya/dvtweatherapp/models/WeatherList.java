package com.tanya.dvtweatherapp.models;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * List of forecast weather
 */
@Entity(tableName = "weather_list")
public class WeatherList {

    @PrimaryKey(autoGenerate = true)
    private long weatherListId;

    @SerializedName("dt")
    @Expose
    @ColumnInfo(name = "date")
    private long dt;

    @SerializedName("main")
    @Expose
    @Embedded
    private Main main;

    @SerializedName("weather")
    @Expose
    @ColumnInfo(name = "weather")
    // TODO: Add list type converter
    private List<Weather> weather;

    @Ignore
    public WeatherList() {
        // Default empty constructor
    }

    public WeatherList(long dt, Main main, List<Weather> weather) {
        this.dt = dt;
        this.main = main;
        this.weather = weather;
    }

    public long getWeatherListId() {
        return weatherListId;
    }

    public void setWeatherListId(long weatherListId) {
        this.weatherListId = weatherListId;
    }

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

}
