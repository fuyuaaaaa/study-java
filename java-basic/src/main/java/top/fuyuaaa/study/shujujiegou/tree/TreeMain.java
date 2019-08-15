package top.fuyuaaa.study.shujujiegou.tree;

/**
 * @description :  
 * @author : fuyuaaa
 * @create : 2019-08-15 15:37
 */
public class TreeMain {

    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode("F");
        TreeNode treeNode2 = new TreeNode("B");
        TreeNode treeNode3 = new TreeNode("G");
        TreeNode treeNode4 = new TreeNode("A");
        TreeNode treeNode5 = new TreeNode("D");
        TreeNode treeNode6 = new TreeNode("I");
        TreeNode treeNode7 = new TreeNode("C");
        TreeNode treeNode8 = new TreeNode("E");
        TreeNode treeNode9 = new TreeNode("H");
        treeNode1.setLeft(treeNode2);
        treeNode1.setRight(treeNode3);
        treeNode2.setLeft(treeNode4);
        treeNode2.setRight(treeNode5);
        treeNode5.setLeft(treeNode7);
        treeNode5.setRight(treeNode8);
        treeNode3.setRight(treeNode6);
        treeNode6.setLeft(treeNode9);


        BinaryTreeTraversal.preOrder(treeNode1);
        System.out.println();
        BinaryTreeTraversal.midOrder(treeNode1);
        System.out.println();
        BinaryTreeTraversal.postOrder(treeNode1);
        System.out.println();
        BinaryTreeTraversal.levelOrder(treeNode1);
    }
}
