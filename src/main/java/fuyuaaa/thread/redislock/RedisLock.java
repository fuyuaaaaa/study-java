package fuyuaaa.thread.redislock;

import redis.clients.jedis.Jedis;

import java.util.Collections;
import java.util.UUID;

/**
 * @author: fuyuaaa
 * @creat: 2019-01-28 13:59
 */
public class RedisLock {

    private ThreadLocal<String> tokenMap = new ThreadLocal<>();

    private Jedis jedis;

    private boolean isFlushLeaseTime = false;

    public RedisLock(Jedis jedis) {
        this.jedis = jedis;
    }

    public void lock(String key) {
        lock(key,10,10);
    }

    public void lock(String key, Integer waitTime, Integer leaseTime) {
        tryLock(key, waitTime, leaseTime);
    }

    public boolean tryLock(String key, Integer leaseTime) {
        String result = jedis.set(key, getToken(), "NX", "EX", leaseTime);
        System.out.println(result);
        return "O".equals(result);
    }

    public boolean tryLock(String key, Integer waitTime, Integer leaseTime) {
        String jedisToken = jedis.get(key);
        if (null != jedisToken && jedisToken.equals(getToken())) {
            System.out.println("重入锁成功 ：" + getToken());
            return true;
        }
        long startTime = System.currentTimeMillis();
        while ((System.currentTimeMillis() - startTime) / 1000 < waitTime) {
            String result = jedis.set(key, getToken(), "NX", "EX", leaseTime);
            if ("OK".equals(result)) {
                System.out.println("获取锁成功" + getToken());
                isFlushLeaseTime = true;
                flushLeaseTime(key, getToken(), leaseTime);
                return true;
            }
        }
        return false;
    }

    public void unlock(String key) {
        String delScript = "local r = redis.call('get',KEYS[1])\n" +
                "if r == ARGV[1]\n" +
                "  then\n" +
                "  redis.call('del',KEYS[1])\n" +
                "  return true\n" +
                "end \n" +
                "return false";
        Long result = (Long) jedis.eval(delScript, Collections.singletonList(key), Collections.singletonList(getToken()));
        if (null != result && result > 0) {
            System.out.println("remove tokenMap : " + tokenMap.get());
            isFlushLeaseTime = false;
            tokenMap.remove();
        }
    }

    private void flushLeaseTime(String key, String value, Integer leaseTime) {
        System.out.println(leaseTime.toString());
        new Thread(() -> {
            while (isFlushLeaseTime) {
                try {
                    Thread.sleep(leaseTime / 4 * 3 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String flushScript = "if redis.call('get', KEYS[1]) == ARGV[1] then " +
                        "return redis.call('expire',KEYS[1],ARGV[2]) " +
                        "else " +
                        "return 0 end";
                Long result = (Long) jedis.eval(flushScript, 1, key, value, leaseTime.toString());
                if (1 == result) {
                    System.out.println("刷新过期时间成功" + value);
                }
            }
        }).start();
    }

    private String getToken() {
        String token = tokenMap.get();
        if (null == token || token.length() < 1) {
            token = UUID.randomUUID().toString().replaceAll("-", "");
            tokenMap.set(token);
        }
        return token;
    }
}
