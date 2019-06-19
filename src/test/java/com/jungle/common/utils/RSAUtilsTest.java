package com.jungle.common.utils;

import org.junit.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class RSAUtilsTest {

    private static final Logger LOGGER = Logger.getLogger(RSAUtilsTest.class.getName());

    @Test
    public void generateKeyPair() throws NoSuchAlgorithmException {

        Map<String, String> keyPair = RSAUtils.generateKeyPair();

        assertNotNull(keyPair);

        String publicKey = keyPair.get(RSAUtils.PUBLIC_KEY);
        String privateKey = keyPair.get(RSAUtils.PRIVATE_KEY);

        assertNotNull(publicKey);
        assertNotNull(privateKey);

        LOGGER.info("public key:" + publicKey);
        LOGGER.info("private key:" + privateKey);
    }

    @Test
    public void signature() throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, SignatureException {

        String privateKey = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBALXEhOfRjFjlf0amn335/pV15uGm0jPDhKYpN0Qgxd+2iVK+Rv/qguwhGGYu/r3Z7ck/9xmXdpm/Fq1ncwmMEJ4u1aaZ/7Yd+wfom1ePIK7wkrZnhTYNAlaLNt53UkZjJIdeCTy6pRW12SdevVNjnK7dLm2COq1MjCphowM2ld1vAgMBAAECgYEAmce3gcEhchxHv+g8S4JzxtNHEGXotO6XjVSVc0ge4JQ4Pg7JVr57IXcmg10DHHAB4yAA2F/ycUTf1LkO9GbRrvSgT9HQBBh/R8fwWOYGrcdJYCangp3aNsBFLv+c4n9i582mhO/zq0gwc92ehirQ+b0mCon3nuJCYgXdkCpLeQECQQDnSnu5HM+hEikUm3Cct07shIknhc8x8ZmTM6dKQgf99BvZy+LYvqkkEvHgzg+hK8zfcN4gSAm8yvnGF85eHmtBAkEAyS+izXVT378MIn/BUNe00FHr6JjxPhbW1OLML07SzmWyJX7DdtX7RC4vl0feL6+ED2pvZ41fZSkenkKzA2OMrwJBAIWRfwe0+rbi/PRYm9z1A1AVbft0C+akxnK61rZMkb73tQm+878FxDn/8s1HUsFZwROcY7qyv2TWtw3N+7Bg0sECQQCCqD8w89uu2eJFnXaszAoPqMRu4LT7v9VxmIFG0Ag0JvOVSkgjK0DtvJkHxJLVoUVAxbzoIXYsupG58lNUBhjdAkBwGgWQAckSf8I2mJixDhZniSEtBWtXR6W/3m/DfBB2zURXdOJw6Y1U7/1BiRIQjz98npbvvcPxzMnE7QzqCWM+";
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC1xITn0YxY5X9Gpp99+f6VdebhptIzw4SmKTdEIMXftolSvkb/6oLsIRhmLv692e3JP/cZl3aZvxatZ3MJjBCeLtWmmf+2HfsH6JtXjyCu8JK2Z4U2DQJWizbed1JGYySHXgk8uqUVtdknXr1TY5yu3S5tgjqtTIwqYaMDNpXdbwIDAQAB";
        String signature1 = RSAUtils.signature("Hello from jungle".getBytes(), privateKey);
        assertNotNull(signature1);
        String signature2 = RSAUtils.signature(RSAUtils.encode("Hello from jungle".getBytes()), privateKey);
        assertNotNull(signature2);
        assertEquals(signature1,signature2);

        assertTrue(RSAUtils.signatureVerify(publicKey, "Hello from jungle".getBytes(), RSAUtils.decode(signature1)));
        assertTrue(RSAUtils.signatureVerify(publicKey, "Hello from jungle", signature1));

    }

    /**
     * public key [MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC1xITn0YxY5X9Gpp99+f6VdebhptIzw4SmKTdEIMXftolSvkb/6oLsIRhmLv692e3JP/cZl3aZvxatZ3MJjBCeLtWmmf+2HfsH6JtXjyCu8JK2Z4U2DQJWizbed1JGYySHXgk8uqUVtdknXr1TY5yu3S5tgjqtTIwqYaMDNpXdbwIDAQAB]
     * private key [MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBALXEhOfRjFjlf0amn335/pV15uGm0jPDhKYpN0Qgxd+2iVK+Rv/qguwhGGYu/r3Z7ck/9xmXdpm/Fq1ncwmMEJ4u1aaZ/7Yd+wfom1ePIK7wkrZnhTYNAlaLNt53UkZjJIdeCTy6pRW12SdevVNjnK7dLm2COq1MjCphowM2ld1vAgMBAAECgYEAmce3gcEhchxHv+g8S4JzxtNHEGXotO6XjVSVc0ge4JQ4Pg7JVr57IXcmg10DHHAB4yAA2F/ycUTf1LkO9GbRrvSgT9HQBBh/R8fwWOYGrcdJYCangp3aNsBFLv+c4n9i582mhO/zq0gwc92ehirQ+b0mCon3nuJCYgXdkCpLeQECQQDnSnu5HM+hEikUm3Cct07shIknhc8x8ZmTM6dKQgf99BvZy+LYvqkkEvHgzg+hK8zfcN4gSAm8yvnGF85eHmtBAkEAyS+izXVT378MIn/BUNe00FHr6JjxPhbW1OLML07SzmWyJX7DdtX7RC4vl0feL6+ED2pvZ41fZSkenkKzA2OMrwJBAIWRfwe0+rbi/PRYm9z1A1AVbft0C+akxnK61rZMkb73tQm+878FxDn/8s1HUsFZwROcY7qyv2TWtw3N+7Bg0sECQQCCqD8w89uu2eJFnXaszAoPqMRu4LT7v9VxmIFG0Ag0JvOVSkgjK0DtvJkHxJLVoUVAxbzoIXYsupG58lNUBhjdAkBwGgWQAckSf8I2mJixDhZniSEtBWtXR6W/3m/DfBB2zURXdOJw6Y1U7/1BiRIQjz98npbvvcPxzMnE7QzqCWM+]
     */
    @Test
    public void encryptByPublicKey() throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, IOException, BadPaddingException, InvalidKeyException, InvalidKeySpecException {

        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCbZXeDIV17etLYblpFIz3LJ+V1iZOB5Lpa2s2E29rwPVeIsQlUkxJ2u1M1PJR44dilLriZgxnxLwTu8gycCDZf6Cha7ShLL7S0t3G7EJiA5VovsCJTvH4+nF8dM01954/G8X0YYYwohl32tlz4ELtlcFn6j3oewVxewp5g3K2NWwIDAQAB";


        byte[] encryptedBytes =
                RSAUtils.encryptByPublicKey("{ \"feature\":\"{\\\"Sex\\\":0,\\\"MinIncome\\\":0.0,\\\"EduStatus\\\":2,\\\"EduLevel\\\":1,\\\"Birth\\\":\\\"1995\\\",\\\"MaxIncome\\\":5000.0,\\\"EduForm\\\":0}\", \"ipfs\":[\"ipfs1\", \"ipfs2\"] }".getBytes(),
                        publicKey);
        String encryptBase64 = RSAUtils.encode(encryptedBytes);
        assertEquals("jurcmsGRiu7obFLrwmsNPsIABG1xXUwKAo9157oRKspHUswNEXBDL0IOFzPJfF1RoxGp070FlDv7LT+QIBt8fH0btH8m+TuiWx0b5xLy5EHmpckilmMwIsivTxrmUSV1HlsoYwLz+dJac31cnYWztcMqhY0TLNRMAvreWuDzS/g=", encryptBase64);
    }

    @Test
    public void decryptByPrivateKey() throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, IOException, BadPaddingException, InvalidKeyException, InvalidKeySpecException {

        String privateKey = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBALXEhOfRjFjlf0amn335/pV15uGm0jPDhKYpN0Qgxd+2iVK+Rv/qguwhGGYu/r3Z7ck/9xmXdpm/Fq1ncwmMEJ4u1aaZ/7Yd+wfom1ePIK7wkrZnhTYNAlaLNt53UkZjJIdeCTy6pRW12SdevVNjnK7dLm2COq1MjCphowM2ld1vAgMBAAECgYEAmce3gcEhchxHv+g8S4JzxtNHEGXotO6XjVSVc0ge4JQ4Pg7JVr57IXcmg10DHHAB4yAA2F/ycUTf1LkO9GbRrvSgT9HQBBh/R8fwWOYGrcdJYCangp3aNsBFLv+c4n9i582mhO/zq0gwc92ehirQ+b0mCon3nuJCYgXdkCpLeQECQQDnSnu5HM+hEikUm3Cct07shIknhc8x8ZmTM6dKQgf99BvZy+LYvqkkEvHgzg+hK8zfcN4gSAm8yvnGF85eHmtBAkEAyS+izXVT378MIn/BUNe00FHr6JjxPhbW1OLML07SzmWyJX7DdtX7RC4vl0feL6+ED2pvZ41fZSkenkKzA2OMrwJBAIWRfwe0+rbi/PRYm9z1A1AVbft0C+akxnK61rZMkb73tQm+878FxDn/8s1HUsFZwROcY7qyv2TWtw3N+7Bg0sECQQCCqD8w89uu2eJFnXaszAoPqMRu4LT7v9VxmIFG0Ag0JvOVSkgjK0DtvJkHxJLVoUVAxbzoIXYsupG58lNUBhjdAkBwGgWQAckSf8I2mJixDhZniSEtBWtXR6W/3m/DfBB2zURXdOJw6Y1U7/1BiRIQjz98npbvvcPxzMnE7QzqCWM+";
        byte[] dataBytes = RSAUtils.decode("jurcmsGRiu7obFLrwmsNPsIABG1xXUwKAo9157oRKspHUswNEXBDL0IOFzPJfF1RoxGp070FlDv7LT+QIBt8fH0btH8m+TuiWx0b5xLy5EHmpckilmMwIsivTxrmUSV1HlsoYwLz+dJac31cnYWztcMqhY0TLNRMAvreWuDzS/g=");
        String clearText = new String(RSAUtils.decryptByPrivateKey(dataBytes, privateKey));
        assertEquals("Hello from jungle!",
                clearText);
    }

    @Test
    public void decryptByPublicKey() throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, IOException, BadPaddingException, InvalidKeyException, InvalidKeySpecException {

        byte[] bytes = RSAUtils.decode("TU/3intjAKkm7gslX1vh8Lk3yBepRvSJEhLh7xIMpdalXFqndv7+uHNOCJidYaDOeUfIrVGnoa8G8yMPTWeeFX+++kFgnZYKmxHp2IPPXYKhkOzjavNvOXxy/N7tAGlTlB8bhkZ9ot3Cig2V3wilG8ez7tTwVKbv6teXmyKrt0c=");
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC1xITn0YxY5X9Gpp99+f6VdebhptIzw4SmKTdEIMXftolSvkb/6oLsIRhmLv692e3JP/cZl3aZvxatZ3MJjBCeLtWmmf+2HfsH6JtXjyCu8JK2Z4U2DQJWizbed1JGYySHXgk8uqUVtdknXr1TY5yu3S5tgjqtTIwqYaMDNpXdbwIDAQAB";
        byte[] decryptBytes = RSAUtils.decryptByPublicKey(bytes, publicKey);
        String clearText = new String(decryptBytes, Charset.forName("utf-8"));
        assertEquals("Hello from jungle!", clearText);

    }

    @Test
    public void encryptByPrivateKey() throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, IOException, BadPaddingException, InvalidKeyException, InvalidKeySpecException {

        String privateKey = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBALXEhOfRjFjlf0amn335/pV15uGm0jPDhKYpN0Qgxd+2iVK+Rv/qguwhGGYu/r3Z7ck/9xmXdpm/Fq1ncwmMEJ4u1aaZ/7Yd+wfom1ePIK7wkrZnhTYNAlaLNt53UkZjJIdeCTy6pRW12SdevVNjnK7dLm2COq1MjCphowM2ld1vAgMBAAECgYEAmce3gcEhchxHv+g8S4JzxtNHEGXotO6XjVSVc0ge4JQ4Pg7JVr57IXcmg10DHHAB4yAA2F/ycUTf1LkO9GbRrvSgT9HQBBh/R8fwWOYGrcdJYCangp3aNsBFLv+c4n9i582mhO/zq0gwc92ehirQ+b0mCon3nuJCYgXdkCpLeQECQQDnSnu5HM+hEikUm3Cct07shIknhc8x8ZmTM6dKQgf99BvZy+LYvqkkEvHgzg+hK8zfcN4gSAm8yvnGF85eHmtBAkEAyS+izXVT378MIn/BUNe00FHr6JjxPhbW1OLML07SzmWyJX7DdtX7RC4vl0feL6+ED2pvZ41fZSkenkKzA2OMrwJBAIWRfwe0+rbi/PRYm9z1A1AVbft0C+akxnK61rZMkb73tQm+878FxDn/8s1HUsFZwROcY7qyv2TWtw3N+7Bg0sECQQCCqD8w89uu2eJFnXaszAoPqMRu4LT7v9VxmIFG0Ag0JvOVSkgjK0DtvJkHxJLVoUVAxbzoIXYsupG58lNUBhjdAkBwGgWQAckSf8I2mJixDhZniSEtBWtXR6W/3m/DfBB2zURXdOJw6Y1U7/1BiRIQjz98npbvvcPxzMnE7QzqCWM+";
        byte[] bytes = RSAUtils.encryptByPrivateKey("Hello from jungle!".getBytes(), privateKey);
        String encryptText = RSAUtils.encode(bytes);
        assertEquals("TU/3intjAKkm7gslX1vh8Lk3yBepRvSJEhLh7xIMpdalXFqndv7+uHNOCJidYaDOeUfIrVGnoa8G8yMPTWeeFX+++kFgnZYKmxHp2IPPXYKhkOzjavNvOXxy/N7tAGlTlB8bhkZ9ot3Cig2V3wilG8ez7tTwVKbv6teXmyKrt0c=", encryptText);
    }
}