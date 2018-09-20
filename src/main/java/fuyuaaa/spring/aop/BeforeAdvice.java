package fuyuaaa.spring.aop;

import java.lang.reflect.Method;

/**
 * @author: fuyuaaaaa
 * @description:
 * @program: study
 * @creat: 2018-08-13 20:02
 **/
public class BeforeAdvice implements Advice {
    private Object bean;
    private MethodInvocation methodInvocation;

    public BeforeAdvice(Object bean, MethodInvocation methodInvocation) {
        this.bean = bean;
        this.methodInvocation = methodInvocation;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        methodInvocation.invoke();
        return method.invoke(bean, args);
    }
}
