package com.java.advanced.features.io.bixiangdong.p17_properties;

import java.io.*;
import java.util.Properties;
import java.util.Set;

/**
 * 属性列表中每个键及其对应值都是一个字符串
 *
 * @author wangzhichao
 * @since 2021/7/28
 */
public class PropertiesDemo {
    public static void main(String[] args) throws IOException {
//        propertiesSetAndGet();
//        propertiesStream();
//        propertiesWriteConfig();
//        propertiesReadConfig();
        propertiesAdjustConfig();
    }

    private static void propertiesAdjustConfig() throws IOException {
        // 先读取配置
        Properties properties = new Properties();
        FileInputStream fis = new FileInputStream("grade.config");
        properties.load(fis);
        fis.close();
        // 对数据进行修改
        properties.setProperty("math", "0");
        // 最后写配置
        FileOutputStream fos = new FileOutputStream("grade.config");
        properties.store(fos, "class=grade");
        fos.close();
    }

    private static void propertiesReadConfig() throws IOException {
        Properties properties = new Properties();

        FileInputStream fis = new FileInputStream("grade.config");
        // 加载流里的内容
//        properties.load(fis);
        // 自己实现的 load 方法
        myLoad(properties, fis);

        fis.close();
        properties.list(System.out);
    }

    private static void myLoad(Properties properties, InputStream inputStream) throws IOException {
        InputStreamReader isr = new InputStreamReader(inputStream);
        BufferedReader br = new BufferedReader(isr);
        String line;
        while ((line = br.readLine()) != null) {
            if (line.startsWith("#")) continue;
            String[] split = line.split("=");
            if (split.length != 2) continue;
            properties.setProperty(split[0], split[1]);
        }
        br.close();
    }

    private static void propertiesWriteConfig() throws IOException {
        Properties properties = new Properties();
        properties.setProperty("chinese", "100");
        properties.setProperty("math", "95");
        properties.setProperty("english", "98");

//        FileOutputStream fos = new FileOutputStream("grade.config");
//        // 不要使用中文注释
//        properties.store(fos, "class=grade");
//        fos.close();

        FileWriter fw = new FileWriter("grade.config");
        properties.store(fw, "class=grade");
        fw.close();
    }

    /**
     * Properties集合和流
     */
    private static void propertiesStream() {
        Properties properties = new Properties();
        properties.setProperty("chinese", "100");
        properties.setProperty("math", "95");
        properties.setProperty("english", "98");

        properties = System.getProperties();

        properties.list(System.out);
    }

    /**
     * Properties 集合的存，取，改。
     */
    private static void propertiesSetAndGet() {
        Properties properties = new Properties();
        properties.setProperty("chinese", "100");
        properties.setProperty("math", "95");
        properties.setProperty("english", "98");

        properties.setProperty("math", "150");

        Set<String> propertyNames = properties.stringPropertyNames();
        for (String propertyName : propertyNames) {
            String property = properties.getProperty(propertyName);
            System.out.println(propertyName + "=" + property);
        }
    }
}
