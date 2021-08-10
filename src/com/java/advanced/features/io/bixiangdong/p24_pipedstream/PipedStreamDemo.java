package com.java.advanced.features.io.bixiangdong.p24_pipedstream;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * 特点：
 * 1，管道输入流应该连接到管道输出流；
 * 2，管道输入流提供要写入管道输出流的所有字节；
 * 3，通常，数据由某个线程从 PipedInputStream 对象读取，并由其他线程将其写入到相应的 PipedOutputStream。
 * 4，不建议对这两个对象尝试使用单个线程，因为这样可能造成死锁线程。
 * 5，管道输入流包含一个缓冲区，可在缓冲区限定的范围内将读操作和写操作分离开。
 *
 * @author wangzhichao
 * @since 2021/8/9
 */
public class PipedStreamDemo {
    public static void main(String[] args) throws IOException {
        PipedInputStream pis = new PipedInputStream();
        PipedOutputStream pos = new PipedOutputStream();

        pis.connect(pos);

        new Thread(new Input(pis)).start();
        new Thread(new Output(pos)).start();
    }
}

class Input implements Runnable {
    private PipedInputStream in;

    Input(PipedInputStream in) {
        this.in = in;
    }

    @Override
    public void run() {
        try {
            byte[] buf = new byte[1024];
            int len = in.read(buf);
            System.out.println(new String(buf, 0, len));
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


class Output implements Runnable {
    private PipedOutputStream out;

    Output(PipedOutputStream out) {
        this.out = out;
    }

    @Override
    public void run() {
        try {
            out.write("hi, pipe is coming".getBytes());
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}