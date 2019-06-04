package top.fuyuaaa.study.jedis;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

/**
 * @author: fuyuaaa
 * @creat: 2019-03-12 23:15
 */
public class JedisBasicUseDemo {

    Jedis jedis;

    @BeforeEach
    void connect(){
        jedis = new Jedis("106.14.169.161", 6379);
        jedis.auth("Fuyu742423672");
    }

    @Test
    void basicUse(){
        String res = jedis.set("hello", "fuyuaaa");
        String value = jedis.get("hello");
        System.out.println(res);
        System.out.println(value);
    }


    @AfterEach
    void disConnect(){
        jedis.close();
    }
}
