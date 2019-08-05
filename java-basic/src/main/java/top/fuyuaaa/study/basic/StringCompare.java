package top.fuyuaaa.study.basic;

/**
 * @description :  String StringBuffer StringBuilder 性能
 * @author : fuyuaaa
 * @create : 2019-08-02 10:41
 */
public class StringCompare {

    public static void main(String[] args) {
        int num = 10000;
        String a = "abc";
        long time = System.currentTimeMillis();
        for (int i = 1; i < num; i++) {
            a = a + i;
        }
        System.out.println(System.currentTimeMillis() - time);
        long time1 = System.currentTimeMillis();
        StringBuffer buffer = new StringBuffer();
        for (int i = 1; i < num; i++) {
            buffer.append(i);
        }
        System.out.println(System.currentTimeMillis() - time1);
        StringBuilder builder = new StringBuilder();
        long time2 = System.currentTimeMillis();
        for (int i = 1; i < num; i++) {
            builder.append(i);
        }
        System.out.println(System.currentTimeMillis() - time2);
    }
}
