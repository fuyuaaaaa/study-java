package fuyuaaa.offer;

/**
 * @author: fuyuaaaaa
 * @description: 变态跳台阶
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。
 * 求该青蛙跳上一个n级的台阶总共有多少种跳法。
 * @program: study-java
 * @creat: 2018-11-20 20:12
 **/
public class Solution009 {
    public int JumpFloorII(int target) {
        int res = 1;
        for (int i = 1; i < target; i++) {
            res *= 2;
        }
        return res;
    }
}
