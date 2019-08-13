package com.androidassignmentapp.view.activity;

import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AboutCanadaActivityTestUnit {

    @Mock
    private ArrayList<String> mockArrayList;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    public void testMyTest() {
        when(mockArrayList.get(0)).thenReturn("Hello world");

        String result = mockArrayList.get(0);

        assertEquals("Should have the correct string", "Hello world", result);

        verify(mockArrayList).get(0);
    }
}