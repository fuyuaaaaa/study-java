package fuyuaaa.thread.redislock;

import redis.clients.jedis.Jedis;

import java.util.UUID;

import static fuyuaaa.thread.redislock.LockConstants.*;

/**
 * 设置锁的过期时间
 * 存在的问题：
 * A、B两个客户端，设置超时时间10s
 * A获取锁，处理时间超过10s,锁释放
 * B获取锁，A处理完，释放了B的锁
 *
 * @author: fuyuaaa
 * @creat: 2019-01-24 16:40
 */
public class LockCase2 extends RedisLock {
    LockCase2(Jedis jedis, String lockKey) {
        super(jedis, lockKey);
    }

    @Override
    public void lock() {
        while (true) {
            String result = jedis.set(lockKey, "value", NOT_EXIST, SECONDS, 10);
            if (OK.equals(result)) {
                System.out.println(Thread.currentThread().getId() + " lock successfully");
                break;
            }
        }
    }

    @Override
    public void unlock() {
        jedis.del(lockKey);
    }
}
