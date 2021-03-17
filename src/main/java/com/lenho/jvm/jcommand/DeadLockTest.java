package com.lenho.jvm.jcommand;

/**
 * jps
 * jstack -l pid
 *
 *   Found one Java-level deadlock:
 * =============================
 * "mythread-tianluo":
 *   waiting for ownable synchronizer 0x00000000d6267c80, (a java.util.concurrent.locks.ReentrantLock$NonfairSync),
 *   which is held by "mythread-jay"
 * "mythread-jay":
 *   waiting for ownable synchronizer 0x00000000d6267cb0, (a java.util.concurrent.locks.ReentrantLock$NonfairSync),
 *   which is held by "mythread-tianluo"
 *
 * Java stack information for the threads listed above:
 * ===================================================
 * "mythread-tianluo":
 *         at sun.misc.Unsafe.park(Native Method)
 *         - parking to wait for  <0x00000000d6267c80> (a java.util.concurrent.locks.ReentrantLock$NonfairSync)
 *         at java.util.concurrent.locks.LockSupport.park(LockSupport.java:175)
 *         at java.util.concurrent.locks.AbstractQueuedSynchronizer.parkAndCheckInterrupt(AbstractQueuedSynchronizer.java:836)
 *         at java.util.concurrent.locks.AbstractQueuedSynchronizer.acquireQueued(AbstractQueuedSynchronizer.java:870)
 *         at java.util.concurrent.locks.AbstractQueuedSynchronizer.acquire(AbstractQueuedSynchronizer.java:1199)
 *         at java.util.concurrent.locks.ReentrantLock$NonfairSync.lock(ReentrantLock.java:209)
 *         at java.util.concurrent.locks.ReentrantLock.lock(ReentrantLock.java:285)
 *         at com.lenho.jvm.jcommand.DeadLockTest$2.run(DeadLockTest.java:40)
 * "mythread-jay":
 *         at sun.misc.Unsafe.park(Native Method)
 *         - parking to wait for  <0x00000000d6267cb0> (a java.util.concurrent.locks.ReentrantLock$NonfairSync)
 *         at java.util.concurrent.locks.LockSupport.park(LockSupport.java:175)
 *         at java.util.concurrent.locks.AbstractQueuedSynchronizer.parkAndCheckInterrupt(AbstractQueuedSynchronizer.java:836)
 *         at java.util.concurrent.locks.AbstractQueuedSynchronizer.acquireQueued(AbstractQueuedSynchronizer.java:870)
 *         at java.util.concurrent.locks.AbstractQueuedSynchronizer.acquire(AbstractQueuedSynchronizer.java:1199)
 *         at java.util.concurrent.locks.ReentrantLock$NonfairSync.lock(ReentrantLock.java:209)
 *         at java.util.concurrent.locks.ReentrantLock.lock(ReentrantLock.java:285)
 *         at com.lenho.jvm.jcommand.DeadLockTest$1.run(DeadLockTest.java:26)
 *
 * Found 1 deadlock.
 *
 *
 * @author langyonghe
 * @date 2020/12/17 9:33
 */

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Java 死锁demo
 */
public class DeadLockTest {
    private static Lock lock1 = new ReentrantLock();
    private static Lock lock2 = new ReentrantLock();

    public static void deathLock() {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                try {
                    lock1.lock();
                    System.out.println(Thread.currentThread().getName() + " get the lock1");
                    Thread.sleep(1000);
                    lock2.lock();
                    System.out.println(Thread.currentThread().getName() + " get the lock2");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread t2 = new Thread() {
            @Override
            public void run() {
                try {
                    lock2.lock();
                    System.out.println(Thread.currentThread().getName() + " get the lock2");
                    Thread.sleep(1000);
                    lock1.lock();
                    System.out.println(Thread.currentThread().getName() + " get the lock1");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        //设置线程名字，方便分析堆栈信息
        t1.setName("mythread-jay");
        t2.setName("mythread-tianluo");
        t1.start();
        t2.start();
    }
    public static void main(String[] args) {
        deathLock();
    }
}

