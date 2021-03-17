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

    @SerializedName("dt_txt")
    @Expose
    @ColumnInfo(name = "dt_txt")
    private String dtTxt;

    @SerializedName("main")
    @Expose
    @Embedded
    private Main main;

    @SerializedName("weather")
    @Expose
    @ColumnInfo(name = "weather")
    private List<Weather> weather;

    @Ignore
    public WeatherList() {
        // Default empty constructor
    }

    public WeatherList(long dt, String dtTxt, Main main, List<Weather> weather) {
        this.dt = dt;
        this.dtTxt = dtTxt;
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

    public String getDtTxt() {
        return dtTxt;
    }

    public void setDtTxt(String dtTxt) {
        this.dtTxt = dtTxt;
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
