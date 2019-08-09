package top.fuyuaaa.springbootrabbitmq.mq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @description :  
 * @author : fuyuaaa
 * @create : 2019-08-08 10:02
 */
@Component
public class Test {

    @Autowired
    ApplicationContext applicationContext;

    public void test(){
        new Thread(()-> applicationContext.publishEvent(new OrderEvent(1,"nihao"))).start();
        System.out.println("====");
    }
}
