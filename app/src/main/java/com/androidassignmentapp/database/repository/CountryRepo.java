package com.androidassignmentapp.database.repository;


import com.androidassignmentapp.database.entity.CountryEntity;

import java.util.List;

import io.reactivex.Observable;

public interface CountryRepo {

    public void saveCountryInfo(CountryEntity saveCountryList);
    public Observable<CountryEntity> getAllCountryInfo();


    //public void saveRowEntityInfo(List<RowEntity> saveRowEntity);
    //public Observable<List<RowEntity>> getRowEntityInformation();
}
