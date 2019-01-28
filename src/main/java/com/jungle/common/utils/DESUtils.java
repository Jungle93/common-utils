package com.jungle.common.utils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

/**
 * @author jungle
 * @version V1.0
 * @date 2019/1/21 10:25
 * @Title: DESUtils.java
 * @Package com.jungle.common.utils
 * @Description: DES对称加密
 */
public final class DESUtils {

    private static SecureRandom secureRandom;
    private static SecretKeyFactory keyFactory;

    static {
        try {
            secureRandom = SecureRandom.getInstance("SHA1PRNG");
            keyFactory = SecretKeyFactory.getInstance("DES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /**
     * no-args constructor.
     */
    private DESUtils() {/**/ }

    /**
     * 使用DES算法和密码加密目标字符串。
     *
     * @param dataSource 待加密数据
     * @param password   密码数据
     * @return byte[]
     * @throws InvalidKeyException       无效key
     * @throws InvalidKeySpecException   无效key标准
     * @throws NoSuchPaddingException    填充方式不存在
     * @throws NoSuchAlgorithmException  无此算法
     * @throws BadPaddingException       坏的填充方式
     * @throws IllegalBlockSizeException 非法块大小
     */
    public static byte[] encrypt(byte[] dataSource, String password) throws InvalidKeyException, InvalidKeySpecException, NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException {
        DESKeySpec desKey = new DESKeySpec(password.getBytes());
        SecretKey secretKey = keyFactory.generateSecret(desKey);
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, secureRandom);
        return cipher.doFinal(dataSource);
    }

    /**
     * 使用DES算法和密码解密所给密文。
     *
     * @param encryptText 加密密文
     * @param password    密码数据
     * @return byte[]
     * @throws InvalidKeyException       无效key
     * @throws InvalidKeySpecException   无效key标准
     * @throws NoSuchPaddingException    填充方式不存在
     * @throws NoSuchAlgorithmException  无此算法
     * @throws BadPaddingException       坏的填充方式
     * @throws IllegalBlockSizeException 非法块大小
     */
    public static byte[] decrypt(byte[] encryptText, String password) throws InvalidKeyException, InvalidKeySpecException, NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException {
        DESKeySpec desKey = new DESKeySpec(password.getBytes());
        SecretKey secretKey = keyFactory.generateSecret(desKey);
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey, secureRandom);
        return cipher.doFinal(encryptText);
    }
}
