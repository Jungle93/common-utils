package com.jungle.common.utils;

import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.util.Date;

import static org.junit.Assert.*;

public class DateTimeUtilsTest {

    @Test
    public void parse(){
        Assert.assertNotNull(DateTimeUtils.parse("2018-12-11","yyyy-MM-dd"));
        Assert.assertNotNull(DateTimeUtils.parse("2018-12-11",DateTimeUtils.SDF_YYYY_MM_DD));
        Assert.assertNotNull(DateTimeUtils.parse("2018-12-11 12:00:01",DateTimeUtils.SDF_YYYY_MM_DD_HH_MM_SS));
        Assert.assertNotNull(DateTimeUtils.parse("2018-12-11 12:00:01.555",DateTimeUtils.SDF_YYYY_MM_DD_HH_MM_SS_SSS));
        Assert.assertNull(DateTimeUtils.parse("2018-12-11","yyyy-MM-dd HH:mm:ss"));
        Assert.assertNull(DateTimeUtils.parse("2018-12-11",DateTimeUtils.SDF_YYYY_MM_DD_HH_MM_SS));
        Assert.assertNull(DateTimeUtils.parse("2018-12-11",DateTimeUtils.SDF_YYYY_MM_DD_HH_MM_SS_SSS));
    }

    @Test
    public void format() {
        Assert.assertNotNull(DateTimeUtils.format(new Date(),"yyyy-MM-dd"));
        Assert.assertNotNull(DateTimeUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
    }

    @Test
    public void diffInDays() {
        Assert.assertEquals(16,DateTimeUtils.diffInDays(
                DateTimeUtils.parse("2018-11-13 00:00:00",DateTimeUtils.SDF_YYYY_MM_DD_HH_MM_SS),
                DateTimeUtils.parse("2018-11-29 23:59:59",DateTimeUtils.SDF_YYYY_MM_DD_HH_MM_SS)
        ));
    }

    @Test
    public void date2LocalDate() {
        Assert.assertNotNull(DateTimeUtils.date2LocalDate(new Date()));
    }
}