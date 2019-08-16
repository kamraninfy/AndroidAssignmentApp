package com.androidassignmentapp.database.repository;

import com.androidassignmentapp.database.dao.CountryDao;
import com.androidassignmentapp.database.entity.CountryEntity;

import java.util.concurrent.Callable;

import io.reactivex.Observable;

public class CountryRepository implements CountryRepo{

    private CountryDao countryDao;


    public CountryRepository(CountryDao countryDao) {
        this.countryDao = countryDao;
    }



    @Override
    public void saveCountryInfo(CountryEntity saveCountryInformation) {
        countryDao.saveAboutCountry(saveCountryInformation);
    }

    @Override
    public Observable<CountryEntity> getAllCountryInfo() {
        return Observable.fromCallable(new Callable<CountryEntity>() {
            @Override
            public CountryEntity call() throws Exception {
                return countryDao.getCountryInformation();
            }
        });
    }
}