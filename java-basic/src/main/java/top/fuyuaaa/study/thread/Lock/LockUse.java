package top.fuyuaaa.study.thread.Lock;

import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@SuppressWarnings("all")
public class LockUse {
    @Test
    public void lockUse() {

        Lock lock = new ReentrantLock();
        lock.lock();
        try {
            //业务逻辑
        } catch (Throwable e) {
            //异常处理
        } finally {

        }
        lock.unlock();

    }

    @Test
    public void lovkInterruptibly() throws InterruptedException {

        Lock lock = new ReentrantLock();
        //先获取锁，让aThread阻塞
        lock.lockInterruptibly();

        Thread aThread = new Thread(() -> {
            try {
                //会被阻塞
                lock.lockInterruptibly();
            } catch (InterruptedException e) {
                System.out.println("aThread 被 主线程 中断");
            }
        });
        aThread.start();

        Thread.sleep(1000);
        //让aThread中断
        aThread.interrupt();
        Thread.sleep(2000);

        lock.unlock();
    }

    @Test
    public void readWritreLockUse() throws InterruptedException {
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
        CountDownLatch countDownLatch = new CountDownLatch(7);

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                readLock.lock();
                System.out.println(Thread.currentThread().getId() + " 获取到读锁");
                countDownLatch.countDown();
                readLock.unlock();
            }).start();
        }

        for (int i = 0; i < 2; i++) {
             new Thread(() -> {
                writeLock.lock();
                try {
                    System.out.println(Thread.currentThread().getId() + " 获取到写锁");
                    Thread.sleep(2000);
                    writeLock.unlock();
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        countDownLatch.await();
    }
}
