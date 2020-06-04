package com.java.advanced.features.io;

import java.io.*;

/**
 * 学习 InputStreamReader
 * public String getEncoding()
 */
public class _06_InputStreamReaderTest {
    public static void main(String[] args) {
        testInputStreamReader();
        System.out.println("-----------------------");
        testInputStreamReaderGBK();
        System.out.println("-----------------------");
        testInputStreamReaderUTF8();
    }

    private static void testInputStreamReader() {
        try {
            FileInputStream fis
                    = new FileInputStream("testtxt/_05_OutputStreamWriterTest.txt");
            InputStreamReader isrDefault = new InputStreamReader(fis);
            BufferedReader brDefault = new BufferedReader(isrDefault);
            System.out.println("isrDefault encode: " + isrDefault.getEncoding());
            String line;
            while((line = brDefault.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void testInputStreamReaderGBK() {
        try {
            FileInputStream fis
                    = new FileInputStream("testtxt/_05_OutputStreamWriterTest.txt");
            InputStreamReader isrGBK = new InputStreamReader(fis, "GBK");
            BufferedReader brDefault = new BufferedReader(isrGBK);
            System.out.println("isrGBK encode: " + isrGBK.getEncoding());
            String line;
            while((line = brDefault.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void testInputStreamReaderUTF8() {
        try {
            FileInputStream fis
                    = new FileInputStream("testtxt/_05_OutputStreamWriterTest.txt");
            InputStreamReader isrUTF8 = new InputStreamReader(fis, "UTF-8");
            BufferedReader brDefault = new BufferedReader(isrUTF8);
            System.out.println("isrUTF8 encode: " + isrUTF8.getEncoding());
            String line;
            while((line = brDefault.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
