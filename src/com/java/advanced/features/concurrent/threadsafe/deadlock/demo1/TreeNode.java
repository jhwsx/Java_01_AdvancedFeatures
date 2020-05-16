package com.java.advanced.features.concurrent.threadsafe.deadlock.demo1;

import java.util.ArrayList;
import java.util.List;

/**
 * 例子来源：https://ifeve.com/deadlock/
 */
public class TreeNode {
    TreeNode parent = null;
    List<TreeNode> children = new ArrayList<>();
    public static Object parentLock = new Object();
    public static Object childLock = new Object();

    public void addChild(TreeNode child) {
        synchronized (parentLock) {
            System.out.println(Thread.currentThread().getName() + " holds lock parentLock");
            if (!children.contains(child)) {
                children.add(child);
                child.setParentOnly(this);
            }
        }
    }

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

    public static void main(String[] args) {
        TreeNode parent = new TreeNode();
        TreeNode child = new TreeNode();
        Thread thread1 = new Thread(new Task1(parent, child), "Thread1");
        Thread thread2 = new Thread(new Task2(parent, child), "Thread2");
        thread1.start();
        thread2.start();
    }
}
