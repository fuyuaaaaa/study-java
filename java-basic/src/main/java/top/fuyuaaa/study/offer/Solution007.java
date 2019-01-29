package top.fuyuaaa.study.offer;

/**
 * @author: fuyuaaaaa
 * @description: 斐波那契数列
 * @program: study-java
 * @creat: 2018-11-15 10:43
 **/
public class Solution007 {
    public int Fibonacci(int n) {
        if (0 == n)
            return 0;
        if (1 == n || 2 == n)
            return 1;
        return Fibonacci(n - 1) + Fibonacci(n - 2);
    }

    public int Fibonacci2(int n) {
        if (0 == n)
            return 0;
        if (1 == n || 2 == n)
            return 1;
        int f1 = 1, f2 = 1, res = 0;
        for (int i = 3; i < n; i++) {
            res = f1 + f2;
            f1 = f2;
            f2 = res;
        }
        return res;
    }
}
