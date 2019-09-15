package com.example.biggernumberguess;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ValidInputTest {
    private  MainActivity activity;
    private String input;

    @Before
    public void setUp() throws Exception {
        activity = new MainActivity();
    }

    @Test
    public void invalidInput() {
        input = "0";
        assertTrue(activity.validInput(input));
    }
}