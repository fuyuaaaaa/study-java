package top.fuyuaaa.study.thread.Lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * TwinsLock: 在同一时刻，只允许之多两个线程同时访问，超过两个线程的访问将被阻塞。
 * <p>
 * 两个线程，定义资源数 state = 2
 */
public class TwinsLock {

    private static final class Sync extends AbstractQueuedSynchronizer {
        // 初始化同步器，设置资源数=2
        Sync(int count) {
            if (count <= 0) {
                //error
                throw new IllegalArgumentException();
            }
            setState(2);
        }

        @Override
        protected int tryAcquireShared(int reduceCount) {
            //自旋
            for (; ; ) {
                //当前资源数量
                int current = getState();
                int newCount = current - reduceCount;
                //如果当前资源数量不够获取，直接返回负数
                //如果够的话，尝试CAS
                if (newCount < 0 || compareAndSetState(current, newCount)) {
                    return newCount;
                }
            }
        }

        @Override
        protected boolean tryReleaseShared(int returnCount) {
            //自旋
            for (; ; ) {
                int current = getState();
                int newCount = current + returnCount;
                if (compareAndSetState(current, newCount)) {
                    return true;
                }
            }
        }
    }

    private Sync sync = new Sync(2);

    public void lock() {
        sync.acquireShared(1);
    }

    public void unlock() {
        sync.releaseShared(1);
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        TwinsLock twinsLock = new TwinsLock();
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                twinsLock.lock();
                try {
                    System.out.println(Thread.currentThread().getId() +" 获取到锁");
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                twinsLock.unlock();
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
    }
}
