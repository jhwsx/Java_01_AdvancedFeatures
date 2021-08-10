package com.java.advanced.features.io.bixiangdong.p27_encode;


import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * @author wangzhichao
 * @since 2021/8/9
 */
public class EncodeDemo {
    public static void main(String[] args) throws UnsupportedEncodingException {
        test("","");
        test("utf-8", "utf-8");
        test("gbk", "gbk");
        test("utf-8","gbk");
        test("gbk","utf-8");
        test("gbk","iso8859-1");
    }

    private static void test(String encodeCharsetName, String decodeCharsetName) throws UnsupportedEncodingException {
        System.out.println("encodeCharsetName = " + encodeCharsetName + ", decodeCharsetName = " + decodeCharsetName);
        String s = "你好";
        // 编码：字符串 -> 字节数组
        byte[] bytes;
        if (encodeCharsetName.equals("")) {
            bytes = s.getBytes();
        } else {
            bytes = s.getBytes(encodeCharsetName);
        }
        System.out.println(Arrays.toString(bytes));
        // 解码：字节数组 -> 字符串
        String s1;
        if (decodeCharsetName.equals("")) {
            s1 = new String(bytes);
        } else {
            s1 = new String(bytes, decodeCharsetName);
        }
        System.out.println(s1);
    }

}
