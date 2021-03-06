package com.tanya.dvtweatherapp.models;

/**
 * Main Weather Information, e.g Temperature
 */
public class Main {

    private double temp;

    private double feelsLike;

    private long pressure;

    private long humidity;

    public Main() {
    }

    public Main(double temp, double feelsLike, long pressure, long humidity) {
        this.temp = temp;
        this.feelsLike = feelsLike;
        this.pressure = pressure;
        this.humidity = humidity;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(double feelsLike) {
        this.feelsLike = feelsLike;
    }

    public long getPressure() {
        return pressure;
    }

    public void setPressure(long pressure) {
        this.pressure = pressure;
    }

    public long getHumidity() {
        return humidity;
    }

    public void setHumidity(long humidity) {
        this.humidity = humidity;
    }

}
