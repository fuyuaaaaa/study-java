package fuyuaaa.leetcode;

import java.util.Stack;

/**
 * 530给定一个所有节点为非负值的二叉搜索树，求树中任意两节点的差的绝对值的最小值。
 * 分析:二叉搜索树的中序遍历是从小到大的结果，问题转化为相邻两个数的差的绝对值的最小值。
 * 二叉搜索树（二叉查找树、二叉排序树）： 左节点<根节点<右节点(所有节点均成立)
 */
public class Solution530 {

    static Stack<Integer> stack = new Stack<>();

    public int getMinimumDifference(TreeNode root) {
        inOrderTraversal(root);
        int min = Integer.MAX_VALUE;
        int prev = stack.pop();
        while (!stack.isEmpty()) {
            int cur = stack.pop();
            min = min > (prev - cur) ? (prev - cur) : min;
            prev = cur;
        }
        return min;
    }

    /**
     * 中序遍历
     */
    public static void inOrderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrderTraversal(root.left);
        stack.push(root.val);
        inOrderTraversal(root.right);
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
