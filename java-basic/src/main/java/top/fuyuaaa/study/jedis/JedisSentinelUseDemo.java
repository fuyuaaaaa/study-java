package top.fuyuaaa.study.jedis;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: fuyuaaa
 * @creat: 2019-03-24 15:19
 */
public class JedisSentinelUseDemo {

    JedisSentinelPool jedisSentinelPool;

    @BeforeEach
    public  void init(){
        Set<String> sentinelSet = new HashSet<>();
        sentinelSet.add("127.0.0.1:26379");
        sentinelSet.add("127.0.0.1:26380");
        sentinelSet.add("127.0.0.1:26381");
        jedisSentinelPool =
                new JedisSentinelPool("mymaster", sentinelSet);
    }
    @Test
    void testJedisSentinelPool(){
        Jedis jedis = jedisSentinelPool.getResource();
        System.out.println(jedisSentinelPool.getCurrentHostMaster());
        jedis.close();
    }

}
