package java.top.fuyuaaa.study_java.shujujiegou.segmentTree;

/**
 * @Auther: fuyuaaaaa
 * @Description: leetcode303
 * @Package_Name: shujujiegou2
 * @Date: created in 2018-07-12 23:02
 */
class NumArray {
    private SegmentTree<Integer> segmentTree;
    public NumArray(int[] nums) {
        if (nums.length > 0) {
            Integer[] data = new Integer[nums.length];
            for (int i = 0; i < nums.length; i++)
                data[i] = nums[i];
            segmentTree = new SegmentTree<>(data, (a, b) -> a + b);

        }
    }

    public int sumRange(int i, int j) {
        if (segmentTree == null)
            throw new IllegalArgumentException("segment tree is null");

        return segmentTree.query(i, j);
    }
}
