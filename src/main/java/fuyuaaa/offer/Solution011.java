package fuyuaaa.offer;

/**
 * @author: fuyuaaaaa
 * @description: 二进制中 1 的个数
 *  题目：输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
 * @program: study-java
 * @creat: 2018-11-27 14:18
 **/
public class Solution011 {
    public static int NumberOf1(int n) {
        String nStr = Integer.toBinaryString(n);
        int count = 0;
        for (int i = 0; i < nStr.length(); i++) {
            if (nStr.charAt(i) == '1') {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(NumberOf1(10));
    }
}
