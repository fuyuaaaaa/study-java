package top.fuyuaaa.study.shujujiegou.segmentTree;

/**
 * @Auther: fuyuaaaaa
 * @Description: 线段数main
 * @Package_Name: shujujiegou2
 * @Date: created in 2018-07-08 23:28
 */
public class MainST {
    public static void main(String[] args) {
        Integer[] nums = {-2, 0, 3, -5, 2, -1};
        SegmentTree<Integer> segmentTree = new SegmentTree<>(nums, (a, b) -> a + b);

//        System.out.println(segmentTree);
        System.out.println(segmentTree.query(0, 2));
    }
}
