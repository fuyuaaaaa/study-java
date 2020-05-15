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
        HashMap map = new HashMap(7);
        map.put(1,1);
        map.put(2,1);
        map.put(3,1);
        map.put(4,1);
        map.put(5,1);
        map.put(6,1);
        map.put(7,1);
        map.put(8,1);
        map.put(9,1);
        map.put(11,1);

        System.out.println(9 & (4-1));
    }


    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
}
