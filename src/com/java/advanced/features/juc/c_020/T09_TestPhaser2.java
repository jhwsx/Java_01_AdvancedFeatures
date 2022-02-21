package com.java.advanced.features.juc.c_020;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * Phaser 是在 1.7 添加的
 *
 * @author wangzhichao
 * @since 2020/3/31
 */
public class T09_TestPhaser2 {
    static Random r = new Random();
    static MarriagePhaser phaser = new MarriagePhaser();
    static void milliSleep(int milli) {
        try {
            TimeUnit.MILLISECONDS.sleep(milli);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // 动态添加多个 parties
        phaser.bulkRegister(7);
        for (int i = 0; i < 5; i++) {
            new Thread(new Person("第" + i + "波儿亲朋好友")).start();
        }
        new Thread(new Person("新郎")).start();
        new Thread(new Person("新娘")).start();
    }
    static class MarriagePhaser extends Phaser {
        @Override
        protected boolean onAdvance(int phase, int registeredParties) {
            switch (phase) {
                case 0:
                    System.out.println("所有人都到齐了！" + registeredParties);
                    System.out.println();
                    return false;
                case 1:
                    System.out.println("所有人都吃完了！" + registeredParties);
                    System.out.println();
                    return false;
                case 2:
                    System.out.println("所有人都离开了！" + registeredParties);
                    System.out.println();
                    return false;
                case 3:
                    System.out.println("婚礼结束！新郎新娘抱抱！" + registeredParties);
                    return true;
                default:
                    return true;
            }
        }
    }

    static class Person implements Runnable {
        String name;

        public Person(String name) {
            this.name = name;
        }

        public void arrive() {
            milliSleep(r.nextInt(1000));
            System.out.printf("%s 到达现场！\n", name);
            // 到达并等待其他线程到达
            phaser.arriveAndAwaitAdvance();
        }

        public void eat() {
            milliSleep(r.nextInt(1000));
            System.out.printf("%s 吃完！\n", name);
            // 到达并等待其他线程到达
            phaser.arriveAndAwaitAdvance();
        }

        public void leave() {
            milliSleep(r.nextInt(1000));
            System.out.printf("%s 离开！\n", name);
            // 到达并等待其他线程到达
            phaser.arriveAndAwaitAdvance();
        }

        private void hug() {
            if (name.equals("新郎") || name.equals("新娘")) {
                milliSleep(r.nextInt(1000));
                System.out.printf("%s 洞房！\n", name);
                // 到达并等待其他线程到达
                phaser.arriveAndAwaitAdvance();
            } else {
                // 到达并注销该parties，这个方法不会使线程阻塞
                phaser.arriveAndDeregister();

            }
        }
        @Override
        public void run() {
            arrive();
            eat();
            leave();
            hug();
        }
    }

}
