package fuyuaaa.jvm;

/**
 * @author: fuyuaaaaa
 * @description:
 * @program: study-java
 * @creat: 2018-11-11 19:30
 **/
public class TestGC {
    public static void main(String[] args) {
        new TestGC();
        System.gc();
        System.runFinalization();
    }
}
