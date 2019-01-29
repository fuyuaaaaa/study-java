package top.fuyuaaa.study.offer;

/**
 * @author: fuyuaaaaa
 * @description: 青蛙跳台阶 斐波那契数列
 * @program: study-java
 * @creat: 2018-11-16 17:03
 **/
public class Solution008 {
    public int JumpFloor(int target) {
        if (target ==1)
            return 1;
        if (target == 2)
            return 2;
        return JumpFloor(target - 1) + JumpFloor((target - 2));
    }

    public int JumpFloor2(int target) {
        if (target ==1)
            return 1;
        if (target == 2)
            return 2;
        int f1 = 1, f2 = 1, res = 0;
        for (int i = 3; i < target; i++) {
            res = f1 + f2;
            f1 = f2;
            f2 = res;
        }
        return res;
    }
}
