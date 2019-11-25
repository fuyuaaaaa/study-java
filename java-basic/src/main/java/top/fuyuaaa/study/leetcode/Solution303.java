package top.fuyuaaa.study.leetcode;


/**
 * @description :
 * 给定一个整数数组  nums，求出数组从索引 i 到 j  (i ≤ j) 范围内元素的总和，包含 i,  j 两点。
 *
 * 示例：
 *
 * 给定 nums = [-2, 0, 3, -5, 2, -1]，求和函数为 sumRange()
 *
 * sumRange(0, 2) -> 1
 * sumRange(2, 5) -> -1
 * sumRange(0, 5) -> -3
 * 说明:
 *
 * 你可以假设数组不可变。
 * 会多次调用 sumRange 方法。
 *
 * @author : fuyuaaa
 * @create : 2019-11-25 18:18
 */
public class Solution303 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        NumArray numArray = new NumArray(nums);
        System.out.println(numArray.sumRange(0, 3));
        System.out.println(numArray.sumRange(1, 3));

    }

}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(i,j);
 */
class NumArray {

    private int[] sum;

    public NumArray(int[] nums) {
        sum = new int[nums.length];
        int temp = 0;
        for (int i = 0; i < nums.length; i++) {
            temp = temp + nums[i];
            sum[i] = temp;
        }
    }

    public int sumRange(int i, int j) {
        if (i == 0) {
            return sum[j];
        }
        return sum[j] - sum[i - 1];
    }
}
