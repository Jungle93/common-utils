package com.jungle.common.utils;

import org.junit.Test;

import java.nio.charset.Charset;

/**
 * @author jungle
 * @version V1.0
 * @date 2019/8/1
 * 描述：SHA256工具类的测试类
 * copyright © 2019- .com
 */
public class SHA256UtilsTest {


    @Test
    public void digest_success() {
        org.junit.Assert.assertEquals("a03e95dcf71443458781e87c156f3e08319ca77618be22d4dd9f86958d643c52", SHA256Utils.digest("Team make"));
        org.junit.Assert.assertEquals("a03e95dcf71443458781e87c156f3e08319ca77618be22d4dd9f86958d643c52", SHA256Utils.digest("Team make".getBytes(),false));
        org.junit.Assert.assertEquals("a03e95dcf71443458781e87c156f3e08319ca77618be22d4dd9f86958d643c52", SHA256Utils.digest("Team make", false));
        org.junit.Assert.assertEquals("a03e95dcf71443458781e87c156f3e08319ca77618be22d4dd9f86958d643c52", SHA256Utils.digest("Team make", false, Charset.forName("UTF-8")));
        org.junit.Assert.assertEquals("A03E95DCF71443458781E87C156F3E08319CA77618BE22D4DD9F86958D643C52", SHA256Utils.digest("Team make", true, Charset.forName("UTF-8")));
    }

}
