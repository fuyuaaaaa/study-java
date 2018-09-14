package fuyuaaa.spring.ioc;


import org.junit.jupiter.api.Test;

/**
 * @author: fuyuaaaaa
 * @description: ioc test
 * @program: StudyDemo
 * @creat: 2018-08-11 17:24
 **/
public class SimpleIOCTest {
    @Test
    public void getBean() throws Exception{
        String location = SimpleIOC.class.getClassLoader().getResource("fuyuaaa/spring/ioc/ioc.xml").getFile();
        SimpleIOC bf = new SimpleIOC(location);
        Wheel wheel = (Wheel) bf.getBean("wheel");
        System.out.println(wheel);
        Car car = (Car) bf.getBean("car");
        System.out.println(car);
    }
}
