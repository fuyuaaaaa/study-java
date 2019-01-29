package top.fuyuaaa.study.jedis;

import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author: fuyuaaaaa
 * @description: jedis  test 1
 * @program: study-java
 * @creat: 2018-10-24 10:33
 **/
public class JedisDemo1 {
    @Test
    /**
     * 单实例测试
     */
    public void demo1() {
        //1.设置IP地址和端口
        Jedis jedis = new Jedis("106.14.169.161", 6379);
        jedis.auth("Fuyu742423672");
        //2、保存数据
        jedis.set("name", "fuyuaaa");
        String value = jedis.get("name");
        System.out.println(value);

        //3、释放资源
        jedis.close();
    }

    @Test
    /**
     * use pool
     */
    public void demo2() {
        //获得连接池的配置对象
        JedisPoolConfig config = new JedisPoolConfig();
        //设置最大连接数
        config.setMaxTotal(30);
        //设置最大空闲连续数
        config.setMaxIdle(10);

        //获得连接池
        JedisPool jedisPool = new JedisPool(config, "106.14.169.161", 6379);
        //获得核心对象
        Jedis jedis = null;

        try {
            //通过连接池获得来凝结
            jedis = jedisPool.getResource();
            jedis.auth("Fuyu742423672");
            jedis.set("name", "fuyuaaa");
            System.out.println(jedis.get("name"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放资源
            if (jedis != null) {
                jedis.close();
            }
            if (jedisPool != null) {
                jedisPool.close();
            }
        }

    }
}
