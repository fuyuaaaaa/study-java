import java.util.ArrayList;
import java.util.List;

/**
 * @author: fuyuaaa
 * @creat: 2019-03-04 19:42
 */
public class Main {
    public static void main(String[] args) {
        List<Thread> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(new Thread());
        }
        list.forEach(a -> System.out.println(a.getName()));
    }
}
