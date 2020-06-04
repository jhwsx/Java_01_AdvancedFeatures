package com.java.advanced.features.io;

import java.io.*;

public class _04_BufferedReaderWriterTest {
    public static void main(String[] args) {
//        testFileWriter();
        testFileReader();
    }

    private static void testFileWriter() {
        try {
            BufferedWriter bw =
                    new BufferedWriter(
                            new FileWriter(
                                    new File("testtxt/_04_BufferedReaderWriterTest.txt")
                            )
                    );
            bw.write(123456789);
            bw.write("本文会介绍反射相关的知识，为了不和泛型类的方法混在一起，本文集中介绍基本类的反射知识。主要内容包括：\n" +
                    "\n" +
                    "类的生命周期是什么？\n" +
                    "有几种方式获取类类型，这些方式之间的区别是什么？\n" +
                    "如何获取类的超类或实现的接口？\n" +
                    "如何获取类及接口的访问修饰符？\n" +
                    "获取类名的几种方法及区别。");
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void testFileReader() {
        try {
            BufferedReader br =
                    new BufferedReader(
                            new FileReader(
                                    new File("testtxt/_04_BufferedReaderWriterTest.txt")
                            )
                    );
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
