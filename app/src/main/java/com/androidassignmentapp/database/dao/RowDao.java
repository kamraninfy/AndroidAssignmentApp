package com.androidassignmentapp.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.androidassignmentapp.database.constants.DbConstants;

import java.util.List;

@Dao
public interface RowDao {

//    @Query("SELECT * FROM " + DbConstants.TABLE_NAME_ROW)
//    List<RowEntity> getRowEntityInformation();
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    void saveCountryRowInformation(List<RowEntity> saveRowEntityList);

}
