package top.fuyuaaa.study.jvm;

/**
 * @author : fuyuaaa
 * @date : 2020-06-01 15:16
 */
public class Test {

    static int i = 123;

    static int value;
    static {
        value = 456;
    }

    static {
        System.out.println(789);
    }
}
