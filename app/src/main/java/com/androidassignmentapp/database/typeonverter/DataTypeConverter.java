package com.androidassignmentapp.database.typeonverter;

import android.arch.persistence.room.TypeConverter;

import com.androidassignmentapp.database.entity.RowEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class DataTypeConverter {
    private static Gson gson = new Gson();
    @TypeConverter
    public static List<RowEntity> stringToList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<RowEntity>>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String ListToString(List<RowEntity> someObjects) {
        return gson.toJson(someObjects);
    }
}
