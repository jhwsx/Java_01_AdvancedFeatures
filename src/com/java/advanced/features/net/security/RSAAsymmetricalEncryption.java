package com.java.advanced.features.net.security;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;

/**
 * @author wangzhichao
 * @since 2022/4/22
 */
public class RSAAsymmetricalEncryption {
    // 一次性加密的最大长度是117字节
    private static final int ENCRYPT_BLOCK_MAX = 117;
    // 一次性解密的最大长度是128字节
    private static final int DECRYPT_BLOCK_MAX = 128;

    public static byte[] encrypt(byte[] input, PublicKey publicKey)
            throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException, NoSuchPaddingException, NoSuchAlgorithmException {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return doFinalWithBatch(input, cipher, ENCRYPT_BLOCK_MAX);
    }

    public static byte[] decrypt(byte[] input, PrivateKey privateKey)
            throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException, NoSuchPaddingException, NoSuchAlgorithmException {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return doFinalWithBatch(input, cipher, DECRYPT_BLOCK_MAX);
    }

    private static byte[] doFinalWithBatch(byte[] input, Cipher cipher, int blockSize) throws IllegalBlockSizeException, BadPaddingException, IOException {
        int length = input.length;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        for (int offset = 0; offset < length; offset += blockSize) {
            int inputLen = Math.min(length - offset, blockSize);
            baos.write(cipher.doFinal(input, offset, inputLen));
        }
        return baos.toByteArray();
    }

    private static final String TRANSFORMATION = "RSA";
    private static final String ALGORITHM = "RSA";

    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, IOException {
        KeyPairGenerator generator = KeyPairGenerator.getInstance(ALGORITHM);
        generator.initialize(1024);
        KeyPair keyPair = generator.generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();
        String str = "hello，中国！";
        byte[] encrypt = encrypt(str.getBytes(StandardCharsets.UTF_8), publicKey);
        System.out.println(new String(encrypt));
        byte[] decrypt = decrypt(encrypt, privateKey);
        System.out.println(new String(decrypt));
    }

}
