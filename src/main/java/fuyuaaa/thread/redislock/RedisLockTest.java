package fuyuaaa.thread.redislock;

import redis.clients.jedis.Jedis;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: fuyuaaa
 * @creat: 2019-01-25 14:14
 */
public class RedisLockTest {

    static {

    }

    public static void main(String[] args) {
        ThreadPoolExecutor poolExecutor =
                new ThreadPoolExecutor(10, 10, 1, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));

        for (int i = 0; i < 10; i++) {
            poolExecutor.execute(() -> {
                Jedis jedis = new Jedis("106.14.169.161", 6379);
                jedis.auth("Fuyu742423672");
                jedis.connect();
                try {
                    RedisLockFullV lock = new RedisLockFullV(jedis, "test", String.valueOf(Thread.currentThread().getId()));
                    lock.lock();
                    lock.sleepBySecond(15);
                    lock.unlock();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    jedis.disconnect();
                }
            });
        }
        while (poolExecutor.getActiveCount() == 0) {
            poolExecutor.shutdown();
        }

    }
}
