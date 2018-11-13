package fuyuaaa.jvm;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: fuyuaaaaa
 * @description: 内存泄漏demo
 * @program: study-java
 * @creat: 2018-11-11 15:24
 **/
public class MemoryLeakDemo {
    static class Key {
        Integer id;

        Key(Integer id) {
            this.id = id;
        }

        @Override
        public int hashCode() {
            return id.hashCode();
        }
    }

    public static void main(String[] args) {
        Map m = new HashMap();
        while (true) {
            for (int i = 0; i < 10000; i++) {
                if (!m.containsKey(new Key(i))) {
                    m.put(new Key(i), "Number:" + i);
                }
            }
            System.out.println(m.size());
        }
    }
}
