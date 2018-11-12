package com.jungle.common.utils;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;

public class AssertTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Test(expected = IllegalArgumentException.class)
    public void notNull() {
        Assert.notNull(null, "args can't be null");
    }

    @Test(expected = IllegalArgumentException.class)
    public void notZeroSize() {
        Assert.notZeroSize(new ArrayList(), "args can't be zero size");
    }
}