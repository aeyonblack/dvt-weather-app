package com.tanya.dvtweatherapp.models;

/**
 * A model for the current weather of a specified location
 */
public class CurrentWeather {

    private long dt;

    private String cod;

    private City city;

    private Coord coord;

    private Main main;

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

