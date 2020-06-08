package top.fuyuaaa.study.leetcode;

/**
 * 最大子序和
 *
 * @author : fuyuaaa
 * @date : 2020-06-05 18:02
 */
public class Solution53 {
    public int maxSubArray(int[] nums) {
        int ans = nums[0];
        int sum = 0;
        for (int num : nums) {
            if (sum > 0) {
                sum += num;
            } else {
                sum = num;
            }
            ans = Math.max(sum, ans);
        }
        return ans;
    }
}
