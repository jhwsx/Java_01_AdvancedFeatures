package com.java.advanced.features.io.practice;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.*;

public class Zip {
    /**
     * 把指定文件解压到指定目录下
     *
     * @param zip 需要解压的文件
     * @param dir 解压后的文件存放的目录
     */
    public static void unZip(File zip, File dir) {
        try {
            // 打开供阅读的 ZIP 文件，由指定的 File 对象给出。
            ZipFile zipFile = new ZipFile(zip);
            // 返回 ZIP 文件条目的枚举。
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            // 测试此枚举是否包含更多的元素。
            while (entries.hasMoreElements()) {
                // 如果此枚举对象至少还有一个可提供的元素，则返回此枚举的下一个元素。
                ZipEntry zipEntry = entries.nextElement();
                String name = zipEntry.getName();
                if ("META-INF/CERT.RSA".equals(name)
                        || "META-INF/CERT.SF".equals(name)
                        || "META-INF/MANIFEST.MF".equals(name)) {
                    continue;
                }
                if (!zipEntry.isDirectory()) {
                    // 创建 zipEntry 在目标目录下的文件
                    File file = new File(dir, name);
                    // 返回此抽象路径名父目录的抽象路径名；如果此路径名没有指定父目录，则返回 null。
                    File parentFile = file.getParentFile();
                    if (!parentFile.exists()) {
                        parentFile.mkdirs();
                    }
                    //  返回输入流以读取指定 ZIP 文件条目的内容。
                    InputStream is = zipFile.getInputStream(zipEntry);
                    FileOutputStream fos = new FileOutputStream(file);
                    byte[] buffer = new byte[4 * 1024];
                    int length;
                    while ((length = is.read(buffer)) != -1) {
                        fos.write(buffer, 0, length);
                    }
                    is.close();
                    fos.close();
                }
            }
            zipFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 把指定目录下的文件压缩为指定的压缩包
     * @param dir
     * @param zip
     */
    public static void zip(File dir, File zip) {
        zip.delete();
        try {
            // 对输出文件做 CRC32 校验
            CheckedOutputStream cos = new CheckedOutputStream(
                    new FileOutputStream(zip), new CRC32()
            );
            ZipOutputStream zos = new ZipOutputStream(cos);
            compress(dir, zos, "");
            zos.flush();
            zos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void compress(File srcFile, ZipOutputStream zos,
                                 String basePath) throws Exception {
        if (srcFile.isDirectory()) {
            compressDir(srcFile, zos, basePath);
        } else {
            compressFile(srcFile, zos, basePath);
        }
    }

    private static void compressDir(File dir, ZipOutputStream zos,
                                    String basePath) throws Exception {
        File[] files = dir.listFiles();
        // 构建空目录
        if (files.length < 1) {
            ZipEntry entry = new ZipEntry(basePath + dir.getName() + "/");
            zos.putNextEntry(entry);
            zos.closeEntry();
        }
        for (File file : files) {
            // 递归压缩
            compress(file, zos, basePath + dir.getName() + "/");
        }
    }

    private static void compressFile(File file, ZipOutputStream zos, String dir)
            throws Exception {


        String dirName = dir + file.getName();

        String[] dirNameNew = dirName.split("/");

        StringBuffer buffer = new StringBuffer();

        if (dirNameNew.length > 1) {
            for (int i = 1; i < dirNameNew.length; i++) {
                buffer.append("/");
                buffer.append(dirNameNew[i]);

            }
        } else {
            buffer.append("/");
        }

        ZipEntry entry = new ZipEntry(buffer.toString().substring(1));
        zos.putNextEntry(entry);
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(
                file));
        int count;
        byte data[] = new byte[1024];
        while ((count = bis.read(data, 0, 1024)) != -1) {
            zos.write(data, 0, count);
        }
        bis.close();
        zos.closeEntry();
    }
}
