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

    @PrimaryKey(autoGenerate = true)
    private long id;

    @SerializedName("temp")
    @Expose
    @ColumnInfo(name = "temp")
    private double temp;

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

    public Main(double temp, long pressure, long humidity) {
        this.temp = temp;
        this.pressure = pressure;
        this.humidity = humidity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
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
