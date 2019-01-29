package java.top.fuyuaaa.study_java.thread.waittimeout;

import java.sql.Connection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: fuyuaaaaa
 * @creat: 2018-12-19 23:07
 */
public class ConnectionPoolTest {
    static ConnectionPool pool = new ConnectionPool(10);
    //保证所有ConnectionRunner能够同时开始
    static CountDownLatch start = new CountDownLatch(1);
    //main 线程将会等待所有ConnectionRunner结束后才能继续执行
    static CountDownLatch end;

    public static void main(String[] args) throws InterruptedException {
        int threadCount = 100;
        end = new CountDownLatch(threadCount);

        int count = 20;
        AtomicInteger got = new AtomicInteger();
        AtomicInteger notGot = new AtomicInteger();

        for (int i = 0; i < threadCount; i++) {
            Thread thread = new Thread(new ConnectionRunner(count, got, notGot),
                    "ConnectionRunnerThread" + i);
            thread.start();
        }
        start.countDown();
        end.await();
        System.out.println("total" + (threadCount * count));
        System.out.println("got" + got);
        System.out.println("notGot" + notGot);
    }

    static class ConnectionRunner implements Runnable {

        int count;
        AtomicInteger got;
        AtomicInteger notGot;

        public ConnectionRunner(int count, AtomicInteger got, AtomicInteger notGot) {
            this.count = count;
            this.got = got;
            this.notGot = notGot;
        }

        @Override
        public void run() {
            try {
                start.await();
            } catch (InterruptedException e) {
            }

            while (count > 0) {
                try {
                    //从连接池中获取连接，如果1000ms内无法获取到，将会返回null
                    //分别统计获取到的（got），没获取到的（notGot）
                    Connection connection = pool.fetchConnection(1000);
                    if (null != connection) {
                        try {
                            connection.createStatement();
                            connection.commit();
                        } finally {
                            pool.releaseConnection(connection);
                            got.incrementAndGet();
                        }
                    } else {
                        notGot.incrementAndGet();
                    }
                } catch (Exception e) {

                } finally {
                    count--;
                }
            }
            end.countDown();
        }
    }
}
