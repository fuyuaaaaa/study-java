package top.fuyuaaa.study.leetcode;

/**
 *
 * @author : fuyuaaa
 * @date : 2020-03-11 16:45
 */
public class Solution1013 {

    public boolean canThreePartsEqualSum(int[] A) {
        if (A.length < 3) {
            return false;
        }
        int sum = 0;
        for (int i = 0; i < A.length; i++) {
            sum += A[i];
        }
        if (sum % 3 != 0) {
            return false;
        }
        int part = sum / 3;
        //当前部分求和
        int now = 0;
        //满足的部分
        int partNum = 0;
        for (int i = 0; i < A.length; i++) {
            now += A[i];
            if (now == part && (partNum != 2 || i == A.length - 1)) {
                now = 0;
                partNum++;
            }
        }
        if (partNum == 3) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Solution1013 solution1013 = new Solution1013();
        int[] array = {10, -10, 10, -10, 10, -10, 10, -10};
        System.out.println(solution1013.canThreePartsEqualSum(array));
    }
}
