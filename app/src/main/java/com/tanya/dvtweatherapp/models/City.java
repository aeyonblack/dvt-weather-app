package com.tanya.dvtweatherapp.models;

/**
 * Basic information about a weather location
 * e.g. City name, country, id, etc.
 */
public class City {

    private long id;

    private String name;

    private String country;

    public City() {
    }

    public City(long id, String name, String country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
