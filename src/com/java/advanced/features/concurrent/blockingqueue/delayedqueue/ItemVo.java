package com.java.advanced.features.concurrent.blockingqueue.delayedqueue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 存放在队列中的元素
 * 它必须实现 Delayed 接口
 * 它是一个泛型类
 * 包括到期时间，和业务数据字段
 */
public class ItemVo<T> implements Delayed {
    /**
     * 到期时间，单位是毫秒
     */
    private long keepDuration;

    private T data;

    private long expirationTime;
    public ItemVo(long keepDuration, T data) {
        this.keepDuration = keepDuration;
        this.data = data;
        expirationTime = keepDuration + System.currentTimeMillis();
    }

    public long getKeepDuration() {
        return keepDuration;
    }

    public T getData() {
        return data;
    }

    // 获取剩余的延时时长
    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(expirationTime - System.currentTimeMillis(),
                TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        return Long.compare(getDelay(TimeUnit.MILLISECONDS), o.getDelay(TimeUnit.MILLISECONDS));
    }
}
