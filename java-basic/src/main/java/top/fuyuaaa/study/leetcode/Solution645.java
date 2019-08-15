package top.fuyuaaa.study.leetcode;

import java.util.Arrays;

/**
 * @description :
 * 集合 S 包含从1到 n 的整数。不幸的是，因为数据错误，导致集合里面某一个元素复制了成了集合里面的另外一个元素的值，导致集合丢失了一个整数并且有一个元素重复。
 * 给定一个数组 nums 代表了集合 S 发生错误后的结果。你的任务是首先寻找到重复出现的整数，再找到丢失的整数，将它们以数组的形式返回。
 *
 * 示例 1:
 *
 *  输入: nums = [1,2,2,4]
 *  输出: [2,3]
 *
 * 注意:
 *
 *  给定数组的长度范围是 [2, 10000]。
 *  给定的数组是无序的。
 *
 * @author : fuyuaaa
 * @create : 2019-07-08 19:49
 */
public class Solution645 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 2, 4};
        System.out.println(Arrays.toString(findErrorNums(nums)));
    }

    public static int[] findErrorNums(int[] nums) {
        int[] numsCopy = new int[nums.length + 1];
        for (int num : nums) {
            numsCopy[num]++;
        }
        int repeat = 0;
        int lose = 0;
        for (int i = 1; i < numsCopy.length; i++) {
            if (numsCopy[i] == 2 && repeat == 0) {
                repeat = i;
            }
            if (numsCopy[i] == 0 && lose == 0) {
                lose = i;
            }
        }
        return new int[]{repeat, lose};
    }
}