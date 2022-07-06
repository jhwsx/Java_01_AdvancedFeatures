package com.java.advanced.features.net.security;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

/**
 * @author wangzhichao
 * @since 2022/4/21
 */
public class DESSymmetricalEncryption {
    private static final String ALGORITHM = "DES";
    private static final String TRANSFORMATION = "DES/ECB/PKCS5Padding";
    public static byte[] encrypt(byte[] input, String key)
            throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException {
        // 1，创建指定算法的密钥工厂
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
        // 2，创建密钥规范
        KeySpec keySpec = new DESKeySpec(key.getBytes());
        // 3，生成对称密钥
        SecretKey secretKey = secretKeyFactory.generateSecret(keySpec);
        // 4，创建指定转换的密码器
        Cipher cipher = Cipher.getInstance("DES");
        // 5，用密钥初始化此 Cipher
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        // 6，加密
        return cipher.doFinal(input);
    }

    public static byte[] decrypt(byte[] input, String key)
            throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException {
        // 1，创建指定算法的密钥工厂
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
        // 2，创建密钥规范
        KeySpec keySpec = new DESKeySpec(key.getBytes());
        // 3，生成对称密钥
        SecretKey secretKey = secretKeyFactory.generateSecret(keySpec);
        // 4，创建指定转换的密码器
        Cipher cipher = Cipher.getInstance("DES");
        // 5，用密钥初始化此 Cipher
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        // 6，解密
        return cipher.doFinal(input);
    }

    //    private static final String KEY = "123456";  // 报错：java.security.InvalidKeyException: Wrong key size，不能低于64位。
    private static final String KEY = "12345678"; // 至少 64 位，超出的不会使用到。

    public static void main(String[] args) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeySpecException, BadPaddingException, InvalidKeyException {
        String str = "hello，中国！";
        byte[] encrypt = encrypt(str.getBytes(StandardCharsets.UTF_8), KEY);
        System.out.println(new String(encrypt));
        byte[] decrypt = decrypt(encrypt, KEY);
        System.out.println(new String(decrypt));
    }
}
