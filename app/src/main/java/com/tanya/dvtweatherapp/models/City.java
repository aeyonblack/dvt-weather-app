package com.tanya.dvtweatherapp.models;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Basic information about a weather location
 * e.g. City name, country, id, etc.
 */
@Entity(tableName = "city")
public class City {

    @SerializedName("id")
    @Expose
    @PrimaryKey(autoGenerate = true)
    private long cityId;

    @SerializedName("name")
    @Expose
    @ColumnInfo(name = "name")
    private String name;

    @SerializedName("coord")
    @Expose
    @Embedded
    private Coord coord;

    @SerializedName("country")
    @Expose
    @ColumnInfo(name = "country")
    private String country;

    @Ignore
    public City() {
    }

    public City(long cityId, String name, Coord coord, String country) {
        this.cityId = cityId;
        this.name = name;
        this.coord = coord;
        this.country = country;
    }

    public long getCityId() {
        return cityId;
    }

    public void setCityId(long cityId) {
        this.cityId = cityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
