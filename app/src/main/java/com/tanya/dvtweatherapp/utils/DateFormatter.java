package com.tanya.dvtweatherapp.utils;

import android.text.format.DateFormat;

import java.util.Date;

public class DateFormatter {

    /**
     * Get what day of week it is i.e, Sunday, Monday e.t.c
     */
    public static String getDayOfWeek(Long time) {
        Date date = new Date(time);
        return (String)DateFormat.format("EEEE", date);
    }

    /**
     * Return the actual day of month e.g 15th (of March)
     */
    public static String getDay(Date date) {
        return (String)DateFormat.format("dd", date);
    }

    /**
     * Get the month
     */
    public static String getMonth(Date date) {
        return (String)DateFormat.format("MMMM", date);
    }

    /**
     * Return the time -> 04:16 pm
     */
    private static String getTime(Date date) {
        return (String)DateFormat.format("HH:mm", date);
    }

    /**
     * Get a string combination of month, day and time
     */
    public static String getTimeSinceLastUpdate(Long time) {
        Date date = new Date(time);
        return getMonth(date) + " " + getDay(date) + ", " + getTime(date);
    }

}
