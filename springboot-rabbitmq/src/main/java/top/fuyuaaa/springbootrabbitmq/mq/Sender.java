package top.fuyuaaa.springbootrabbitmq.mq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description :  
 * @author : fuyuaaa
 * @create : 2019-08-06 16:04
 */
@Component
public class Sender {
    @Autowired
    AmqpTemplate rabbitTemplate;

    public void send(Integer i){

        String context = "hello " + i;
        System.out.println("Sender : " + context);
        rabbitTemplate.convertAndSend("fuyu", context);
    }
}
