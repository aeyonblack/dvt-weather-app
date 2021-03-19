package com.tanya.dvtweatherapp.utils;

import android.text.format.DateFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateFormatter {

    /**
     * Get what day of week it is i.e, Sunday, Monday e.t.c
     */
    public static String getDayOfWeek(Long time) {
        Date date = new Date(time);
        return (String)DateFormat.format("EEEE", date);
    }

    public static String getDayOfWeek(String date, int dayCount) {
        String inputFormat = "yyyy-MM-dd HH:mm:ss";
        Date parsed = null;
        SimpleDateFormat input = new SimpleDateFormat(inputFormat, Locale.getDefault());

        try {
            parsed = input.parse(date);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd EEEE", Locale.getDefault());
            if (parsed != null) {
                date = dateFormat.format(parsed.getTime() + (dayCount*24*60*60*1000));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String getDay(Long time, int dayCount) {
        String date = (String)DateFormat.format("yyyy-MM-dd HH:mm:ss", time);
        return getDayOfWeek(date, dayCount);
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
