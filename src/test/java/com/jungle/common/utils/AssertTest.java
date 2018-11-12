package com.jungle.common.utils;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.HashSet;

public class AssertTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Test(expected = IllegalArgumentException.class)
    public void notNull() {
        Assert.notNull(new Object(), "args not null");
        Assert.notNull(null, "args can't be null");
    }

    @Test(expected = IllegalArgumentException.class)
    public void notZeroSize() {
        Assert.notZeroSize(new HashSet<String>() {{
            add("1");
        }}, "args not zero size");
        Assert.notZeroSize(new ArrayList(), "args can't be zero size");
    }
}