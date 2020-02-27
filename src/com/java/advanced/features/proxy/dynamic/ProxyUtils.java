package com.java.advanced.features.proxy.dynamic;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Modifier;

public class ProxyUtils {
    public static void generateClassFile(String proxyName, Class<?>[] interfaces) {
        /*
        byte[] proxyClassFile = ProxyGenerator.generateProxyClass(
                proxyName, interfaces, accessFlags);*/
        byte[] proxyClassFile = ProxyGenerator.generateProxyClass(
                proxyName, interfaces);
        String path = ProxyUtils.class.getResource(".").getPath();
        System.out.println(path);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(path + proxyName + ".class");
            fos.write(proxyClassFile);
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
