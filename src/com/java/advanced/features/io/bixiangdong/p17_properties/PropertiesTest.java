package com.java.advanced.features.io.bixiangdong.p17_properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 定义功能，获取一个应用程序运行的次数，如果超过5次，给出使用次数已到请注册的提示，并不再运行程序
 *
 * 简单配置用 properties
 * 复杂配置用 xml
 * @author wangzhichao
 * @since 2021/8/2
 */
public class PropertiesTest {

    private static final String FILE_NAME = "times.properties";
    public static final String KEY_TIME = "time";

    public static void main(String[] args) throws IOException {
        int usedTimes = readUsedTimes();
        if (usedTimes >= 5) {
            System.err.println("使用次数已到请注册");
            System.exit(0);
        } else {
            writeUsedTimes(usedTimes + 1);
        }
        System.out.println("剩余使用次数：" + (5 - usedTimes - 1));
        System.out.println("欢迎使用本程序！！！");
    }

    public static int readUsedTimes() throws IOException {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            file.createNewFile();
        }
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(file);
        properties.load(fileInputStream);
        String timesStr = properties.getProperty(KEY_TIME);
        try {
            return Integer.valueOf(timesStr);
        } catch (NumberFormatException e) {
            return 0;
        } finally {
            fileInputStream.close();
        }
    }

    public static void writeUsedTimes(int usedTimes) throws IOException {
        File file = new File(FILE_NAME);
        Properties properties = new Properties();
        properties.setProperty("time", String.valueOf(usedTimes));
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        properties.store(fileOutputStream, KEY_TIME);
        fileOutputStream.close();
    }
}
