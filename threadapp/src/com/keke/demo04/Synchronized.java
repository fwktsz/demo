package com.keke.demo04;

/**
 * @author k 2022/9/19 23:31
 */
public class Synchronized {
    public static void main(String[] args) {
        synchronized (Synchronized.class) {
            m();
        }
    }

    public static synchronized void m() {
    }
}
