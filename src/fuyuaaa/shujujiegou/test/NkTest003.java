package fuyuaaa.shujujiegou.test;

import java.util.Scanner;
import java.util.Stack;

/**
 * @Auther: fuyuaaaaa
 * @Description:
 * @Package_Name: shujujiegou2
 * @Date: created in 2018-07-21 16:40
 */
public class NkTest003 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        int n = Integer.parseInt(scanner.nextLine());
        int n = scanner.nextInt();
        String a[] = new String[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.next();
        }
        int sum = 0;

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                if (stackKH(a[i],a[j]))
                    sum++;
            }
        }

        System.out.println(sum);
    }

    public static boolean stackKH(String a, String b){
        Stack<Character> stack = new Stack<>();
        String c = a + b;
        System.out.println(c);
        for (int i = 0; i < c.length(); i++) {
            if (c.charAt(i) == '('){
                stack.push('(');
                continue;
            }
            if (c.charAt(i) == ')') {
                if (!stack.empty() && stack.peek() == '(') {
                    stack.pop();
                    continue;
                }
                else {
                    return false;
                }
            }
        }

        if (stack.empty()){
            return true;
        }
        return false;
    }
}
