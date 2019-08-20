package com.androidassignmentapp.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.androidassignmentapp.database.dao.CountryDao;
import com.androidassignmentapp.database.entity.CountryEntity;
import com.androidassignmentapp.database.entity.RowEntity;
import com.androidassignmentapp.database.typeonverter.DataTypeConverter;

/**
 * File Description
 * Database File for Country Class
 */
@Database(entities = {CountryEntity.class, RowEntity.class}, version = 1, exportSchema = false)
@TypeConverters(DataTypeConverter.class)
public abstract class CountryDatabase extends RoomDatabase {

    public abstract CountryDao countryDao();

}