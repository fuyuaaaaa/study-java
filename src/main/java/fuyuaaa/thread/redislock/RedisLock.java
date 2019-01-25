package fuyuaaa.thread.redislock;

import redis.clients.jedis.Jedis;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author: fuyuaaa
 * @creat: 2019-01-24 16:29
 */
public abstract class RedisLock implements Lock {
    Jedis jedis;
    String lockKey;
    String lockValue;
    boolean isOpenFlushLeaseTime = false;

    RedisLock(Jedis jedis, String lockKey, String lockValue) {
        this.jedis = jedis;
        this.lockKey = lockKey;
        this.lockValue = lockValue;
    }


    void openFlushLeaseTime() {
        new Thread(() -> {
            while (isOpenFlushLeaseTime) {
                System.out.println("执行延迟失效时间中...");

                String checkAndExpireScript = "if redis.call('get', KEYS[1]) == ARGV[1] then " +
                        "return redis.call('expire',KEYS[1],ARGV[2]) " +
                        "else " +
                        "return 0 end";
                jedis.eval(checkAndExpireScript, 1, lockKey, lockValue, "30");

                sleepBySecond(10);

            }
        }).start();
    }

    void sleepBySecond(int second) {
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void lock() {

    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {

    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
