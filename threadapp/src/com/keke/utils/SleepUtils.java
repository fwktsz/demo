package com.keke.utils;

import java.util.concurrent.TimeUnit;

/**
 * @author k 2022/9/17 0:08
 */
public class SleepUtils {
    public static final void second(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
        }
    }
}
