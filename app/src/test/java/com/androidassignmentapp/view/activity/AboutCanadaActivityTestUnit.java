package com.androidassignmentapp.view.activity;

import android.app.Instrumentation;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.androidassignmentapp.app.CustomApplication;
import com.androidassignmentapp.database.CountryDatabase;
import com.androidassignmentapp.database.dao.CountryDao;
import com.androidassignmentapp.database.entity.CountryEntity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;



public class AboutCanadaActivityTestUnit {
    private CountryDao countryDao;
    private CountryDatabase db;

    @Mock
    Context context;

    @Before
    public void createDb() {
        db = Room.inMemoryDatabaseBuilder(context, CountryDatabase.class).build();
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }

    @Test
    public void writeUserAndReadInList() throws Exception {
        CountryEntity countryEntity = new CountryEntity();
        countryEntity.setTitle("george");
        countryDao.saveAboutCountry(countryEntity);
        CountryEntity byName = countryDao.getCountryInformation();
        assertThat(byName, equalTo(countryEntity));
    }

    private static class TestUtil {
        public static CountryEntity createUser(int i) {
            return null;
        }
    }
}