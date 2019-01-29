package java.top.fuyuaaa.study_java.jvm;

/**
 * @author: fuyuaaaaa
 * @description: intern demo
 * @program: study-java
 * @creat: 2018-11-11 15:50
 **/
public class StringInternDemo {

    public static void main(String[] args) {
        String s = new String("1");
        s.intern();
        String s2 = "1";
        System.out.println(s == s2);

        String s3 ="11";
        s3.intern();
        String s4 = "11";
        System.out.println(s3 == s4);
    }
}
