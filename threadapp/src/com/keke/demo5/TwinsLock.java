package com.keke.demo5;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 设计一个同步工具：该工具在同一时刻，只允许至多两个线程同时访问，超过两个线程的
 * 访问将被阻塞，我们将这个同步工具命名为TwinsLock。
 * <p>
 * 首先，确定访问模式。TwinsLock能够在同一时刻支持多个线程的访问，这显然是共享式
 * 访问，因此，需要使用同步器提供的acquireShared(int args)方法等和Shared相关的方法，这就要
 * 求TwinsLock必须重写tryAcquireShared(int args)方法和tryReleaseShared(int args)方法，这样才能
 * 保证同步器的共享式同步状态的获取与释放方法得以执行。
 *
 * @author k 2022/10/14 21:56
 */
public class TwinsLock {

    private final Sync sync = new Sync(2);

    private static final class Sync extends AbstractQueuedSynchronizer {
        Sync(int count) {
            if (count <= 0) {
                throw new IllegalArgumentException("count must large than zero.");
            }
            setState(count);
        }
    }


}
