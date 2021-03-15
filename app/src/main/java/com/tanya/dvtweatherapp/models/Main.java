package com.tanya.dvtweatherapp.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Main Weather Information, e.g Temperature
 */
@Entity(tableName = "main")
public class Main {

    //@PrimaryKey(autoGenerate = true)
    private long mainId;

    @SerializedName("temp")
    @Expose
    @ColumnInfo(name = "temp")
    private double temp;

    @SerializedName("temp_min")
    @Expose
    @ColumnInfo(name = "temp_min")
    private double minTemp;

    @SerializedName("temp_max")
    @Expose
    @ColumnInfo(name = "temp_max")
    private double maxTemp;

    @SerializedName("feels_like")
    @Expose
    @ColumnInfo(name = "feels_like")
    private double feelsLike;

    @SerializedName("pressure")
    @Expose
    @ColumnInfo(name = "pressure")
    private long pressure;

    @SerializedName("humidity")
    @Expose
    @ColumnInfo(name = "humidity")
    private long humidity;


    @Ignore
    public Main() {
    }

    public Main(long mainId, double temp, double minTemp, double maxTemp, double feelsLike, long pressure, long humidity) {
        this.mainId = mainId;
        this.temp = temp;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.feelsLike = feelsLike;
        this.pressure = pressure;
        this.humidity = humidity;
    }

    public long getMainId() {
        return mainId;
    }

    public void setMainId(long mainId) {
        this.mainId = mainId;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(double minTemp) {
        this.minTemp = minTemp;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(double maxTemp) {
        this.maxTemp = maxTemp;
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
