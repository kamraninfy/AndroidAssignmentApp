package com.androidassignmentapp.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.androidassignmentapp.database.dao.CountryDao;
import com.androidassignmentapp.database.dao.RowDao;
import com.androidassignmentapp.database.entity.CountryEntity;

/**
 * File Description
 * Database File for Country Class
 */
@Database(entities = {CountryEntity.class}, version = 1,exportSchema = false)
public abstract class CountryDatabase extends RoomDatabase {

    public abstract CountryDao countryDao();
    public abstract RowDao rowDao();


}