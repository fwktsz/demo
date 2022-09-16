package com.keke.demo01;

/**
 * @author k 2022/9/16 22:13
 */
public class VolatileMain {


    public static void main(String[] args) {
        VolatileFeaturesExample a = new VolatileFeaturesExample();
        Thread t1 = new Thread(() -> {
            a.set(3);
        });
        Thread t2 = new Thread(() -> {
            a.getAndIncrement();
        });
        Thread t3 = new Thread(() -> {
            a.get();
            System.out.println(a);
        });
        t1.start();
        t2.start();
        t3.start();


    }
}


class VolatileFeaturesExample {
    volatile long vl = 0L;    //使用volatile声明64位的long型变量

    public void set(long l) {
        vl = l;        // 单个volatile变量的写
    }

    public void getAndIncrement() {
        vl++;// 复合（多个）volatile变量的读/写
    }

    public long get() {
        return vl; // 单个volatile变量的读
    }
}