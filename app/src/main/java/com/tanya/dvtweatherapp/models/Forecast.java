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
 * A model for the 5 Day Weather Forecast
 */
@Entity(tableName = "weather_forecast")
public class Forecast {

    @PrimaryKey(autoGenerate = true)
    private long forecastId;

    @SerializedName("cod")
    @Expose
    @ColumnInfo(name = "cod")
    private String cod;

    @SerializedName("cnt")
    @Expose
    @ColumnInfo(name = "cnt")
    private int cnt;

    @SerializedName("list")
    @Expose
    @ColumnInfo(name = "list")
    private List<WeatherList> list;

    @SerializedName("city")
    @Expose
    @Embedded
    private City city;

    private boolean isError;

    private String errorMessage;

    @Ignore
    public Forecast() {
        // Default empty constructor
    }

    public String getCod() {
        return cod;
    }

    public Forecast(String cod, int cnt, List<WeatherList> list, City city) {
        this.cod = cod;
        this.cnt = cnt;
        this.list = list;
        this.city = city;
    }

    public long getForecastId() {
        return forecastId;
    }

    public void setForecastId(long forecastId) {
        this.forecastId = forecastId;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public List<WeatherList> getList() {
        return list;
    }

    public void setList(List<WeatherList> list) {
        this.list = list;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
