package com.java.advanced.features.io.practice;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Signature {
    public static void signature(File unsignedApk, File signedApk) {
        String cmd[] = {
                "cmd.exe", "/C", "jarsigner", "-sigalg", "MD5withRSA",
                "-digestalg", "SHA1",
                "-keystore", "C:\\Users\\wangzhichao\\.android/debug.keystore",
                "-storepass", "android",
                "-keypass", "android",
                "-signedjar", signedApk.getAbsolutePath(),
                unsignedApk.getAbsolutePath(),
                "androiddebugkey"
        };
        try {
            Process process = Runtime.getRuntime().exec(cmd);
            System.out.println("start sign");
            int waitResult = process.waitFor();
            System.out.println("waitResult: " + waitResult);
            if (process.exitValue() != 0) {
                InputStream is = process.getErrorStream();
                int len;
                byte[] buffer = new byte[2 * 1024];
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                while ((len = is.read(buffer)) != -1) {
                    baos.write(buffer, 0, len);
                }
                System.out.println(new String(baos.toByteArray(), "UTF-8"));
                throw new RuntimeException("签名执行失败");
            }
            System.out.println("finish sign");
            process.destroy();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
