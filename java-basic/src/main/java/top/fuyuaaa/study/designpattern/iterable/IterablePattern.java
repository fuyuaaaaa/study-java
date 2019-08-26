package top.fuyuaaa.study.designpattern.iterable;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * @description :  
 * @author : fuyuaaa
 * @create : 2019-08-26 09:54
 */
public class IterablePattern {

    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>(100);
        for (int i = 0; i < 100; i++) {
            stringList.add(i + "");
        }
        ListIterator<String> stringListIterator = stringList.listIterator();
        while (stringListIterator.hasNext()) {
            System.out.println(stringListIterator.next());
        }

    }
}
