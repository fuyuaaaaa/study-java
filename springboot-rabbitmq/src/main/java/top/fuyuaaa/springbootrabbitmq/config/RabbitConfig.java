package top.fuyuaaa.springbootrabbitmq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description :  
 * @author : fuyuaaa
 * @create : 2019-08-06 16:02
 */
@Configuration
public class RabbitConfig {

    @Bean
    public Queue Queue() {
        return new Queue("fuyu");
    }
}
