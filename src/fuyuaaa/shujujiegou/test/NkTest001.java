package fuyuaaa.shujujiegou.test;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @Auther: fuyuaaaaa
 * @Description:
 * @Package_Name: shujujiegou2
 * @Date: created in 2018-07-21 15:12
 */
public class NkTest001 {
    public static void main(String[] args) {
        System.out.println("please input");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        ArrayList<Integer> list = new ArrayList();
        for (int i = 0; i < n; i++) {
            list.add(scanner.nextInt());
        }
        int sum = 0;
        for (int a : list) {
            if (a % 2 == 0) {
                while (a % 2 == 0){
                    a = a / 2;
                    sum++;
                }

            }
        }
        System.out.println(sum);
    }
}
