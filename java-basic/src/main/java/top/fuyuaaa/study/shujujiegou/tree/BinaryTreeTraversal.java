package top.fuyuaaa.study.shujujiegou.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @description :  
 * @author : fuyuaaa
 * @create : 2019-08-15 15:44
 */
public class BinaryTreeTraversal {

    public static void preOrder(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        System.out.print(treeNode.getValue() + " ");
        preOrder(treeNode.getLeft());
        preOrder(treeNode.getRight());
    }

    public static void midOrder(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        midOrder(treeNode.getLeft());
        System.out.print(treeNode.getValue() + " ");
        midOrder(treeNode.getRight());
    }

    public static void postOrder(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        midOrder(treeNode.getLeft());
        midOrder(treeNode.getRight());
        System.out.print(treeNode.getValue() + " ");
    }

    public static void levelOrder(TreeNode treeNode) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(treeNode);
        while (!queue.isEmpty()) {
            TreeNode temp = queue.poll();
            System.out.print(temp.getValue() + " ");
            if (temp.getLeft() != null) {
                queue.add(temp.getLeft());
            }
            if (temp.getRight() != null) {
                queue.add(temp.getRight());
            }
        }
    }

}
