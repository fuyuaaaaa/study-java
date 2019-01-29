package java.top.fuyuaaa.study_java.Pattern.SingletonPattern;

/**
 * @author: fuyuaaaaa
 * @description: 饿汉模式
 * @program: StudyDemo
 * @creat: 2018-07-27 10:04
 **/
public class Singleton1 {
    private Singleton1() {
    }

    private static Singleton1 instance = new Singleton1();

    public Singleton1 getInstance () {
        return instance;
    }

}
