package com.java.advanced.features.net.security;

/**
 * 凯撒加密
 *
 * @author wangzhichao
 * @since 2022/4/21
 */
public class CaesarEncryption {
    public static String encrypt(String input, int key) {
        char[] chars = input.toCharArray();
        char[] result = new char[chars.length];
        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            result[i] = (char) (ch + key);
        }
        return new String(result);
    }

    public static String decrypt(String input, int key) {
        char[] chars = input.toCharArray();
        char[] result = new char[chars.length];
        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            result[i] = (char) (ch - key);
        }
        return new String(result);
    }

    public static void main(String[] args) {
        String str = "hello,world!";
        int key = 1;
        String encrypt = encrypt(str, key);
        System.out.println(encrypt);
        String decrypt = decrypt(encrypt, key);
        System.out.println(decrypt);
    }
}
