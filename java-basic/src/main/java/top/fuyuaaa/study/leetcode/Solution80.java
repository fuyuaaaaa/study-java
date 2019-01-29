package top.fuyuaaa.study.leetcode;

/**
 * @author: fuyuaaaaa
 * @description: 80. 删除排序数组中的重复项 II
 * 题目：给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素最多出现两次，返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 * @program: study-java
 * @creat: 2018-11-16 15:02
 **/
public class Solution80 {
    public int removeDuplicates(int[] nums) {
        int length = nums.length;
        if (length <= 2)
            return length;
        int cur = 2;
        for (int i = 2; i < length; i++) {
            if (nums[i] != nums[cur - 2]) {
                nums[cur++] = nums[i];
            }
        }
        return cur;
    }

    public static void main(String[] args) {
        Solution80 solution80 = new Solution80();
        int[] a = {1, 1, 1, 2, 2, 3};
        System.out.println(solution80.removeDuplicates(a));
    }
}
