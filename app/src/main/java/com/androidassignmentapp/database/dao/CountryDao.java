package com.androidassignmentapp.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.androidassignmentapp.database.constants.DbConstants;
import com.androidassignmentapp.database.entity.CountryEntity;

/**
 * Dao File - Data Access Object interface
 */
@Dao
public interface CountryDao {
    @Query("SELECT * FROM " + DbConstants.TABLE_NAME_COUNTRY)
    CountryEntity getCountryInformation();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long saveAboutCountry(CountryEntity saveCountryEntity);


}
