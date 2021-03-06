package com.tanya.dvtweatherapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * A model for the current weather of a specified location
 */
public class CurrentWeather {

    @SerializedName("dt")
    @Expose
    private long dt;

    @SerializedName("cod")
    @Expose
    private String cod;

    @SerializedName("city")
    @Expose
    private City city;

    @SerializedName("coord")
    @Expose
    private Coord coord;

    @SerializedName("main")
    @Expose
    private Main main;

    @SerializedName("weather")
    @Expose
    private Weather weather;

    public CurrentWeather() {
        // Default Constructor
    }

    public CurrentWeather(long dt, String cod, City city, Coord coord, Main main, Weather weather) {
        this.dt = dt;
        this.cod = cod;
        this.city = city;
        this.coord = coord;
        this.main = main;
        this.weather = weather;
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

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

}

