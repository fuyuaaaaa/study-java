package java.top.fuyuaaa.study_java.Pattern.SingletonPattern;

/**
 * @author: fuyuaaaaa
 * @description: 双重校验锁
 * @program: StudyDemo
 * @creat: 2018-07-27 10:12
 **/
public class Singleton3 {
    private Singleton3(){}

    private volatile static Singleton3 instance;

    public Singleton3 getInstance(){
        if (instance == null) {
            synchronized(Singleton3.class) {
                if (instance == null) {
                    instance = new Singleton3();
                }
            }
        }
        return instance;
    }
}
