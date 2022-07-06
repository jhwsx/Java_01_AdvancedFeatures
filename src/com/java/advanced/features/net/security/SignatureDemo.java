package com.java.advanced.features.net.security;


import java.nio.charset.StandardCharsets;
import java.security.*;

/**
 * 数字签名
 * @author wangzhichao
 * @since 2022/4/22
 */
public class SignatureDemo {
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        // 1，获取一对公钥和私钥
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(1024);
        KeyPair keyPair = generator.generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();
        // 2，获取 Signature 对象
        Signature signature = Signature.getInstance("MD5withRSA");
        // 3，使用私钥初始化 Signature
        signature.initSign(privateKey);
        // 4，传入要签名的数据
        String str = "hello，中国！";
        signature.update(str.getBytes(StandardCharsets.UTF_8));
        // 5，执行签名
        byte[] signData = signature.sign(); // 得到的是签名数据

        // 原数据和签名数据都会发给接收者的，验证签名
        // 1，使用公钥初始化 Signature
        signature.initVerify(publicKey);
        // 2，传入需要校验的原文
        signature.update(str.getBytes(StandardCharsets.UTF_8));
        // 3，校验签名数据
        boolean verify = signature.verify(signData);
        System.out.println(verify);
    }
}
