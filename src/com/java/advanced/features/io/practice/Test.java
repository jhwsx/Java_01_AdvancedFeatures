package com.java.advanced.features.io.practice;

import java.io.*;

public class Test {
    public static void main(String[] args) {
        // 准备空目录
        File tempFileApk = new File("source/apk/temp");
        if (tempFileApk.exists()) {
            // 删除该目录
            deleteDir(tempFileApk);
        }
        tempFileApk.mkdirs();
        File tempFileAar = new File("source/aar/temp");
        if (tempFileAar.exists()) {
            // 删除该目录
            deleteDir(tempFileAar);
        }
        tempFileAar.mkdirs();
        // 初始化 AES
        AES.init(AES.DEFAULT_PWD);

        //1：处理原始 apk，加密 dex
        // 小步骤：
        // 1.1 解压原始 apk 到 source/apk/temp 目录
        File apkFile = new File("source/apk/app-debug.apk");
        File dir = tempFileApk;
        Zip.unZip(apkFile, dir);
        // 1.2 过滤出 source/apk/temp 目录下的 .dex 文件数组（若存在分包，那么会有多个 .dex 文件）
        File[] dexFiles = dir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".dex");
            }
        });
        // 1.3 遍历 .dex 文件数组，对每个 .dex 文件进行加密处理
        for (File dexFile : dexFiles) {
            // 1.3.1 把 .dex 文件转为 byte[]
            byte[] bytes = Utils.getBytes(dexFile);
            // 1.3.2 对 byte[] 进行加密，得到新的 byte[]
            byte[] encryptBytes = AES.encrypt(bytes);
            // 1.3.3 把得到的 byte[] 写回到原来的 .dex 文件中
            try {
                FileOutputStream fos = new FileOutputStream(dexFile);
                fos.write(encryptBytes);
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 1.4 对 .dex 文件进行重命名，这是为了区分源 dex，和壳 dex 文件
        for (File dexFile : dexFiles) {
            String name = dexFile.getName();
            int index = name.indexOf(".dex");
            String newName = dexFile.getParent() + File.separator
                    + name.substring(0, index) + "_" + ".dex";
            dexFile.renameTo(new File(newName));
        }

        // 2. 处理 aar，获取壳 dex
        // 2.1 解压 aar 文件到 source/aar/temp 目录下
        File aarFile = new File("source/aar/mylibrary-debug.aar");
        Zip.unZip(aarFile, tempFileAar);
        // 2.2 过滤出目录下 classes.jar 文件（因为是 aar 文件，这里只有一个 classes.jar 文件）
        File[] jarFiles = tempFileAar.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return "classes.jar".equals(name);
            }
        });
        File classesJarFile = jarFiles[0];
        // 2.3 把 classes.jar 文件转为 classes.dex 文件
        File classesDexFile = new File(classesJarFile.getParentFile(), "classes.dex");
        Dx.dxCommand(classesDexFile, classesJarFile);
        // 2.4 把获取到的壳 classes.dex 文件写到 source/apk/temp 目录下
        byte[] bytes = Utils.getBytes(classesDexFile);
        File targetFile = new File(tempFileApk, "classes.dex");
        try {
            FileOutputStream fos = new FileOutputStream(targetFile);
            fos.write(bytes);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 3. 打包签名
        // 3.1 打包
        File unsignedApkFile = new File("result/apk-unsigned.apk");
        unsignedApkFile.getParentFile().mkdirs();
        Zip.zip(tempFileApk, unsignedApkFile);
        // 3.2 签名
        File signedApkFile = new File("result/apk-signed.apk");
        Signature.signature(unsignedApkFile, signedApkFile);
    }

    private static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) return false;
            }
        }
        return dir.delete();
    }

}
