package fuyuaaa.thread.redislock;

import redis.clients.jedis.Jedis;

import static fuyuaaa.thread.redislock.LockConstants.*;

/**
 * 分布式锁的简单实现
 * 存在的问题：
 * 其中一个客户端在获取锁之后，释放锁之前挂了，锁->不会被释放
 * @author: fuyuaaa
 * @creat: 2019-01-24 16:32
 */
public class LockCase1 extends RedisLock {

    public LockCase1(Jedis jedis, String lockKey) {
        super(jedis, lockKey);
    }

    @Override
    public void lock() {
        while (true) {
            String result = jedis.set(lockKey, "value", NOT_EXIST);
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
