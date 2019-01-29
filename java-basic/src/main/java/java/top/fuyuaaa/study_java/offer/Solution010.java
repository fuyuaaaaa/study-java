package java.top.fuyuaaa.study_java.offer;

/**
 * @author: fuyuaaaaa
 * @description: 矩形覆盖
 * 题目： 我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。
 * 请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
 * @program: study-java
 * @creat: 2018-11-27 14:08
 **/
public class Solution010 {

    /**
     *递归
     */
    public int RectCover(int target) {
        if (1 == target)
            return 1;
        if (2 == target)
            return 2;
        if (3 <= target)
            return RectCover(target - 1) + RectCover(target - 2);
        return 0;
    }

    /**
     *非递归
     */
    public int RectCover2(int target) {
        if (1 == target)
            return 1;
        if (2 == target)
            return 2;
        int f1 = 1,f2 = 2,res=0;
        for (int i = 3; i <= target; i++) {
            res = f2 + f1;
            f1 = f2;
            f2= res;
        }
        return res;
    }
}
