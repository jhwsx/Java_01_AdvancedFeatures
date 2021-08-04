package com.java.advanced.features.io.bixiangdong.p15_transversedir;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangzhichao
 * @since 2021/7/27
 */
public class DirTest {
    public static void main(String[] args) {
        File dir = new File("H:\\一级目录");
        transverseDir(dir,0);
        System.out.println("=====================================");
        List<File> fileList = getFilesInDir(dir);
        for (File file : fileList) {
            System.out.println(file.getAbsolutePath());
        }
    }

    private static List<File> getFilesInDir(File dir) {
        List<File> result = new ArrayList<>();
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                result.addAll(getFilesInDir(file));
            } else {
                result.add(file);
            }
        }
        return result;
    }

    private static void transverseDir(File dir, int level) {
        System.out.println(getIndent(level) + dir.getName());
        File[] files = dir.listFiles();
        level++;
        for (File file : files) {
            if (file.isDirectory()) {
                transverseDir(file, level);
            } else {
                System.out.println(getIndent(level) + file.getName());
            }
        }
    }

    private static String getIndent(int level) {
        StringBuilder sb = new StringBuilder();
        sb.append("|--");
        for (int i = 0; i < level; i++) {
            sb.insert(0, "|  ");
        }
        return sb.toString();
    }
}
