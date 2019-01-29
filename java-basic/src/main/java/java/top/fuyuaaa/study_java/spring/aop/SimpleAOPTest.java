package java.top.fuyuaaa.study_java.spring.aop;

import org.junit.jupiter.api.Test;

/**
 * @author: fuyuaaaaa
 * @description:
 * @program: study
 * @creat: 2018-08-14 14:42
 **/
public class SimpleAOPTest {
    @Test
    public void getProxy() throws Exception{
        // 1. 创建一个 MethodInvocation 实现类
        MethodInvocation logTask = () -> System.out.println("log task start");
        HelloServiceImpl helloServiceImpl = new HelloServiceImpl();

        // 2. 创建一个 Advice
        Advice beforeAdvice = new BeforeAdvice(helloServiceImpl, logTask);

        // 3. 为目标对象生成代理
        HelloService helloServiceImplProxy = (HelloService) SimpleAOP.getProxy(helloServiceImpl, beforeAdvice);

        helloServiceImplProxy.sayHelloWorld();
    }
}
