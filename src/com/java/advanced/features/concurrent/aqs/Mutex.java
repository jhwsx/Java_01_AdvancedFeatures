package com.java.advanced.features.concurrent.aqs;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 这是 AbstractQueuedSynchronizer 文档里的例子：非再进入的互斥锁类
 *
 * 它使用值 0 表示未锁定状态，使用 1 表示锁定状态。
 * 当非重入锁定不严格地需要当前拥有者线程的记录时，此类使得使用监视器更加方便。
 * 它还支持一些条件并公开了一个检测方法
 */
public class Mutex implements Lock, Serializable {
    // 我们的内部辅助类
    private static class Sync extends AbstractQueuedSynchronizer {
        // Report whether in locked state
        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }
        // Acquire the lock if state is zero
        @Override
        protected boolean tryAcquire(int acquires) {
            assert acquires == 1; // Otherwise unused
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        // Release the lock by setting state to zero
        @Override
        protected boolean tryRelease(int releases) {
            assert releases == 1; // Otherwise unused
            if (getState() == 0) throw new IllegalMonitorStateException();
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        // Provide a Condition
        Condition newCondition() {
            return new ConditionObject();
        }

        // Deserialize properly
        private void readObject(ObjectInputStream s)
            throws IOException, ClassNotFoundException {
            s.defaultReadObject();
            setState(0); // reset to unlocked state
        }
    }

    // The sync object does all the hard work. We just forward to it.
    private final Sync sync = new Sync();
    // Lock 接口方法 开始
    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(timeout));
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }
    // Lock 接口方法 结束

    public boolean isLocked() {
        return sync.isHeldExclusively();
    }

    public boolean hasQueuedThreads() {
        return sync.hasQueuedThreads();
    }
}
