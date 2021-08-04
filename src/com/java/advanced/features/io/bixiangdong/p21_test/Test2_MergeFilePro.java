package com.java.advanced.features.io.bixiangdong.p21_test;

import java.io.*;
import java.util.*;

/**
 * 需求：合并指定目录下的多个文件为一个文件
 *
 * @author wangzhichao
 * @since 2021/8/4
 */
public class Test2_MergeFilePro {
    public static void main(String[] args) throws IOException {
        File dir = new File("H:\\partFilesPro");
        Pair<String, Integer> pair = readConfig(dir);
        mergeFilesInDir(pair, dir);
    }

    private static Pair<String, Integer> readConfig(File dir) throws IOException {
        // 找到配置文件
        File[] files = dir.listFiles(new SuffixFilter(".properties"));
        if (files == null || files.length != 1) {
            throw new ParseConfigException("配置文件里没有后缀为.properties的文件或者有但不唯一");
        }
        Properties properties = new Properties();
        FileInputStream fis = new FileInputStream(new File(dir, files[0].getName()));
        properties.load(fis);
        fis.close();
        String filename = properties.getProperty("filename");
        int partcount = Integer.parseInt(properties.getProperty("partcount"));
        return new Pair<>(filename, partcount);
    }

    private static void mergeFilesInDir(Pair<String, Integer> pair, File dir) throws IOException {
        String fileName = pair.first;
        int partCount = pair.second;
        List<FileInputStream> list = new ArrayList<>();
        File[] files = dir.listFiles(new SuffixFilter(".part"));
        if (files == null || files.length != partCount) {
            throw new MergeFileException("part文件个数不匹配，应为" + partCount + "个");
        }
        for (File file : files) {
            list.add(new FileInputStream(file));
        }
        Enumeration<FileInputStream> enumeration = Collections.enumeration(list);
        SequenceInputStream sequenceInputStream = new SequenceInputStream(enumeration);
        BufferedInputStream bis = new BufferedInputStream(sequenceInputStream);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(dir, fileName)));
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
