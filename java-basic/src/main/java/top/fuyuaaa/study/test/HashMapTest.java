package top.fuyuaaa.study.test;

import java.util.HashMap;

/**
 * @author: fuyuaaaaa
 * @description:
 * @program: study-java
 * @creat: 2018-11-10 12:41
 **/
public class HashMapTest extends HashMap<String, Object> {

    public static void main(String[] args) {
        HashMap hashMap = new HashMap(6);
        System.out.println(hashMap.size());

        int a = 2;
        a |= 2 >>> 1;
        System.out.println(a);
    }


    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
}
