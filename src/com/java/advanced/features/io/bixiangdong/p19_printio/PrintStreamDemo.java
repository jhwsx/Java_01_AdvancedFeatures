package com.java.advanced.features.io.bixiangdong.p19_printio;

import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 * PrintStream 是 FilterOutputStream 包装类的子类，是包装流
 * 1，能够方便地打印各种数据值表示形式
 * 2，永远不会抛出 IOException
 * 3，构造方法：
 *      接收一个文件或文件路径
 *      就收一个字节输出流
 * @author wangzhichao
 * @since 2021/8/3
 */
public class PrintStreamDemo {
    public static void main(String[] args) throws FileNotFoundException {
        PrintStream printStream = new PrintStream("print.txt");
        // 将指定的字节写入此输出流。write 的常规协定是：向输出流写入一个字节。
        // 要写入的字节是参数 b 的八个低位。b 的 24 个高位将被忽略。
//        printStream.write(97); // 97 的二进制：0110 0001，打印出来是a
//        printStream.write(353); // 353 的二进制：0001 0110 0001，打印出来是a
        printStream.print(97); // 内部实现：write(String.valueOf(i))，打印出来是97
        printStream.close();
    }
}
