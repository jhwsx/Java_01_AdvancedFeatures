package com.java.advanced.features.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

/**
 * @author wangzhichao
 * @since 2021/5/15
 */
public class Receiver {
    static String mFilePath = "D:\\Receiver\\";

    public static void main(String[] args) {
        int port = 5555;
        ServerSocket server = null;
        Socket socket = null;

        try {
            server = new ServerSocket(port);
            System.out.println("======启动服务=======");
            while (true) {
                socket = server.accept();
                receiveFile(socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected static void receiveFile(Socket socket) {
        File dirs = new File(mFilePath);
        if (!dirs.exists()) {
            dirs.mkdirs();
        }
        DataInputStream din = null;
        int fileNum = 0;
        long totalSize = 0;
        FileInfo[] fileinfos = null;
        try {
            din = new DataInputStream(new BufferedInputStream(
                    socket.getInputStream()));
            fileNum = din.readInt();
            fileinfos = new FileInfo[fileNum];
            for (int i = 0; i < fileNum; i++) {
                fileinfos[i] = new FileInfo();
                fileinfos[i].mFileName = din.readUTF();
                fileinfos[i].mFileSize = din.readLong();
            }
            totalSize = din.readLong();
        } catch (IOException e) {
            e.printStackTrace();
            // Log.d(TAG,"readInt Exception");
            System.exit(0);
        }
        System.out.println("接收文件的个数："+fileNum);
        System.out.println("接收文件列表：");
        for (FileInfo fileinfo : fileinfos) {
            System.out.println(fileinfo.mFileName + "=>" + fileinfo.mFileSize);
        }
        System.out.println("接收文件的总大小："+totalSize);
        // // /
        int leftLen = 0; // 写满文件后缓存区中剩余的字节长度。
        int bufferedLen = 0; // 当前缓冲区中的字节数
        int writeLen = 0; // 每次向文件中写入的字节数
        long writeLens = 0; // 当前已经向单个文件中写入的字节总数
        long totalWriteLens = 0; // 写入的所有字节数
        byte buf[] = new byte[8192];
        for (int i = 0; i < fileNum; i++) {
            writeLens = 0;
            try {
                FileOutputStream fout = new FileOutputStream(mFilePath
                        + fileinfos[i].mFileName);
                while (true) {
                    if (leftLen > 0) {
                        bufferedLen = leftLen;
                    } else {
                        bufferedLen = din.read(buf);
                    }
                    if (bufferedLen == -1)
                        return;
//                    System.out.println("readlen：" + bufferedLen);
                    // 如果已写入文件的字节数加上缓存区中的字节数已大于文件的大小，只写入缓存区的部分内容。
                    if (writeLens + bufferedLen > fileinfos[i].mFileSize) {
                        leftLen = (int) (writeLens + bufferedLen - fileinfos[i].mFileSize);
                        writeLen = bufferedLen - leftLen;
                        fout.write(buf, 0, writeLen); // 写入部分
                        System.out.println(fileinfos[i].mFileName + "的传输进度：" + (writeLens * 100 / fileinfos[i].mFileSize) + "%," + writeLens);
                        totalWriteLens += writeLen;
                        move(buf, writeLen, leftLen); // 把写满文件后缓存区中剩余的字节长度放在 byte 数组里面
                        break;
                    } else {
                        fout.write(buf, 0, bufferedLen); // 全部写入
                        writeLens += bufferedLen;
                        totalWriteLens += bufferedLen;
                        System.out.println(fileinfos[i].mFileName + "的传输进度：" + (writeLens * 100 / fileinfos[i].mFileSize) + "%," + writeLens);
                        System.out.println("总接收进度：" + (totalWriteLens * 100 / totalSize) + "%" + ", " + totalWriteLens);
                        if (totalWriteLens >= totalSize) {
                            //mListener.report(GroupChatActivity.FAIL, null);
                            return;
                        }
                        leftLen = 0;
                    }
                    //mListener.report(GroupChatActivity.PROGRESS,
                    //(int) (totalWriteLens * 100 / totalSize));
                    System.out.println("总接收进度：" + (totalWriteLens * 100 / totalSize) + "%" + ", " + totalWriteLens);
                } // end while
                fout.flush();
                fout.close();

            } catch (Exception e) {
                e.printStackTrace();
                // Log.d(TAG,"receive file Exception");
            }
            System.out.println("总接收进度：" + (totalWriteLens * 100 / totalSize) + "%" + ", " + totalWriteLens);
        } // end for
        //mListener.report(GroupChatActivity.FAIL, null);
    }

    /**
     * @param buf      读取大小
     * @param writeLen 已经写入到文件中的
     * @param leftLen  内存中剩余的字节长度
     */
    public static void move(byte[] buf, int writeLen, int leftLen) {
        byte[] newbuf = new byte[8192];
        for (int i = 0; i < leftLen; i++) {
            if (buf[writeLen + i] != 0) {
                newbuf[i] = buf[writeLen + i];
            }
        }
        Arrays.fill(buf, (byte) 0);
        System.arraycopy(newbuf, 0, buf, 0, newbuf.length);
    }

}
