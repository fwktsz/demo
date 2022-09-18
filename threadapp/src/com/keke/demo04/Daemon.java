package com.keke.demo04;

import com.keke.utils.SleepUtils;

/**
 * @author k 2022/9/17 0:04
 */
public class Daemon {
    public static void main(String[] args) {
        Thread daemonRunner = new Thread(new DaemonRunner(), "DaemonRunner");
        daemonRunner.setDaemon(true);
        daemonRunner.start();
        System.out.println("------------");
    }
}


class DaemonRunner implements Runnable {

    @Override
    public void run() {
        try {
            SleepUtils.second(110000L);
        } finally {
            System.out.println("DaemonThread finally run.");
        }
    }
}
