package com.tanya.dvtweatherapp.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * A model for the detailed description of a location's weather
 */
@Entity(tableName = "weather")
public class Weather {

    @SerializedName("id")
    @Expose
    @PrimaryKey
    private long weatherId;

    @SerializedName("main")
    @Expose
    @ColumnInfo(name = "main")
    private String main;

    @SerializedName("description")
    @Expose
    @ColumnInfo(name = "description")
    private String description;

    @SerializedName("icon")
    @Expose
    @ColumnInfo(name = "icon")
    private String icon;

    @Ignore
    public Weather() {
    }

    public Weather(long weatherId, String main, String description, String icon) {
        this.weatherId = weatherId;
        this.main = main;
        this.description = description;
        this.icon = icon;
    }

    public long getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(long weatherId) {
        this.weatherId = weatherId;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

}
