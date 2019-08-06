package top.fuyuaaa.springbootrabbitmq.mq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @description :  
 * @author : fuyuaaa
 * @create : 2019-08-06 16:05
 */
@Component
@RabbitListener(queues = "fuyu")
public class Receiver {
    @RabbitHandler
    public void process(String msg) {
        System.out.println("Receiver 1   : " + msg);
    }
}
