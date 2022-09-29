package com.keke.demo5;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author k 2022/9/28 23:01
 */
public class MyAQS extends AbstractQueuedSynchronizer {


    @Override
    protected boolean tryAcquire(int arg) {
        return super.tryAcquire(arg);
    }


}
