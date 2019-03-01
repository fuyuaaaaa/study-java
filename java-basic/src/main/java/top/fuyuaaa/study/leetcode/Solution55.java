package top.fuyuaaa.study.leetcode;

/**
 * @author: fuyuaaa
 * @creat: 2018-09-11 18:42
 */
public class Solution55 {
    private static boolean isCanJump(int[] nums) {
        int length = nums.length;
        if (1 == length) {
            return true;
        }
        int max = 0;
        for (int i = 0; i < length - 1; i++) {
            if (max < i) {
                return false;
            }
            max = Math.max(max, i + nums[i]);
            if (max >= length - 1) {
                return true;
            }
        }
        return false;
    }
}
