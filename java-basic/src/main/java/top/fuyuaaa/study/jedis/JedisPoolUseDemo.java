package top.fuyuaaa.study.jedis;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author: fuyuaaa
 * @creat: 2019-03-13 22:10
 */
@Slf4j
public class JedisPoolUseDemo {
    GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
    JedisPool jedisPool = new JedisPool(poolConfig, "106.14.169.161", 6379);

    @Test
    void jedisPoolTest() {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String value = jedis.get("hello");
            System.out.println(value);
        } catch (Exception e) {
            log.error("jedis pool failed , e: {}", e);
        } finally {
            jedis.close();
        }
    }
}
