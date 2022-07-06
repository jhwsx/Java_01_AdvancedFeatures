package com.java.advanced.features.net.security;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

/**
 * @author wangzhichao
 * @since 2022/4/21
 */
public class AESSymmetricalEncryption {
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/ECB/PKCS5Padding";
    public static byte[] encrypt(byte[] input, String key)
            throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException {
        // 1，生成对称密钥
        SecretKey secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
        // 2，创建指定转换的密码器
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        // 3，用密钥初始化此 Cipher
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        // 4，加密
        return cipher.doFinal(input);
    }

    public static byte[] decrypt(byte[] input, String key)
            throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException {
        // 1，生成对称密钥
        SecretKey secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
        // 2，创建指定转换的密码器
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        // 3，用密钥初始化此 Cipher
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        // 4，解密
        return cipher.doFinal(input);
    }

//    private static final String KEY = "12345678"; // 报错：java.security.InvalidKeyException: Invalid AES key length: 8 bytes
    private static final String KEY = "1234567812345678"; // 必须需要 128 位
    public static void main(String[] args) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeySpecException, BadPaddingException, InvalidKeyException {
        String str = "hello，中国！";
        byte[] encrypt = encrypt(str.getBytes(StandardCharsets.UTF_8), KEY);
        System.out.println(new String(encrypt));
        byte[] decrypt = decrypt(encrypt, KEY);
        System.out.println(new String(decrypt));
    }
}
