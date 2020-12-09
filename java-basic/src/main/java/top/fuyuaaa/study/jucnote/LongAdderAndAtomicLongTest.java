package top.fuyuaaa.study.jucnote;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author : fuyuaaa
 * @date : 2020-12-09 17:02
 */
public class LongAdderAndAtomicLongTest {
    public static void main(String[] args) throws InterruptedException {
        int time = 2000;
        {
            //单线程AtomicLong累加
            long start = System.currentTimeMillis();
            AtomicLong atomicLong = new AtomicLong();
            while (System.currentTimeMillis() - start < time) {
                atomicLong.incrementAndGet();
            }
            System.out.println("单线程累加" + time + "ms,AtomicLong结果:" + atomicLong.get());

        }

        {
            //单线程LongAdder累加

            long start = System.currentTimeMillis();
            LongAdder longAdder = new LongAdder();
            while (System.currentTimeMillis() - start < time) {
                longAdder.increment();
            }
            System.out.println("单线程累加" + time + "ms,LongAdder 结果:" + longAdder.sum());

        }

        {
            //多线程AtomicLong累加
            long start = System.currentTimeMillis();
            AtomicLong atomicLong = new AtomicLong();
            CountDownLatch countDownLatch = new CountDownLatch(3);
            for (int i = 0; i < 4; i++) {
                new Thread(() -> {
                    while (System.currentTimeMillis() - start < time) {
                        atomicLong.incrementAndGet();
                    }
                    countDownLatch.countDown();

                }).start();
            }
            countDownLatch.await();
            System.out.println("多线程累加" + time + "ms,AtomicLong结果:" + atomicLong.get());

        }

        {
            //多线程LongAdder累加
            long start = System.currentTimeMillis();
            LongAdder longAdder = new LongAdder();
            CountDownLatch countDownLatch = new CountDownLatch(3);
            for (int i = 0; i < 4; i++) {
                new Thread(() -> {
                    while (System.currentTimeMillis() - start < time) {
                        longAdder.increment();
                    }
                    countDownLatch.countDown();

                }).start();
            }
            countDownLatch.await();
            System.out.println("多线程累加" + time + "ms,LongAdder 结果:" + longAdder.sum());

        }
    }
}
