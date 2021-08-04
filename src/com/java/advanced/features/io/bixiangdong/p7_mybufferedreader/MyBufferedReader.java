package com.java.advanced.features.io.bixiangdong.p7_mybufferedreader;

import java.io.IOException;
import java.io.Reader;

/**
 * @author wangzhichao
 * @since 2021/7/21
 */
public class MyBufferedReader extends Reader {
    private Reader in;

    public MyBufferedReader(Reader in) {
        this.in = in;
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        return 0;
    }

    @Override
    public void close() throws IOException {

    }
}
