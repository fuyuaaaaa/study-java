package top.fuyuaaa.jms.consumer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: fuyuaaaaa
 * @description:
 * @program: study-java
 * @creat: 2018-12-04 14:10
 **/
public class AppConsumer {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("consumer.xml");
    }
}
