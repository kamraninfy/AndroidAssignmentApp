package com.androidassignmentapp.database.entity;

import android.arch.persistence.room.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "row_information")
public class RowEntity implements Serializable
{

    @SerializedName("title")
    
    private String title;
    @SerializedName("description")
    
    private String description;
    @SerializedName("imageHref")
    
    private Object imageHref;

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

    public Object getImageHref() {
        return imageHref;
    }

    public void setImageHref(Object imageHref) {
        this.imageHref = imageHref;
    }

}
