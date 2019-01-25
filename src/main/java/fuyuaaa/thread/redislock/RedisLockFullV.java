package fuyuaaa.thread.redislock;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

import java.time.LocalTime;
import java.util.UUID;

import static fuyuaaa.thread.redislock.LockConstants.*;

/**
 * @author: fuyuaaa
 * @creat: 2019-01-25 13:52
 */
public class RedisLockFullV extends RedisLock {

    private static ThreadLocal<String> tokenMap = new ThreadLocal<>();

    RedisLockFullV(Jedis jedis, String lockKey, String lockValue) {
        super(jedis, lockKey, UUID.randomUUID().toString() + lockValue);
    }

    @Override
    public void lock() {
        while (true) {
            String result = jedis.set(lockKey, lockValue, NOT_EXIST, SECONDS, 30);
            if (OK.equals(result)) {
                System.out.println("线程id:" + Thread.currentThread().getId() + "加锁成功!时间:" + LocalTime.now());
                isOpenFlushLeaseTime = true;
                openFlushLeaseTime();
                break;
            }
            System.out.println("线程id:" + Thread.currentThread().getId() + "获取锁失败，休眠10秒!时间:" + LocalTime.now());
            sleepBySecond(10);
        }
    }

    @Override
    public void unlock() {
        String compareAndDeleteScript = "if redis.call(\"get\",KEYS[1]) == ARGV[1] then\n" +
                "    return redis.call(\"del\",KEYS[1])\n" +
                "else\n" +
                "    return 0\n" +
                "end";
        do {
            Long l = (Long) jedis.eval(compareAndDeleteScript, 1, lockKey, lockValue);
            if (l == 1) {
                notifyAll();
                System.out.println("线程id:" + Thread.currentThread().getId() + "释放锁!");
                isOpenFlushLeaseTime = false;
                break;
            }
        } while (true);
    }
}
