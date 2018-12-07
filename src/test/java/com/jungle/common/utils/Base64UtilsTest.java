package com.jungle.common.utils;

import org.junit.Test;

import java.io.IOException;
import java.nio.charset.Charset;

import static org.junit.Assert.*;

public class Base64UtilsTest {

    @Test
    public void encode() {
        assertEquals("aGVsbG8s5p2O5aW95ZGA77yB",Base64Utils.encode("hello,李好呀！"));
        assertEquals("aGVsbG8s5p2O5aW95ZGA77yB",Base64Utils.encode("hello,李好呀！",Charset.forName("UTF-8")));
        assertEquals("aGks55CG5aW95ZGA77yB",Base64Utils.encode("hi,理好呀！",Charset.forName("UTF-8")));
    }

    @Test
    public void decode() throws IOException {
        assertEquals("hello,李好呀！",Base64Utils.decode("aGVsbG8s5p2O5aW95ZGA77yB"));
        assertEquals("hello,李好呀！",Base64Utils.decode("aGVsbG8s5p2O5aW95ZGA77yB",Charset.forName("UTF-8")));
        assertEquals("hi,理好呀！",Base64Utils.decode("aGks55CG5aW95ZGA77yB"));
        assertEquals("hi,理好呀！",Base64Utils.decode("aGks55CG5aW95ZGA77yB",Charset.forName("UTF-8")));
    }
}