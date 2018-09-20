package fuyuaaa.suanfa;


/**
 * @author: fuyuaaaaa
 * @description:给定一个二叉树，找出其最大深度。 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * @program: StudyDemo
 * @creat: 2018-08-08 17:24
 **/
public class Solution104 {
    public int maxDepth(TreeNode root) {
            if (root == null)
                return 0;
            else
                return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }
}
