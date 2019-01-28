package com.jungle.common.utils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @author jungle
 * @version V1.0
 * @date 2019/1/22 17:51
 * @Title: AESUtils.java
 * @Package com.jungle.common.utils
 */
public class AESUtils {

    /**
     * no-args constructor.
     */
    private AESUtils() {/**/ }

    /**
     * 使用DES算法和密码加密目标字符串。
     *
     * @param dataSource 待加密数据
     * @param password   密码数据
     * @return byte[]
     * @throws InvalidKeyException       无效key
     * @throws NoSuchPaddingException    填充方式不存在
     * @throws NoSuchAlgorithmException  无此算法
     * @throws BadPaddingException       坏的填充方式
     * @throws IllegalBlockSizeException 非法块大小
     */
    public static byte[] encrypt(byte[] dataSource, String password) throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(password.getBytes());
        keyGenerator.init(128, secureRandom);
        SecretKey secretKey = keyGenerator.generateKey();
        byte[] enCodeFormat = secretKey.getEncoded();
        SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(dataSource);
    }

    /**
     * 使用DES算法和密码解密所给密文。
     *
     * @param encryptText 加密密文
     * @param password    密码数据
     * @return byte[]
     * @throws InvalidKeyException       无效key
     * @throws NoSuchPaddingException    填充方式不存在
     * @throws NoSuchAlgorithmException  无此算法
     * @throws BadPaddingException       坏的填充方式
     * @throws IllegalBlockSizeException 非法块大小
     */
    public static byte[] decrypt(byte[] encryptText, String password) throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(password.getBytes());
        keyGenerator.init(128, secureRandom);
        SecretKey secretKey = keyGenerator.generateKey();
        byte[] enCodeFormat = secretKey.getEncoded();
        SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
        Cipher cipher = Cipher.getInstance("AES");// 创建密码器
        cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
        return cipher.doFinal(encryptText); // 解密
    }
}
