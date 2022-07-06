package com.java.advanced.features.net.security;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @author wangzhichao
 * @since 2022/4/23
 */
public class URLEncodingDemo {
    public static void main(String[] args) {
        String encode = URLEncoder.encode("中国", StandardCharsets.UTF_8);
        System.out.println(encode);
    }
}
