package com.androidassignmentapp.view.activity;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.runner.AndroidJUnit4;

import com.androidassignmentapp.database.CountryDatabase;
import com.androidassignmentapp.database.dao.CountryDao;
import com.androidassignmentapp.database.entity.CountryEntity;
import com.androidassignmentapp.database.entity.RowEntity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

@RunWith(AndroidJUnit4.class)
public class AboutCanadaActivityTestUnit {


    private CountryDatabase db;
    Context context;
    CountryEntity countryEntity;
    CountryDao countryDao;
    RowEntity rowEntity;
    List<RowEntity> rowEntities;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void createDb() {
        context = mock(Context.class);
        countryEntity = Mockito.spy(new CountryEntity());
        rowEntity = Mockito.spy(new RowEntity());
        rowEntities = Mockito.spy(new ArrayList<>());

        db = Room.inMemoryDatabaseBuilder(context, CountryDatabase.class).build();
        countryDao = db.countryDao();
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }

    @Test
    public void writeUserAndReadInList() throws Exception {
        countryEntity.setTitle("george");
        long rowId = countryDao.saveAboutCountry(countryEntity);
        CountryEntity entity = countryDao.getCountryInformation();
        assertEquals(entity.getId(), rowId);
    }

    @Test
    public void createUserInDB() throws Exception {
        countryEntity.setTitle("george");
        long rowId = countryDao.saveAboutCountry(countryEntity);
        CountryEntity entity = countryDao.getCountryInformation();
        assertNotNull(entity);
    }

    @Test
    public void failWriteUserAndReadInList() throws Exception {
        countryEntity.setTitle("george");
        long rowId = countryDao.saveAboutCountry(countryEntity);
        CountryEntity entity = countryDao.getCountryInformation();
        assertNotEquals("George", entity.getTitle());
    }

}