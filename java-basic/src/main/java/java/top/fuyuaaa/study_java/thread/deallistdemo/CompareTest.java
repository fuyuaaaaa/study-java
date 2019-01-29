package java.top.fuyuaaa.study_java.thread.deallistdemo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author: fuyuaaaaa
 * @creat: 2019-01-14 10:08
 */
public class CompareTest {

    private final static long COUNT = 100000L;

    public static void main(String[] args) {

        single();
        multi();
    }

    public static void single() {
        Long start = System.currentTimeMillis();
        final List<Integer> list = new LinkedList<Integer>();

        Thread thread = new Thread(() -> {
            for (int i = 0; i < COUNT; i++) {
                list.add(i);
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(list.size());
        System.out.println((System.currentTimeMillis() - start) + "毫秒");
    }

    public static void multi() {
        Long start = System.currentTimeMillis();

        ExecutorService executorService = new ThreadPoolExecutor(1, 4, 10L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(10), new ThreadPoolExecutor.AbortPolicy());

        final List<Integer> list = new LinkedList<Integer>();
        Runnable task = null;
        for (int i = 0; i < 1; i++) {
            task = () -> {
                for (int j = 0; j < COUNT; j++) {
                    list.add(j);
                }
            };
            executorService.execute(task);
        }

        executorService.shutdown();

        while (true) {
            if (executorService.isTerminated()) {
                System.out.println(list.size());
                System.out.println((System.currentTimeMillis() - start) + "毫秒(multi)");
                break;
            }
        }
    }
}
