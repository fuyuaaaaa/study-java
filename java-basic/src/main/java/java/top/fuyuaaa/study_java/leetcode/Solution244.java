package java.top.fuyuaaa.study_java.leetcode;

import java.util.Stack;

/**
 * 2018-11-5
 * 224. 基本计算器
 * 实现一个基本的计算器来计算一个简单的字符串表达式的值。
 * 字符串表达式可以包含左括号 ( ，右括号 )，加号 + ，减号 -，非负整数和空格。
 * 分析：使用栈
 */
public class Solution244 {
    public int calculate(String s) {
        s = s.replaceAll(" ", "");
        Stack<String> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            String cur = String.valueOf(s.charAt(i));
            if (s.charAt(i) == '(' || s.charAt(i) == '+' || s.charAt(i) == '-') {
                stack.push(cur);
                continue;
            }
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                StringBuilder sb = new StringBuilder();
                while (i < s.length() && s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                    sb.append(s.charAt(i));
                    i++;
                }
                i--;
                int curInt = Integer.valueOf(sb.toString());
                if (!stack.isEmpty() && (stack.peek().equals("+") || stack.peek().equals("-"))) {
                    String operator = stack.pop();
                    int left = Integer.valueOf(stack.pop());
                    int right = curInt;
                    int res = 0;
                    if (operator.equals("+")) {
                        res = left + right;
                    } else {
                        res = left - right;
                    }
                    stack.push(String.valueOf(res));
                } else {
                    stack.push(sb.toString());
                }
                continue;
            }

            if (s.charAt(i) == ')') {
                String popNum = stack.pop();
                stack.pop();
                if (!stack.isEmpty() && (stack.peek().equals("+") || stack.peek().equals("-"))){
                    String operator = stack.pop();
                    int left = Integer.valueOf(stack.pop());
                    int right = Integer.valueOf(popNum);
                    int res = 0;
                    if (operator.equals("+")) {
                        res = left + right;
                    } else {
                        res = left - right;
                    }
                    stack.push(String.valueOf(res));
                } else {
                    stack.push(popNum);
                }
                continue;
            }
        }
        int res = Integer.valueOf(stack.pop());
        return res;
    }

    public static void main(String[] args) {
        Solution244 solution244 = new Solution244();
        System.out.println(solution244.calculate("(1-(1-1)-1"));
    }
}
