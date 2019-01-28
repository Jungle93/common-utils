package com.jungle.common.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author jungle
 * @version V1.0
 * @date 2018/12/4 17:58
 * @Title: Base64Utils.java
 * @Package com.jungle.common.utils
 * @Description: 对称加密DES算法工具
 * copyright © 2018- github.com
 */
public final class Base64Utils {

    private static final BASE64Encoder encoder = new BASE64Encoder();
    private static final BASE64Decoder decoder = new BASE64Decoder();
    /**
     * no-args constructor.
     */
    private Base64Utils() {/**/ }

    public static String encode(String source) {
        return encode(source, Charset.forName("UTF-8"));
    }

    public static String encode(String source, Charset charset) {
        return encoder.encode(source.getBytes(charset));
    }

    public static String decode(String source) throws IOException {
        return decode(source, Charset.forName("UTF-8"));
    }

    public static String decode(String source, Charset charset) throws IOException {
        return new String(decoder.decodeBuffer(source), charset);
    }

    public static String encode(byte[] bytes){
        return encoder.encode(bytes);
    }
}
