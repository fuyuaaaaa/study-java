package top.fuyuaaa.study.Pattern.SingletonPattern;

/**
 * @author: fuyuaaaaa
 * @description: 登记式/静态内部类
 * @program: StudyDemo
 * @creat: 2018-07-27 10:19
 **/
public class Singleton4 {
    private Singleton4(){}

    private static class SingletonHolder{
        private final static Singleton4 INSTANCE = new Singleton4();
    }

    public static Singleton4 getInstance(){
        return SingletonHolder.INSTANCE;
    }
}
