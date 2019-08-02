package top.fuyuaaa.study.designpattern.singleton;

/**
 * @description :  懒汉单例
 * @author : fuyuaaa
 * @create : 2019-08-02 10:12
 */
public class LazySingleton {

    private LazySingleton() {

    }

    private volatile static LazySingleton INSTANCE;

    /**
     * 双重校验🔐
     */
    public static LazySingleton getInstance() {
        if (null == INSTANCE) {
            synchronized (LazySingleton.class) {
                if (null == INSTANCE) {
                    INSTANCE = new LazySingleton();
                }
            }
        }
        return INSTANCE;
    }
}
