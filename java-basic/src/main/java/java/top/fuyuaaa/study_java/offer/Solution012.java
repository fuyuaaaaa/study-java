package java.top.fuyuaaa.study_java.offer;

/**
 * @author: fuyuaaaaa
 * @description: 数值的整数次访
 * 题目：给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
 * （注意考虑负数）
 * @program: study-java
 * @creat: 2018-11-27 14:23
 **/
public class Solution012 {
    public static double Power(double base, int exponent) {
        if (0 == exponent)
            return 1;
        double res = 1;
        if (exponent < 0) {
            for (int i = -1; i >=exponent ; i--) {
                res /= base;
            }
            return res;
        }

        for (int i = 1; i <= exponent; i++) {
            res *= base;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Power(2, -3));
    }
}
