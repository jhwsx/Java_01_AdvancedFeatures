package com.java.advanced.features.io.bixiangdong.p18_iotest;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 获取指定目录下，指定扩展名的文件（包含子目录中的文件）
 * 这些文件的绝对路径写入到一个文本文件中。
 *
 * @author wangzhichao
 * @since 2021/8/2
 */
public class Test1 {
    public static void main(String[] args) throws IOException {
        File dir = new File(".");
        System.out.println(dir.getAbsolutePath());
        List<File> files = getFilesInDir(dir, new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                if (pathname.isDirectory()) return true;
                return pathname.isFile() && pathname.getAbsolutePath().endsWith(".java");
            }
        });
        write2File(files);
    }

    private static void write2File(List<File> list) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("filepath.txt"));
        for (File file : list) {
            System.out.println(file.getAbsolutePath());
            bw.write(file.getAbsolutePath());
            bw.newLine();
            bw.flush();
        }
        bw.close();
    }

    private static List<File> getFilesInDir(File dir, FileFilter fileFilter) {
        List<File> result = new ArrayList<>();
        File[] files = dir.listFiles(fileFilter);
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    result.addAll(getFilesInDir(file, fileFilter));
                } else {
                    result.add(file);
                }
            }
        }
        return result;
    }
}
