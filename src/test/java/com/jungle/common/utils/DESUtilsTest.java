package com.jungle.common.utils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Random;

public class DESUtilsTest {

    /**
     * 日志。
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DESUtilsTest.class);

    private String password;

    @Before
    public void setUp(){
        StringBuffer stringBuffer = new StringBuffer();
        String keyPass = "123456789QAWZSEXDRCFTVGYBHUNJIMKOLP!@#$%^&*()_+qazwsxedcrfvtgbyhnujmikolp";
        Random random = new Random();
        for (int i = 0; i < 8; i++) {
            stringBuffer.append(keyPass.charAt(random.nextInt(keyPass.length())));

        }
        password = stringBuffer.toString();
        LOGGER.info("random password:{}", password);
    }

    @Test
    public void doDESTest() throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidKeySpecException {
        String plainText = "Hello From jungle!2019-01-21 10:48:56";
        byte[] encryptBytes = DESUtils.encrypt(plainText.getBytes(), password);
        LOGGER.info(Base64Utils.encode(encryptBytes));
        byte[] plainBytes = DESUtils.decrypt(encryptBytes, password);
        String result = new String(plainBytes);
        LOGGER.info(result);
        Assert.assertEquals(plainText,result);
    }
}