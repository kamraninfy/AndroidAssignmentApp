package com.androidassignmentapp.view.activity;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.androidassignmentapp.database.CountryDatabase;
import com.androidassignmentapp.database.dao.CountryDao;
import com.androidassignmentapp.database.entity.CountryEntity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.Collections;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;


@RunWith(AndroidJUnit4.class)
public class AboutCanadaActivityUnitTest {
    /*@Rule
    public TestRule rule = new InstantTaskExecutorRule();

    private CountryDatabase database;
    private CountryDao dao;

    @Mock
    private Observer<List<Todo>> observer;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        Context context = InstrumentationRegistry.getTargetContext();
        database = Room.inMemoryDatabaseBuilder(context, CountryDatabase.class)
                .allowMainThreadQueries().build();
        dao = database.todoDao();
    }

    @After
    public void tearDown() throws Exception {
        database.close();
    }

    @Test
    public void insert() throws Exception {
        // given
        Todo todo = new Todo("12345", "Mockito", "Time to learn something new");
        dao.selectAll().observeForever(observer);
        // when
        dao.insert(todo);
        // then
        verify(observer).onChanged(Collections.singletonList(todo));
    }*/
}