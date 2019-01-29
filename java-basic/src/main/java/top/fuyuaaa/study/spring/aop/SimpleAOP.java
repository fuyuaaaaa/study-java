package top.fuyuaaa.study.spring.aop;

import java.lang.reflect.Proxy;

/**
 * @author: fuyuaaaaa
 * @description:
 * @program: study
 * @creat: 2018-08-13 20:16
 **/
public class SimpleAOP {
    public static Object getProxy(Object bean, Advice advice) {
        return Proxy.newProxyInstance(SimpleAOP.class.getClassLoader(),
                bean.getClass().getInterfaces(), advice);

    }
}
