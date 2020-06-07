package com.java.advanced.features.io.practice;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Dx {
    /**
     * 把 .jar 文件转变为 .dex 文件
     *
     * 需要使用 C:\Users\wangzhichao\AppData\Local\Android\sdk\build-tools\29.0.2 下的 dx.bat
     * 需要配置环境变量
     *
     * 这里使用 java 调用 windows 下的命令
     * @param dexFile
     * @param jarFile
     */
    public static void dxCommand(File dexFile, File jarFile) {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process process = runtime.exec("cmd.exe /C dx --dex --output=" + dexFile.getAbsolutePath() +
                    " " + jarFile.getAbsolutePath());
            process.waitFor();
            if (process.exitValue() != 0) {
                InputStream is = process.getErrorStream();
                int len;
                byte[] buffer = new byte[2 * 1024];
                ByteArrayOutputStream baos =
                        new ByteArrayOutputStream();
                while ((len = is.read(buffer)) != -1) {
                    baos.write(buffer, 0, len);
                }
                System.out.println(new String(baos.toByteArray(), "UTF-8"));
                throw new RuntimeException("dx run failed!");
            }
            process.destroy();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}
