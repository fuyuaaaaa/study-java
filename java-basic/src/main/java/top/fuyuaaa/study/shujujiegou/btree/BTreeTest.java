package top.fuyuaaa.study.shujujiegou.btree;

/**
 * @author: fuyuaaa
 * @creat: 2019-05-10 14:24
 */
public class BTreeTest {
    public static void main(String[] args) {
        BTree bTree = BTree.newInstance();
        Integer[] nums = {1,3,12,345,57,4,13,2,5,8,9};
        bTree.insertKeys(nums);
        System.out.println(bTree.sizeOfKeys());
        System.out.println(bTree.findNode(1));
        System.out.println();
    }
}
