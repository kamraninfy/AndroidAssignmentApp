package com.androidassignmentapp.database.repository;


import com.androidassignmentapp.database.entity.CountryEntity;

import io.reactivex.Observable;

public interface CountryRepo {

    void saveCountryInfo(CountryEntity saveCountryList);
    Observable<CountryEntity> getAllCountryInfo();

}
