package top.fuyuaaa.study.leetcode;

/**
 * https://leetcode-cn.com/problems/diameter-of-binary-tree/
 * @author: fuyuaaaaa
 * @description: 二叉树的直径
 * 题目：给定一棵二叉树，你需要计算它的直径长度。
 * 一棵二叉树的直径长度是任意两个结点路径长度中的最大值。
 * 这条路径可能穿过根结点。
 * @program: study-java
 * @creat: 2018-11-15 11:26
 **/
public class Solution543 {
    int max = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        getDepth(root);
        return max;
    }

    public int getDepth(TreeNode root) {
        if (root == null)
            return 0;
        int l = getDepth(root.left);
        int r = getDepth(root.right);
        max = Math.max(max, l + r);
        return Math.max(l + 1, r + 1);
    }
}

class Solution543_2{
    int maxRoad = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        getMaxHeight(root);
        return maxRoad;
    }

    private int getMaxHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftMaxHeight = getMaxHeight(node.left);
        int rightMaxHeight = getMaxHeight(node.right);
        if (maxRoad < leftMaxHeight + rightMaxHeight) {
            maxRoad = leftMaxHeight + rightMaxHeight;
        }
        if (leftMaxHeight > rightMaxHeight) {
            return leftMaxHeight + 1;
        }
        return rightMaxHeight + 1;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}
