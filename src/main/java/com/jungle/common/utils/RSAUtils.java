package com.jungle.common.utils;


import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public final class RSAUtils {

    /**
     * 加密算法RSA。
     */
    private static final String KEY_ALGORITHM = "RSA";
    /**
     * 获取公钥的key。
     */
    public static final String PUBLIC_KEY = "RSAPublicKey";
    /**
     * 获取私钥的key。
     */
    public static final String PRIVATE_KEY = "RSAPrivateKey";
    /**
     * 密钥长度多少位。
     */
    private static final int KEY_BIT_SIZE = 1024;
    /**
     * RSA最大加密明文块大小。
     */
    private static final int MAX_ENCRYPT_BLOCK = KEY_BIT_SIZE / 8 - 11;
    /**
     * RSA最大解密密文块大小。
     */
    private static final int MAX_DECRYPT_BLOCK = KEY_BIT_SIZE / 8;

    /**
     * 私有化构造器。
     */
    private RSAUtils() {/**/ }

    /**
     * 生成公私钥配对。
     *
     * @return {@link Map}
     * @throws NoSuchAlgorithmException 没有这个加密算法
     */
    public static Map<String, String> generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGen.initialize(KEY_BIT_SIZE);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        Map<String, String> result = new HashMap<String, String>(2);
        result.put(PUBLIC_KEY, encode(keyPair.getPublic().getEncoded()));
        result.put(PRIVATE_KEY, encode(keyPair.getPrivate().getEncoded()));
        return result;
    }

    /**
     * 二进制数据编码为BASE64字符串。
     */
    public static String encode(byte[] bytes) {
        return new String(Base64.getEncoder().encode(bytes));
    }

    /**
     * BASE64字符串解码为二进制数据。
     */
    public static byte[] decode(String base64) {
        return Base64.getDecoder().decode(base64.getBytes());
    }

    /**
     * 获取公钥。
     *
     * @param publicKeyStr 公钥字符串
     * @return {@link PublicKey}
     * @throws InvalidKeySpecException  非法Key标准
     * @throws NoSuchAlgorithmException 没有这个算法
     */
    private static PublicKey getPublicKey(String publicKeyStr) throws InvalidKeySpecException, NoSuchAlgorithmException {
        byte[] keyBytes = decode(publicKeyStr);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        return keyFactory.generatePublic(x509KeySpec);
    }

    /**
     * 公钥加密。
     *
     * @param data      待加密数据
     * @param publicKey 公钥字符串
     * @return 加密后数据
     * @throws NoSuchAlgorithmException  没有这个算法
     * @throws InvalidKeySpecException   非法Key标准
     * @throws NoSuchPaddingException    没有此填充方式
     * @throws InvalidKeyException       非法key
     * @throws BadPaddingException       非法填充
     * @throws IllegalBlockSizeException 非法块大小
     * @throws IOException               IO异常
     */
    public static byte[] encryptByPublicKey(byte[] data, String publicKey) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, IOException {
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(publicKey));
        return splitCipher(cipher, Cipher.ENCRYPT_MODE, data);
    }

    /**
     * 公钥解密。
     *
     * @param encryptedData 待解密数据
     * @param publicKey     公钥字符串
     * @return 解密后数据
     * @throws NoSuchAlgorithmException  没有这个算法
     * @throws InvalidKeySpecException   非法Key标准
     * @throws NoSuchPaddingException    没有此填充方式
     * @throws InvalidKeyException       非法key
     * @throws BadPaddingException       非法填充
     * @throws IllegalBlockSizeException 非法块大小
     * @throws IOException               IO异常
     */
    public static byte[] decryptByPublicKey(byte[] encryptedData, String publicKey) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, IOException {
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, getPublicKey(publicKey));
        return splitCipher(cipher, Cipher.DECRYPT_MODE, encryptedData);
    }

    /**
     * 获取私钥。
     *
     * @param privateKeyStr 私钥字符串
     * @return {@link PrivateKey}
     * @throws NoSuchAlgorithmException 没有这个算法
     * @throws InvalidKeySpecException  非法Key标准
     */
    private static PrivateKey getPrivateKey(String privateKeyStr) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] keyBytes = decode(privateKeyStr);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        return keyFactory.generatePrivate(pkcs8KeySpec);
    }

    /**
     * 私钥解密。
     *
     * @param encryptedData 待解密数据
     * @param privateKey    私钥字符串
     * @return 解密后数据
     * @throws NoSuchAlgorithmException  没有这个算法
     * @throws InvalidKeySpecException   非法Key标准
     * @throws NoSuchPaddingException    没有此填充方式
     * @throws InvalidKeyException       非法key
     * @throws BadPaddingException       非法填充
     * @throws IllegalBlockSizeException 非法块大小
     * @throws IOException               IO异常
     */
    public static byte[] decryptByPrivateKey(byte[] encryptedData, String privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, IOException {
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, getPrivateKey(privateKey));
        return splitCipher(cipher, Cipher.DECRYPT_MODE, encryptedData);
    }

    /**
     * 私钥加密。
     *
     * @param data       待加密数据
     * @param privateKey 私钥字符串
     * @return 加密后数据
     * @throws NoSuchAlgorithmException  没有这个算法
     * @throws InvalidKeySpecException   非法Key标准
     * @throws NoSuchPaddingException    没有此填充方式
     * @throws InvalidKeyException       非法key
     * @throws BadPaddingException       非法填充
     * @throws IllegalBlockSizeException 非法块大小
     * @throws IOException               IO异常
     */
    public static byte[] encryptByPrivateKey(byte[] data, String privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException, IOException, InvalidKeyException {
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, getPrivateKey(privateKey));
        return splitCipher(cipher, Cipher.ENCRYPT_MODE, data);
    }

    /**
     * 分段加密。
     *
     * @param cipher 加密器
     * @param mode   方式：加密或者解密{@link Cipher#DECRYPT_MODE} | {@link Cipher#ENCRYPT_MODE}
     * @param data   数据
     * @return byte[]
     * @throws BadPaddingException       非法填充
     * @throws IllegalBlockSizeException 非法块大小
     * @throws IOException               IO异常
     */
    private static byte[] splitCipher(Cipher cipher, int mode, byte[] data) throws BadPaddingException, IllegalBlockSizeException, IOException {
        int inputLength = data.length;
        int offSet = 0;
        int index = 0;
        byte[] buf;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int blockSize = 0;
        if (mode == Cipher.DECRYPT_MODE) {
            blockSize = MAX_DECRYPT_BLOCK;
        } else if (mode == Cipher.ENCRYPT_MODE) {
            blockSize = MAX_ENCRYPT_BLOCK;
        }
        while (inputLength - offSet > 0) {
            if (inputLength - offSet > blockSize) {
                buf = cipher.doFinal(data, offSet, blockSize);
            } else {
                buf = cipher.doFinal(data, offSet, inputLength - offSet);
            }
            out.write(buf, 0, buf.length);
            index++;
            offSet = index * blockSize;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;
    }

}