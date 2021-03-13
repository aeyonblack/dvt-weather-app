package com.tanya.dvtweatherapp.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * Defines a favourite location
 */
@Entity(tableName = "favourite_locations")
public class FavouriteLocation {

    @ColumnInfo(name = "location_id")
    @PrimaryKey
    private int locationId;

    @ColumnInfo(name = "location_name")
    private String locationName;

    @ColumnInfo(name = "longitude")
    private double lon;

    @ColumnInfo(name = "latitude")
    private double lat;

    @Ignore
    public FavouriteLocation() {
        // Default constructor
    }

    public FavouriteLocation(int locationId, String locationName, double lon, double lat) {
        this.locationId = locationId;
        this.locationName = locationName;
        this.lon = lon;
        this.lat = lat;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

}
