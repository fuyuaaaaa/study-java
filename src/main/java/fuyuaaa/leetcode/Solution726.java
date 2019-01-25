package fuyuaaa.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author: fuyuaaaaa
 * @creat: 2018-12-29 17:10
 */
public class Solution726 {
    public String countOfAtoms(String formula) {
        Map<String, Integer> map = new HashMap<>();
        Stack stack = new Stack();
        for (int i = 0; i < formula.length(); i++) {
            char c = formula.charAt(i);
            if (Character.isLetter(c)) {
                stack.push(c);
            }
        }

        return null;
    }

    class Temp {
        String s;
        int num;
        Temp(String s, int num) {
            this.s = s;
            this.num = num;
        }
    }
}