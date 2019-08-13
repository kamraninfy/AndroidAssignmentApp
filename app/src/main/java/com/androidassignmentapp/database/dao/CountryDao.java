package com.androidassignmentapp.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.androidassignmentapp.database.entity.CountryEntity;

import java.util.List;

/**
 * File Description
 * Dao File
 */
@Dao
public interface CountryDao {
    @Query("SELECT * FROM country_information")
    LiveData<List<CountryEntity>> loadPopularArticles();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveCountryInformation(List<CountryEntity> saveCountryInformation);

}
