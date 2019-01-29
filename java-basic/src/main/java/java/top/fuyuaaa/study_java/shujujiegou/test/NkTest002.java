package java.top.fuyuaaa.study_java.shujujiegou.test;


/**
 * @Auther: fuyuaaaaa
 * @Description:
 * @Package_Name: shujujiegou2
 * @Date: created in 2018-07-21 15:57
 */

import java.util.Map;
import java.util.Scanner;

public class NkTest002 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        double c = b * Math.log(a);
        double d = a * Math.log(b);
        if (c > d)
            System.out.println(">");
        else if (c < d)
            System.out.println("<");
        else
            System.out.println("=");
    }
}
