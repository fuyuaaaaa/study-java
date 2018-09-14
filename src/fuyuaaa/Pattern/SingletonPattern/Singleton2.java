package fuyuaaa.Pattern.SingletonPattern;

/**
 * @author: fuyuaaaaa
 * @description: 懒汉模式
 * @program: StudyDemo
 * @creat: 2018-07-27 10:07
 **/
public class Singleton2 {
    private Singleton2(){}

    private static Singleton2 instance;

    public Singleton2 getInstance(){ //加synchronized 可使线程安全，但会极大降低效率
        if (instance == null) {
            instance = new Singleton2();
        }
        return instance;
    }
}
