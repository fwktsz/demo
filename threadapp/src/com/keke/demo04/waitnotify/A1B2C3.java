package com.keke.demo04.waitnotify;

/**
 * @author k 2022/9/21 22:45
 */
public class A1B2C3 {

    static Object lock = new Object();

    static String a = "abcdefg";

    static String b = "1234567";

    static boolean flag = true;


    public static void main(String[] args) {

        Thread zimu = new Thread(new Zimu(), "zimu");
        Thread shuzu = new Thread(new Shuzi(), "shuzu");
        zimu.start();
        shuzu.start();

    }

    static class Zimu implements Runnable {
        static char[] charsa;

        static {
            charsa = a.toCharArray();
        }

        @Override
        public void run() {
            synchronized (lock) {
                for (char c : charsa) {
                    System.out.println(c);
                    try {
                        lock.notify();
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }
            }
        }
    }

    static class Shuzi implements Runnable {
        static char[] charsa;

        static {
            charsa = b.toCharArray();
        }

        @Override
        public void run() {
            synchronized (lock) {
                for (char c : charsa) {
                    System.out.println(c);
                    try {
                        lock.notify();
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }


}
