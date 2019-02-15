package top.fuyuaaa.study.thread.redislock;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: fuyuaaa
 * @creat: 2019-01-30 19:34
 */
public class CuratorDemo {

    public static void main(String[] args) throws Exception {

        //重试机制
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        //客户端
        CuratorFramework client = CuratorFrameworkFactory.newClient("localhost:2181", retryPolicy);
        client.start();

        //线程池
        ThreadPoolExecutor threadPoolExecutor =
                new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors() * 2, 10, 10,
                        TimeUnit.SECONDS, new LinkedBlockingQueue<>(1000),
                        //com.google.guava的ThreadFactoryBuilder
                        new ThreadFactoryBuilder().setNameFormat("test-pool-%d").build());
        for (int i = 0; i < 5; i++) {
            threadPoolExecutor.execute(() -> {
                //分布式可重入排它锁

                InterProcessMutex mutex = new InterProcessMutex(client, "/test");
                try {
                    String threadName = Thread.currentThread().getName();
                    System.out.println("创建线程："+threadName);

                    mutex.acquire();
                    System.out.println(threadName + " 加锁成功！");
                    Thread.sleep(1000);

                    mutex.acquire();
                    System.out.println(threadName + " 重入锁成功！");
                    Thread.sleep(1000);

                    mutex.release();
                    mutex.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        threadPoolExecutor.shutdown();
        if (threadPoolExecutor.isTerminated()) {
            client.close();
        }
    }
}
