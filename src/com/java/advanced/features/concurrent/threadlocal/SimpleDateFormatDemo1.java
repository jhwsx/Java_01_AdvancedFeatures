package com.java.advanced.features.concurrent.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * SimpleDateFormat线程不安全问题与ThreadLocal原理
 *
 * https://www.jianshu.com/p/5a7a88e62309
 * https://zhuanlan.zhihu.com/p/61923452
 * https://www.cnblogs.com/peida/archive/2013/05/31/3070790.html
 *
 * 这个例子是演示 SimpleDateFormat 在多线程下不安全的问题
 *
 * @author wangzhichao
 * @since 2021/3/28
 */
public class SimpleDateFormatDemo1 {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        ExecutorService threadPool = new ThreadPoolExecutor(
                5, 50, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(100));

        List<String> data = Arrays.asList(
                "2021-03-01 00:00:00",
                "2020-01-01 12:11:40",
                "2019-07-02 23:11:23",
                "2010-12-03 08:22:33",
                "2013-11-29 10:10:10",
                "2017-09-01 14:14:14",
                "2021-04-01 15:15:15"
        );

        for (String date : data) {
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(sdf.parse(date));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        threadPool.shutdown();

    }
}
