package top.fuyuaaa.consumer;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.fuyuaaa.api.TestService;

/**
 * @author: fuyuaaaaa
 * @creat: 2018-12-12 23:33
 */
public class Consumer {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"/dubbo-demo-consumer.xml"});
        context.start();
        TestService testService = (TestService) context.getBean("testService"); // get remote services proxy
        while (true) {
            try {
                Thread.sleep(1000);
                String hello = testService.demo("world111"); // call remote method
                System.out.println(hello); // get result
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }
}
