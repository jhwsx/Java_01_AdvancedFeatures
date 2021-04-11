package com.java.advanced.features.concurrent.threadsafe.deadlock.demo1;

import java.util.ArrayList;
import java.util.List;

/**
 * 例子来源：https://ifeve.com/deadlock/
 */
// TreeNode 类，它可以调用不同实例的 synchronized 同步代码块方法
public class TreeNode {
    TreeNode parent = null;
    List<TreeNode> children = new ArrayList<>();
    public static Object parentLock = new Object();
    public static Object childLock = new Object();

    /**
     * 把 TreeNode 子对象添加进 children 列表里面
     * @param child
     */
    public void addChild(TreeNode child) {
        synchronized (parentLock) {
            System.out.println(Thread.currentThread().getName() + " holds lock parentLock");
            if (!children.contains(child)) {
                children.add(child);
                child.setParentOnly(this);
            }
        }
    }

    /**
     * 给 TreeNode 设置父节点
     * @param parent
     */
    public void setParent(TreeNode parent) {
        synchronized (childLock) {
            System.out.println(Thread.currentThread().getName() + " holds lock childLock");
            this.parent = parent;
            parent.addChildOnly(this);
        }
    }

    private void addChildOnly(TreeNode child) {
        System.out.println(Thread.currentThread().getName() + " waits for lock parentLock");
        synchronized (parentLock) {
            System.out.println(Thread.currentThread().getName() + " holds lock parentLock");
            if (!children.contains(child)) {
                children.add(child);
            }
        }
    }

    private void setParentOnly(TreeNode parent) {
        System.out.println(Thread.currentThread().getName() + " waits for lock childLock");
        synchronized (childLock) {
            System.out.println(Thread.currentThread().getName() + " holds lock childLock");
            this.parent = parent;
        }
    }

    // 任务1：需要先拿到 parentLock 锁，再拿到 childLock 锁
    private static class Task1 implements Runnable {
        private TreeNode parent;
        private TreeNode child;

        public Task1(TreeNode parent, TreeNode child) {
            this.parent = parent;
            this.child = child;
        }

        @Override
        public void run() {
            parent.addChild(child);
        }
    }
    // 任务2：需要先拿到 childLock 锁，再拿到 parentLock 锁
    private static class Task2 implements Runnable {
        private TreeNode parent;
        private TreeNode child;

        public Task2(TreeNode parent, TreeNode child) {
            this.parent = parent;
            this.child = child;
        }

        @Override
        public void run() {
            child.setParent(parent);
        }
    }

    // 这个例子会发生死锁
    public static void main(String[] args) {
        TreeNode parent = new TreeNode();
        TreeNode child = new TreeNode();
        // 两个任务中的 parent 是同一对象，child 也是同一对象
        Thread thread1 = new Thread(new Task1(parent, child), "Thread1");
        Thread thread2 = new Thread(new Task2(parent, child), "Thread2");
        thread1.start();
        thread2.start();
    }
}
