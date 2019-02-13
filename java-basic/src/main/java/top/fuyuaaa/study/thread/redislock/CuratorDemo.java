package top.fuyuaaa.study.thread.redislock;

import org.apache.curator.CuratorZookeeperClient;
import org.apache.curator.RetryPolicy;
import org.apache.curator.RetrySleeper;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @author: fuyuaaa
 * @creat: 2019-01-30 19:34
 */
public class CuratorDemo {

    public static void main(String[] args) throws Exception {

        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient("localhost:2181", retryPolicy);
        client.start();

        InterProcessMutex mutex = new InterProcessMutex(client, "/test");
        mutex.acquire();
        System.out.println("Enter mutex");
        mutex.release();

        client.close();
    }

}
