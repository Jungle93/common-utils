package com.jungle.common.utils;

import org.junit.Assert;
import org.junit.Test;

public class StringUtilsTest {

    @Test
    public void isEmpty() {
        Assert.assertTrue(StringUtils.isEmpty(null));
        Assert.assertTrue(StringUtils.isEmpty(""));
        Assert.assertTrue(StringUtils.isEmpty("    \n"));
        Assert.assertFalse(StringUtils.isEmpty("a"));
        Assert.assertFalse(StringUtils.isEmpty("b "));
        Assert.assertFalse(StringUtils.isEmpty(" c"));
        Assert.assertFalse(StringUtils.isEmpty(" cd"));
        Assert.assertFalse(StringUtils.isEmpty("cde"));
    }

    @Test
    public void isNotEmpty() {
        Assert.assertFalse(StringUtils.isNotEmpty(null));
        Assert.assertFalse(StringUtils.isNotEmpty(""));
        Assert.assertFalse(StringUtils.isNotEmpty("    \n"));
        Assert.assertTrue(StringUtils.isNotEmpty("a"));
        Assert.assertTrue(StringUtils.isNotEmpty("b "));
        Assert.assertTrue(StringUtils.isNotEmpty(" c"));
        Assert.assertTrue(StringUtils.isNotEmpty(" cd"));
        Assert.assertTrue(StringUtils.isNotEmpty("cde"));
    }

    @Test
    public void equalsWithoutNPE() {
        Assert.assertTrue(StringUtils.equalsWithoutNPE(null, null));
        Assert.assertTrue(StringUtils.equalsWithoutNPE("hello world", "hello world"));
        Assert.assertFalse(StringUtils.equalsWithoutNPE(null, "1"));
        Assert.assertFalse(StringUtils.equalsWithoutNPE("2", null));
        Assert.assertFalse(StringUtils.equalsWithoutNPE("i know who you are", "you know who i am"));
    }
}