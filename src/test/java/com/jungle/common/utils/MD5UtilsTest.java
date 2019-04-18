package com.jungle.common.utils;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.nio.charset.Charset;

public class MD5UtilsTest {

    @Test
    public void encodeMD5() {
        Assert.assertEquals("c4743b8a2779763242419fe3d8cca66b", MD5Utils.digest("Team make"));
        Assert.assertEquals("c4743b8a2779763242419fe3d8cca66b", MD5Utils.digest("Team make", false));
        Assert.assertEquals("c4743b8a2779763242419fe3d8cca66b", MD5Utils.digest("Team make", false, Charset.forName("UTF-8")));
        Assert.assertEquals("C4743B8A2779763242419FE3D8CCA66B", MD5Utils.digest("Team make", true, Charset.forName("UTF-8")));
        Assert.assertEquals("26a6fe67fc70751230714c0a232ffac3",MD5Utils.digest(new File("D:\\Postman-win64-7.0.7-Setup.exe")));

    }

}