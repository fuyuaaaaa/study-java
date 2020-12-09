package top.fuyuaaa.for_zwy;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @author : fuyuaaa
 * @date : 2020-09-06 10:47
 */
@Component
public class CustomerApplicationEventListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("spring容器加载完成。");
    }
}
