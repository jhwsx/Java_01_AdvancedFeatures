package com.java.advanced.features.io;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.time.Duration;
import java.time.Instant;

public class _08_FileChannelTest {
    public static void main(String[] args) {
        File sourceFile = new File("F://test.mp4");
        File targetFile1 = new File("F://test_stream.mp4");
        File targetFile2 = new File("F://test_channel.mp4");
//        copyFileByStream(sourceFile, targetFile1);
        copyFileByFileChannel(sourceFile, targetFile2);
    }

    private static void copyFileByStream(File sourceFile, File targetFile) {
        Instant begin = Instant.now();
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(sourceFile);
            fos = new FileOutputStream(targetFile);
            byte[] buffer = new byte[1024 * 1024];
            while (fis.read(buffer) != -1) {
                fos.write(buffer);
            }
            fos.flush();
        }  catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        long cost = Duration.between(begin, Instant.now()).toMillis();
        System.out.println("copyFileByStream cost time: " + cost);
    }

    private static void copyFileByFileChannel(File sourceFile, File targetFile) {
        Instant begin = Instant.now();
        FileChannel fileChannelSource = null;
        FileChannel fileChannelTarget = null;
        try {
            RandomAccessFile rafSource = new RandomAccessFile(sourceFile, "r");
            RandomAccessFile rafTarget = new RandomAccessFile(targetFile, "rw");

            fileChannelSource = rafSource.getChannel();
            fileChannelTarget = rafTarget.getChannel();

            ByteBuffer byteBuffer = ByteBuffer.allocate(1024 * 1024);

            while (fileChannelSource.read(byteBuffer) != -1) {
                byteBuffer.flip();
                fileChannelTarget.write(byteBuffer);
                byteBuffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileChannelSource != null) {
                try {
                    fileChannelSource.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileChannelTarget != null) {
                try {
                    fileChannelTarget.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        long cost = Duration.between(begin, Instant.now()).toMillis();
        System.out.println("copyFileByFileChannel cost time: " + cost);
    }
}
