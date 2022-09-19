package com.keke.demo04.synchronizeddemo;

/**
 * 不知道在写啥的demo
 * @author k 2022/9/19 23:50
 */
public class synchronizedPro {

    public static void main(String[] args) throws InterruptedException {

        Thread synDemo1 = new Thread(new SynDemo1(), "SynDemo1");
        Thread synDemo2 = new Thread(new SynDemo2(), "SynDemo2");
        Thread synDemo3 = new Thread(new SynDemo3(), "SynDemo3");
        synDemo1.start();
        synDemo2.start();
        synDemo3.start();

        synDemo2.join();
        synDemo3.join();
        synDemo1.join();
    }


    static class SynDemo1 implements Runnable {
        @Override
        public void run() {
            synchronized (synchronizedPro.class) {
                System.out.println(1);
            }

        }
    }

    static class SynDemo2 implements Runnable {
        @Override
        public void run() {
            synchronized (synchronizedPro.class) {
                System.out.println(2);
            }
        }
    }

    static class SynDemo3 implements Runnable {
        @Override
        public void run() {
            synchronized (synchronizedPro.class) {
                System.out.println(3);
            }
        }
    }


}
