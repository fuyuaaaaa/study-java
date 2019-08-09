package top.fuyuaaa.springbootrabbitmq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.fuyuaaa.springbootrabbitmq.mq.Sender;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRabbitmqApplicationTests {

    @Autowired
    Sender sender;

    @Test
    public void contextLoads() throws Exception{
        for (int i = 0; i < 10; i++) {
            sender.send(i);
        }
        System.in.read();
    }

    @Autowired
    top.fuyuaaa.springbootrabbitmq.mq.Test test;
    @Test
    public void test2() {
        test.test();
    }
}
