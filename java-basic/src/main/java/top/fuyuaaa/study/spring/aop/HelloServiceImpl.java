package top.fuyuaaa.study.spring.aop;

/**
 * @author: fuyuaaaaa
 * @description:
 * @program: study
 * @creat: 2018-08-14 14:41
 **/
public class HelloServiceImpl implements HelloService{
    @Override
    public void sayHelloWorld() {
        System.out.println("hello world!");
    }
}
