package com.jungle.common.utils;

import org.junit.Assert;
import org.junit.Test;

import java.io.Serializable;
import java.util.Date;

public class CollectionUtilsTest {

    @Test
    public void map() {
        Assert.assertNotNull(CollectionUtils.map("1", "2"));
        Assert.assertNotNull(CollectionUtils.map("1", "2", "3", "4"));
        Assert.assertNotNull(CollectionUtils.map("1", "2", "3", "4",
                "5", "6"));
        Assert.assertNotNull(CollectionUtils.map("1", "2", "3", "4",
                "5", "6", "7", "8"));
        Assert.assertNotNull(CollectionUtils.map("1", "2", "3", "4",
                "5", "6", "7", "8", "9", "10"));
        Assert.assertNotNull(CollectionUtils.map("1", "2", "3", "4",
                "5", "6", "7", "8", "9", "10", "11", "12"));
    }

    @Test
    public void array() {
        Serializable[] array = CollectionUtils.array("1", 2, "3", "4");
        Assert.assertEquals(4, array.length);

        String[] strings = CollectionUtils.array("1", "2", "3", "4", "5");
        Assert.assertEquals(5, strings.length);

        Object[] objects = CollectionUtils.array("1", new Date(), "3", 4, new Object(),new Object());
        Assert.assertEquals(6, objects.length);
    }
}