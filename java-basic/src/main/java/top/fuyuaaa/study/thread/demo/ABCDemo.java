package top.fuyuaaa.study.thread.demo;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;

/**
 * @author : fuyuaaa
 * @date : 2020-05-15 11:21
 */
@SuppressWarnings("all")
public class ABCDemo {

    public static void main(String[] args) throws InterruptedException {
        CopyOnWriteArrayList list = new CopyOnWriteArrayList();
        list.add(new Object());
        CountDownLatch countDownLatch = new CountDownLatch(3);
        new Thread(() -> {
            while (list.size() <= 30) {
                if (list.size() % 3 == 1) {
                    System.out.println("A");
                    list.add(new Object());
                }
            }
            countDownLatch.countDown();
        }).start();

        new Thread(() -> {
            while (list.size() <= 30) {
                if (list.size() % 3 == 2) {
                    System.out.println("B");
                    list.add(new Object());
                }
            }
            countDownLatch.countDown();
        }).start();

        new Thread(() -> {
            while (list.size() <= 30) {
                if (list.size() % 3 == 0) {
                    System.out.println("C");
                    list.add(new Object());
                }
            }
            countDownLatch.countDown();
        }).start();
        countDownLatch.await();
    }


}
