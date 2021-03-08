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
 * A model for the current weather of a specified location
 */
@Entity(tableName = "current_weather")
public class CurrentWeather {

    @SerializedName("id")
    @Expose
    @PrimaryKey
    private int id;

    @SerializedName("name")
    @Expose
    @ColumnInfo(name = "name")
    private String name;

    @SerializedName("dt")
    @Expose
    @ColumnInfo(name = "date")
    private long dt;

    @SerializedName("cod")
    @Expose
    @ColumnInfo(name = "cod")
    private String cod;

    @SerializedName("coord")
    @Expose
    @Embedded
    private Coord coord;

    @SerializedName("main")
    @Expose
    @Embedded
    private Main main;

    @SerializedName("weather")
    @Expose
    @ColumnInfo(name = "weather")
    //TODO: Add list converter
    private List<Weather> weather;

    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Ignore
    public CurrentWeather() {
        // Default Constructor
    }

    public CurrentWeather(int id, String name, long dt, String cod, Coord coord, Main main, List<Weather> weather) {
        this.id = id;
        this.name = name;
        this.dt = dt;
        this.cod = cod;
        this.coord = coord;
        this.main = main;
        this.weather = weather;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
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

