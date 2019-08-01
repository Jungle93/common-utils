package com.jungle.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author jungle
 * @version V1.0
 * @date 2019/8/1
 * 描述：用标准库实现的SHA256工具类
 * copyright © 2019- .com
 */
public class SHA256Utils {

    /**
     * 日志。
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SHA256Utils.class);


    /**
     * 16进制小写字符表。
     */
    private static final char[] DIGITS_LOWER;
    /**
     * 16进制大写字符表。
     */
    private static final char[] DIGITS_UPPER;

    static {
        DIGITS_LOWER = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        DIGITS_UPPER = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    }


    /**
     * 以16进制对字节低4位和高4位进行编码
     *
     * @param data        目标数据
     * @param digitsTable 16长度的字符表
     *
     * @return digitsTable(char[])
     */
    private static char[] encodeHex(byte[] data, char[] digitsTable) {

        int length = data.length;
        char[] out = new char[length * 2];
        for (int j = 0, i = 0; i < 32; i++) {
            out[j++] = digitsTable[data[i] >>> 4 & 0xf];
            out[j++] = digitsTable[data[i] & 0xf];
        }
        return out;
    }

    /**
     * 获取所给字符串内容MD5算法的消息摘要。
     *
     * @param source 目标字符串
     *
     * @return SHA256(source)
     */
    public static String digest(String source) {

        return digest(source, false);
    }

    /**
     * 以给定的编码获取字符串内容MD5算法的消息摘要。
     *
     * @param source    目标字符串
     * @param upperFlag 大写标识
     *
     * @return SHA256(source)
     */
    public static String digest(String source, boolean upperFlag) {

        return digest(source, upperFlag, Charset.forName("UTF-8"));
    }

    /**
     * 以给定的编码获取字符串内容SHA256算法的消息摘要。
     *
     * @param source    目标字符串
     * @param upperFlag 大写标识
     * @param charset   {@link Charset}
     *
     * @return SHA256(source)
     */
    public static String digest(String source, boolean upperFlag, Charset charset) {

        return digest(source.getBytes(charset), upperFlag);
    }

    /**
     * 以给定的编码获取字符串内容SHA256算法的消息摘要。
     *
     * @param bytes     字节数据
     * @param upperFlag 大写标识
     *
     * @return SHA256(source)
     */
    public static String digest(byte[] bytes, boolean upperFlag) {

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] digestBytes = messageDigest.digest(bytes);
            return new String(encodeHex(digestBytes, upperFlag ? DIGITS_UPPER : DIGITS_LOWER));
        } catch (NoSuchAlgorithmException e) {
            LOGGER.warn("获取消息摘要失败：", e);
            return null;
        }
    }

}
