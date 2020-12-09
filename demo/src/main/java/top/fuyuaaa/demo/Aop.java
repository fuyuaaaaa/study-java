package top.fuyuaaa.demo;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author : fuyuaaa
 * @date : 2020-10-27 15:09
 */
@Aspect
@Component
public class Aop {

    @Before("execution(public * top.fuyuaaa.demo.DemoApplication.test())")
    public void doBefore(){
        System.out.println("do before test...");
    }
}
