package com.java.advanced.features.io.practice;

import java.io.*;

public class Utils {
    public static byte[] getBytes(File file) {
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(file, "r");
            byte[] buffer = new byte[(int) raf.length()];
            raf.readFully(buffer);
            raf.close();
            return buffer;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] getBytes2(File file) throws IOException {
        byte[] result = null;
        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(fis);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[4 * 1024];
        int len;
        while ((len = bis.read(buffer)) != -1) {
            baos.write(buffer, 0, len);
        }
        baos.flush();
        fis.close();
        result = baos.toByteArray();
        baos.close();
        return result;
    }
}
