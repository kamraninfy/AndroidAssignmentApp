package com.androidassignmentapp.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.androidassignmentapp.database.constants.DbConstants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * File Description
 * This class defines the table and column name
 */
@Entity(tableName = DbConstants.TABLE_NAME_COUNTRY)
public class CountryEntity {



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @PrimaryKey
    @SerializedName("id")
    private long id;

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("rows")
    //private List<RowEntity> rows = null;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /*public List<RowEntity> getRows() {
        return rows;
    }

    public void setRows(List<RowEntity> rows) {
        this.rows = rows;
    }*/


}
