package com.tanya.dvtweatherapp.data.local;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

/**
 * Holds all database migrations
 */
public class DatabaseMigration {

    /**
     * Migration from database version 1 to version 2
     * Created a new table that holds a user's favourite locations
     */
    public static final Migration MIGRATION_1_2 = new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE 'favourite_locations' ('location_id' INTEGER, " +
                    "'location_name' TEXT, 'longitude' DOUBLE, " +
                    "'latitude' DOUBLE, PRIMARY KEY('location_id'))");
        }
    };

}
