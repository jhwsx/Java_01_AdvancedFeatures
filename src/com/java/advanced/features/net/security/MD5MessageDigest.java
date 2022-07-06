package com.java.advanced.features.net.security;

import org.apache.commons.codec.binary.Hex;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 消息摘要
 * @author wangzhichao
 * @since 2022/4/22
 */
public class MD5MessageDigest {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        String str = "hello，中国！";
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(str.getBytes(StandardCharsets.UTF_8));
        System.out.println("messageDigest.getDigestLength() = " + messageDigest.getDigestLength());
        byte[] digest = messageDigest.digest();
        System.out.println(Hex.encodeHex(digest));
    }
}
/*
messageDigest.getDigestLength() = 16
f480f5116e8bba96944c344362cebde9
*/
