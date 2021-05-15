package com.java.advanced.features.io;

import java.io.*;
import java.net.Socket;

/**
 * https://blog.csdn.net/fx1ts/article/details/38702603
 * https://www.jianshu.com/p/368df4d2c5bd
 * @author wangzhichao
 * @since 2021/5/15
 */
public class Sender {
    public static int port = 5555;
    public static String host = "localhost";
    public static Socket socket = null;
    public static void main(String[] args) {
        createSocket();
        String dirStr = "E:\\videos";
        File dir = new File(dirStr);
        File[] files = dir.listFiles();
        sendFile(socket, files);
    }

    public static void createSocket(){
        try {
            socket = new Socket(host, port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected static void sendFile(Socket socket, File[] files) {
        long totalSize = 0;
        byte buf[] = new byte[8192];
        int len;
        try {
            if (socket.isOutputShutdown()) {
                return;
            }
            DataOutputStream dout = new DataOutputStream(
                    socket.getOutputStream());
            System.out.println("传输文件的个数：" + files.length);
            dout.writeInt(files.length);
            System.out.println("传输文件列表：");
            for (int i = 0; i < files.length; i++) {
                dout.writeUTF(files[i].getName());
                dout.flush();
                dout.writeLong(files[i].length());
                dout.flush();
                System.out.println(files[i].getName() + "=>" + files[i].length());
                totalSize += files[i].length();
            }
            System.out.println("传输文件的总大小：" + totalSize);
            dout.writeLong(totalSize);

            for (int i = 0; i < files.length; i++) {
                BufferedInputStream din = new BufferedInputStream(
                        new FileInputStream(files[i]));
                while ((len = din.read(buf)) != -1) {
                    dout.write(buf, 0, len);
                }
            }
            System.out.println("文件传输完成");

        } catch (Exception e) {
            e.printStackTrace();
            // Log.d(TAG,"send file exception");
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
