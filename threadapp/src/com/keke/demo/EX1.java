package com.keke.demo;

/**
 * @author k 2022/11/7 23:23
 */
public class EX1 {


    public static void main(String[] args) {

        String s = "1234567890";
        String b = "abcdefghij";


        Object lock = new Object();

        Thread t1 = new Thread(() -> {
            char[] chars = s.toCharArray();
            synchronized (lock) {
                for (char aChar : chars) {
                    System.out.print(aChar);
                    try {
                        lock.notify();
                        lock.wait();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }


        });

        Thread t2 = new Thread(() -> {
            char[] chars = b.toCharArray();
            synchronized (lock) {
                for (char aChar : chars) {
                    System.out.print(aChar);
                    try {
                        lock.notify();
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }

        });
        t2.start();
        t1.start();

    }









}



