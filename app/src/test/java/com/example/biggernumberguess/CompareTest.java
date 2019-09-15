package com.example.biggernumberguess;

import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.junit.Before;
import org.junit.Test;

import static java.security.AccessController.getContext;
import static org.junit.Assert.*;

public class CompareTest extends AppCompatActivity {
    private  MainActivity activity;
    private String input;
    @Before
    public void setUp() throws Exception {

    }
    @Test
    public void compareNumber() {
        input = "q";
        assertEquals("You are quitting this app",compareNumber(input));

    }
}