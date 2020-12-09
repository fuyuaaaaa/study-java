package top.fuyuaaa.study.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : fuyuaaa
 * @date : 2020-04-28 14:38
 */
public class test {
    public static void main(String[] args)throws Exception{
        ReentrantLock lock = new ReentrantLock();
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                lock.lock();
                try {
                    Thread.sleep(600 * 1000);
                } catch (Exception e) {
                } finally {
                    lock.unlock();
                    countDownLatch.countDown();
                }
            }).start();
        }
        countDownLatch.await();
    }

    public static class xx extends AbstractQueuedSynchronizer{
        @Override
        protected boolean tryAcquire(int arg) {
            return super.tryAcquire(arg);
        }

        @Override
        protected boolean tryRelease(int arg) {
            return super.tryRelease(arg);
        }

        @Override
        protected int tryAcquireShared(int arg) {
            return super.tryAcquireShared(arg);
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            return super.tryReleaseShared(arg);
        }
    }
}
