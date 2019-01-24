package fuyuaaa.thread.redislock;

import redis.clients.jedis.Jedis;

import java.util.UUID;

import static fuyuaaa.thread.redislock.LockConstants.*;

/**
 * @author: fuyuaaa
 * @creat: 2019-01-24 16:40
 */
public class LockCase3 extends RedisLock {

    private String lockValue = UUID.randomUUID().toString() + Thread.currentThread().getId();

    LockCase3(Jedis jedis, String lockKey) {
        super(jedis, lockKey);
    }

    @Override
    public void lock() {
        while (true) {
            String result = jedis.set(lockKey, lockValue, NOT_EXIST, SECONDS, 10);
            if (OK.equals(result)) {
                System.out.println(Thread.currentThread().getId() + " lock successfully");
                break;
            }
        }
    }

    @Override
    public void unlock() {
        String value = jedis.get(lockKey);
        if (lockValue.equals(value)) {
            jedis.del(lockKey);
        }

        //上述逻辑存在类似i++的问题
        //使用lua脚本避免上述问题
        String checkAndDelScript = "if redis.call('get', KEYS[1]) == ARGV[1] then "
                + "return redis.call('del', KEYS[1]) "
                + "else "
                + "return 0 "
                + "end";
        jedis.eval(checkAndDelScript, 1, lockKey, lockValue);
    }
}
