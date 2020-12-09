package top.fuyuaaa.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 启动类
 * @author : fuyuaaa
 * @date : 2020-10-27 15:08
 */
@SpringBootApplication
@EnableTransactionManagement
@RestController
@EnableAspectJAutoProxy
public class DemoApplication {

    @Autowired
    RedisTemplate redisTemplate;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Autowired
    TransactionTest transactionTest;

    @RequestMapping("/test")
    public void test(){
        transactionTest.test();
        redisTemplate.opsForValue().get("123");
    }


}
