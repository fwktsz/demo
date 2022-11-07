package com.keke.demo8;

import java.util.concurrent.CountDownLatch;

/**
 * @author k 2022/11/7 23:40
 */
public class CountDownLatchTest {
    static CountDownLatch c = new CountDownLatch(10);
    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(1);
                c.countDown();
                System.out.println(2);
                c.countDown();
            }
        }).start();

        new Thread(() -> {
            c.countDown();
            System.out.println(1);
        });
        c.await();
        System.out.println("3");
    }
}