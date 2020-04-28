package top.fuyuaaa.study.leetcode;

/**
 * @author : fuyuaaa
 * @date : 2020-04-28 10:03
 */
public class Mianshi56 {
    public int[] singleNumbers(int[] nums) {
        if (nums.length == 2) {
            return nums;
        }
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            result ^= nums[i];
        }

        //找到不为1的那一位，因为result是俩不相等的数的异或, 所有肯定有个1
        int first = 1;
        while ((first & result) == 0) {
            first <<= 1;
        }

        int temp = result;
        for (int i = 0; i < nums.length; i++) {
            if ((nums[i] & first) ==0){
                temp ^=nums[i];
            }
        }

        int[] a = new int[2];
        a[0] = temp;
        a[1] = temp ^ result;

        return a;
    }
}
