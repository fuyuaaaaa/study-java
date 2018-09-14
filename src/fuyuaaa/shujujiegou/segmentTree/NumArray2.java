package fuyuaaa.shujujiegou.segmentTree;

/**
 * @Auther: fuyuaaaaa
 * @Description: 303
 * @Package_Name: shujujiegou2
 * @Date: created in 2018-07-12 23:15
 */
class NumArray2 {
    private int[] sum;

    public NumArray2(int[] nums) {

        sum = new int[nums.length + 1];

        sum[0] = 0;
        for (int i = 1; i < sum.length; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
    }

    public int sumRange(int i, int j) {
        return sum[j + 1] - sum[i];
    }
}
