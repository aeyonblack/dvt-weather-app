package com.tanya.dvtweatherapp.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Defines the coordinates of a weather location
 */
@Entity(tableName = "coord")
public class Coord {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @SerializedName("lon")
    @Expose
    @ColumnInfo(name = "lon")
    private double lon;

    @SerializedName("lat")
    @Expose
    @ColumnInfo(name = "lat")
    private double lat;

    @Ignore
    public Coord() {
    }

    public Coord(double lon, double lat) {
        this.lon = lon;
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

}
