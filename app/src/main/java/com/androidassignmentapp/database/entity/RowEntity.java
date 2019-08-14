package com.androidassignmentapp.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.androidassignmentapp.database.constants.DbConstants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = DbConstants.TABLE_NAME_ROW)
public class RowEntity implements Serializable
{

    @SerializedName("rowId")
    @PrimaryKey
    private int rowId;

    public int getRowId() {
        return rowId;
    }

    public void setRowId(int rowId) {
        this.rowId = rowId;
    }

    @SerializedName("title")
    
    private String title;
    @SerializedName("description")
    
    private String description;

    public String getImageHref() {
        return imageHref;
    }

    public void setImageHref(String imageHref) {
        this.imageHref = imageHref;
    }

    @SerializedName("imageHref")
    
    //private Object imageHref;
    private String imageHref;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /*public Object getImageHref() {
        return imageHref;
    }

    public void setImageHref(Object imageHref) {
        this.imageHref = imageHref;
    }*/

}
