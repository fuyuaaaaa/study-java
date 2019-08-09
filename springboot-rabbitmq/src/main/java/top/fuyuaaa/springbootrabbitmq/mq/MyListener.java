package top.fuyuaaa.springbootrabbitmq.mq;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @description :  
 * @author : fuyuaaa
 * @create : 2019-08-08 10:07
 */
@Component
public class MyListener implements ApplicationListener<OrderEvent> {

    /**
     * Handle an application event.
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(OrderEvent event) {
        System.out.println(event.getMsg());
    }
}
