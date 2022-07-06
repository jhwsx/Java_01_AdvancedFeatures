package com.java.advanced.features.net.security;

import org.apache.commons.codec.binary.Base64;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * @author wangzhichao
 * @since 2022/4/23
 */
public class Base64Demo {
    public static void main(String[] args) {
        String str = "Man";
        byte[] encodeBase64 = Base64.encodeBase64(str.getBytes(StandardCharsets.UTF_8));
        System.out.println(new String(encodeBase64));
        System.out.println(new String(Base64.decodeBase64(encodeBase64)));

        str = "Ma";
        encodeBase64 = Base64.encodeBase64(str.getBytes(StandardCharsets.UTF_8));
        System.out.println(new String(encodeBase64));
        System.out.println(new String(Base64.decodeBase64(encodeBase64)));

    }
}
