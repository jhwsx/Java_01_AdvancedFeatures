package com.java.advanced.features.io.bixiangdong.p21_test;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

/**
 * 需求：合并指定目录下的多个文件为一个文件
 *
 * @author wangzhichao
 * @since 2021/8/4
 */
public class Test2_MergeFile {
    public static void main(String[] args) throws IOException {
        File dir = new File("H:\\partFiles");
        mergeFilesInDir(dir);
    }

    private static void mergeFilesInDir(File dir) throws IOException {
        List<FileInputStream> list = new ArrayList<>();
        File[] files = dir.listFiles();
        if (files == null) return;
        for (File file : files) {
            list.add(new FileInputStream(file));
        }
        Enumeration<FileInputStream> enumeration = Collections.enumeration(list);
        SequenceInputStream sequenceInputStream = new SequenceInputStream(enumeration);
        BufferedInputStream bis = new BufferedInputStream(sequenceInputStream);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("H://merge.mp4"));
        byte[] buffer = new byte[1024];
        int len;
        while ((len = bis.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
            bos.flush();
        }
        bos.close();
        bis.close();
    }
}
