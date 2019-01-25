package fuyuaaa.java8.stream;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author: fuyuaaaaa
 * @creat: 2019-01-17 09:30
 */
public class StreamTest {
    public static void main(String[] args) {
        List<String> stringCollection = new ArrayList<>();
        stringCollection.add("ddd2");
        stringCollection.add("aaa2");
        stringCollection.add("bbb1");
        stringCollection.add("aaa1");
        stringCollection.add("bbb3");
        stringCollection.add("ccc");
        stringCollection.add("bbb2");
        stringCollection.add("ddd1");

        System.out.println(stringCollection.stream().filter((s) -> s.startsWith("a"))
                .count());
    }


}
