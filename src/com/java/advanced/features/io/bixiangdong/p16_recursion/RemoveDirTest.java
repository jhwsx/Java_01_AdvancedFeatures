package com.java.advanced.features.io.bixiangdong.p16_recursion;

import java.io.File;

/**
 * 删除带内容的目录
 *
 * Windows：从里往外删
 *
 * @author wangzhichao
 * @since 2021/7/28
 */
public class RemoveDirTest {
    public static void main(String[] args) {
        File dir = new File("H:\\aa");
        deleteDir(dir);
    }

    private static void deleteDir(File dir) {
        File[] files = dir.listFiles();
        if (files == null) return;
        if (files.length == 0) {
            // 空目录，直接可以删掉了
            dir.delete();
            return;
        }
        // 有内容的目录，遍历删除吧
        for (File file : files) {
            if (file.isDirectory()) {
                deleteDir(file);
            } else {
                file.delete();
            }
        }
        // 变成空目录了，可以直接删掉。
        dir.delete();
    }


}
