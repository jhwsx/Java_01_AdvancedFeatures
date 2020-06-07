package com.java.advanced.features.io.practice;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class AES {
    // 长度是有要求的
    public static final String DEFAULT_PWD = "abcdefghijklmnop";

    private static final String ALGORITHM_STR = "AES/ECB/PKCS5Padding";

    private static Cipher encryptCipher;
    private static Cipher decryptCipher;

    /**
     * 初始化
     * @param password
     */
    public static void init(String password) {
        try {
            encryptCipher = Cipher.getInstance(ALGORITHM_STR);
            decryptCipher = Cipher.getInstance(ALGORITHM_STR);
            byte[] pwdBytes = password.getBytes();
            SecretKeySpec key = new SecretKeySpec(pwdBytes, "AES");
            encryptCipher.init(Cipher.ENCRYPT_MODE, key);
            decryptCipher.init(Cipher.DECRYPT_MODE, key);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
    }

    /**
     * 加密
     * @param content
     * @return
     */
    public static byte[] encrypt(byte[] content) {
        byte[] result = null;
        try {
            result = encryptCipher.doFinal(content);
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 解密
     * @param content
     * @return
     */
    public static byte[] decrypt(byte[] content) {
        byte[] result = null;
        try {
            result = decryptCipher.doFinal(content);
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
