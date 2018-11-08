package fuyuaaa.leetcode;

/**
 *给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。
 * 分析： 3越多越大。
 */
public class Solution343 {
    public int integerBreak(int n) {
        if (n <= 3) {
            return n - 1;
        }
        int result = 1;
        while (n > 4) {
            result *=3;
            n -= 3;
        }

        return result * n;
    }

    public static void main(String[] args) {
        Solution343 solution343 = new Solution343();
        System.out.println(solution343.integerBreak(16));
    }
}
