package top.fuyuaaa.springbootrabbitmq.mq;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * @description :  
 * @author : fuyuaaa
 * @create : 2019-08-08 10:03
 */
public class OrderEvent extends ApplicationEvent {

    @Getter
    private String msg;

    /**
     * Create a new ApplicationEvent.
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public OrderEvent(Object source,String msg) {
        super(source);
        this.msg = msg;
    }

}
