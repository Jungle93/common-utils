package com.jungle.common.utils;


import org.junit.Assert;
import org.junit.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class AESUtilsTest {

    @Test
    public void testEncryptDecrypt() {

        try {
            String clearText = "Hello AES from jungle";
            String password = "ass888HouseHold**_&";
            byte[] encryptBytes = AESUtils.encrypt(clearText.getBytes(), password);
            String result = new String(AESUtils.decrypt(encryptBytes, password));
            Assert.assertEquals(clearText,result);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
    }

}