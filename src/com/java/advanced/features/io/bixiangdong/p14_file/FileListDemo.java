package com.java.advanced.features.io.bixiangdong.p14_file;

import java.io.File;

/**
 * @author wangzhichao
 * @since 2021/7/27
 */
public class FileListDemo {
    public static void main(String[] args) {
        // 存在的目录
        File cDir = new File("C://");
        // 空目录
        File emptyDir = new File("H://a");
        File notexistsDir = new File("Z://");
        // 列出当前目录下的所有文件和目录，包括隐藏的。
        print(cDir);
        print(emptyDir);
        print(notexistsDir);
    }

    private static void print(File cDir) {
        System.out.println("cDir.getAbsolutePath() = " + cDir.getAbsolutePath());
        String[] list = cDir.list();
        System.out.println("list = " + list);
        if (list != null) {
            for (String s : list) {
                System.out.println(s);
            }
        }
    }
}
