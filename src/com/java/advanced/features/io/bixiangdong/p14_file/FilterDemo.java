package com.java.advanced.features.io.bixiangdong.p14_file;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.Arrays;

/**
 * @author wangzhichao
 * @since 2021/7/27
 */
public class FilterDemo {
    public static void main(String[] args) {
        File dir = new File("H://");
        String[] list = dir.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".mp4");
            }
        });
        System.out.println(Arrays.toString(list));
        System.out.println("==================================");
        File[] files = dir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.canRead();
            }
        });
        for (File file : files) {
            System.out.println(file.getAbsolutePath());
        }
        System.out.println("==================================");
        File[] files1 = dir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".mp4");
            }
        });
        for (File file : files1) {
            System.out.println(file.getAbsolutePath());
        }
    }
}
