package top.fuyuaaa.study.designpattern.singleton;

/**
 * @description :  饿汉单例
 * @author : fuyuaaa
 * @create : 2019-08-02 10:09
 */
public class Singleton {

    private static final Singleton INSTANCE = new Singleton();

    private Singleton() {

    }

    public static Singleton getInstance() {
        return INSTANCE;
    }
}
