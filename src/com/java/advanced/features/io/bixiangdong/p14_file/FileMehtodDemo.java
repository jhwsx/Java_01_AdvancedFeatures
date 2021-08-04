package com.java.advanced.features.io.bixiangdong.p14_file;

import javafx.scene.input.DataFormat;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;

/**
 * @author wangzhichao
 * @since 2021/7/27
 *
 */
public class FileMehtodDemo {
    public static void main(String[] args) {
        /**
         * 获取 api 演示
         */
//        getDemo();

//        createAndDeleteDemo();

//        isDemo();

//        renameToDemo();

        listRootsDemo();
    }

    private static void listRootsDemo() {
        File[] files = File.listRoots();
        for (File file : files) {
            System.out.println(file.getAbsolutePath());
            System.out.println("file.getTotalSpace() = " + file.getTotalSpace());
            System.out.println("file.getUsableSpace() = " + file.getUsableSpace());
            System.out.println("file.getFreeSpace() = " + file.getFreeSpace());
        }
    }

    private static void renameToDemo() {
        // 重命名
//        File f1 = new File("I:\\xinjing.mp4");
        File f2 = new File("I:\\xinjing2.mp4");
//        boolean b = f1.renameTo(f2);
//        System.out.println("b = " + b);
        // 移动
        File f3 = new File("H:\\xinjing.mp4");
        boolean b1 = f2.renameTo(f3);
        System.out.println("b1 = " + b1);
    }

    private static void isDemo() {
        File file = new File("file.txt");
        System.out.println("file.exists() = " + file.exists());
        File dir = new File("a");
        System.out.println("dir.exists() = " + dir.exists());
        File what = new File("what");
        System.out.println("what.exists() = " + what.exists());
    }

    private static void createAndDeleteDemo() {
        // 对于文件
        File file = new File("create.txt");
        try {
            // 如果文件存在，返回false；反之，返回true
            boolean created = file.createNewFile();
            System.out.println("created = " + created);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (file.exists()) {
            boolean delete = file.delete();
            System.out.println("delete = " + delete);
        }
        try {
            // temp.getAbsolutePath() = C:\Users\willw\AppData\Local\Temp\temp2464096493512010557.dat
            File temp = File.createTempFile("temp", ".dat");
            System.out.println("temp.getAbsolutePath() = " + temp.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对于文件夹
        File dir = new File("a");
        // 创建此抽象路径名指定的目录。 创建单级目录
        boolean mkdir = dir.mkdir();
        System.out.println("mkdir = " + mkdir);
        // 如果此路径名表示一个目录，则该目录必须为空才能删除。
        boolean delete = dir.delete();
        System.out.println("delete = " + delete);

        File dir2 = new File("a//c//d//e");
        // 创建多级目录
        boolean mkdirs = dir2.mkdirs();
        System.out.println("mkdirs = " + mkdirs);
    }

    private static void getDemo() {
        File file = new File("file.txt");

        String name = file.getName();
        System.out.println("name = " + name);
        String absPath = file.getAbsolutePath();
        System.out.println("absPath = " + absPath);
        String path = file.getPath();
        System.out.println("path = " + path);
        try {
            String canonicalPath = file.getCanonicalPath();
            System.out.println("canonicalPath = " + canonicalPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 相对路径，获取父目录为null
        String parent = file.getParent();
        System.out.println("parent = " + parent);
        long length = file.length();
        System.out.println("length = " + length);
        long time = file.lastModified();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
        String timeStr = dateFormat.format(time);
        System.out.println("timeStr = " + timeStr);
    }
}
