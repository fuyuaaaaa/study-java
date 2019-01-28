package fuyuaaa.thread.redislock;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: fuyuaaa
 * @creat: 2019-01-28 14:06
 */
public class RedisLockTest {

    private static final ExecutorService EXECUTOR_SERVICE =
            new ThreadPoolExecutor(4, 10, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));

    public static void main(String[] args) {
        JedisPool jedisPool = new JedisPool(
                new GenericObjectPoolConfig(), "106.14.169.161", 6379, 1000, "Fuyu742423672");
        for (int i = 0; i < 4; i++) {
            EXECUTOR_SERVICE.execute(()->{
                System.out.println("初始化..");
                Jedis jedis = jedisPool.getResource();
                RedisLock lock = new RedisLock(jedis);
                String key = "TEST_KEY";
                lock.lock(key,20,10);
                try {
                    Thread.sleep(15 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.unlock(key);
                jedis.close();
            });
        }
        EXECUTOR_SERVICE.shutdown();
    }
}
