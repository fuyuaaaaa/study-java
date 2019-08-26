package top.fuyuaaa.study.shujujiegou.tree.avl;

import java.util.LinkedList;
import java.util.Queue;
import lombok.AllArgsConstructor;

/**
 * @description :  平衡二叉查找树
 * 平衡：每个节点的左子树和右子树的高度最多相差 1
 * @author : fuyuaaa
 * @create : 2019-08-26 10:33
 */
public class AVL<E extends Comparable<E>> {

    @AllArgsConstructor
    private class AVLNode<E> {

        private E e;
        private AVLNode<E> left, right, parent;
        /**
         * 节点到叶子节点(height=0)的最大高度
         */
        private Integer height;

        private AVLNode(E e) {
            this.e = e;
            this.left = null;
            this.right = null;
            this.parent = null;
            this.height = 0;
        }
    }

    private int size;
    private AVLNode<E> root;

    private int height(AVLNode<E> node) {
        return null == node ? -1 : node.height;
    }

    private int recalculateHeight(AVLNode<E> node) {
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    /**
     * 左左单旋转 -> 右旋
     * @param node 不平衡的节点
     * @return 旋转之后的节点
     */
    private AVLNode<E> LLRotate(AVLNode<E> node) {
        AVLNode<E> result = node.left;
        node.left = result.right;
        result.right = node;
        node.height = recalculateHeight(node);
        result.height = recalculateHeight(result);
        return result;
    }


    /**
     * 右右单旋转 -> 左旋
     * @param node 不平衡的节点
     * @return 旋转之后的节点
     */
    private AVLNode<E> RRRotate(AVLNode<E> node) {
        AVLNode<E> result = node.right;
        node.right = result.left;
        result.left = node;
        node.height = recalculateHeight(node);
        result.height = recalculateHeight(result);
        return result;
    }

    /**
     * 左右双旋转 -> 先左旋后右旋
     * @param node 不平衡的节点
     * @return 旋转之后的节点
     */
    private AVLNode<E> LRRotate(AVLNode<E> node) {
        node.left = this.RRRotate(node.left);
        return this.LLRotate(node);
    }

    /**
     * 右左双旋转 -> 先右旋后左旋
     * @param node 不平衡的节点
     * @return 旋转之后的节点
     */
    private AVLNode<E> RLRotate(AVLNode<E> node) {
        node.right = this.LLRotate(node.right);
        return this.RRRotate(node);
    }

    public void addArray(E[] eArray) {
        for (E e : eArray) {
            this.add(e);
        }
    }

    public void add(E e) {
        root = this.add(e, root);
    }

    private AVLNode<E> add(E e, AVLNode<E> node) {
        if (node == null) {
            this.size++;
            return new AVLNode<>(e);
        }
        //在当前节点的左子树插入
        if (e.compareTo(node.e) < 0) {
            node.left = add(e, node.left);
            //判断当前节点是否平衡
            if (height(node.left) - height(node.right) == 2) {
                //在当前节点的左子树的左子树插入 -- 左左情况
                if (e.compareTo(node.left.e) < 0) {
                    node = LLRotate(node);
                }
                //在当前节点的左子树的右子树插入 -- 左右情况
                else {
                    node = LRRotate(node);
                }
            }
        }
        //在当前节点的右子树插入
        else if (e.compareTo(node.e) > 0) {
            node.right = add(e, node.right);
            if (height(node.right) - height(node.left) == 2) {
                //在当前节点的右子树的右子树插入 -- 右右情况
                if (e.compareTo(node.right.e) > 0) {
                    node = RRRotate(node);
                }
                //在当前节点的右子树的左子树插入 -- 右左情况
                else {
                    node = RLRotate(node);
                }
            }
        }
        //重新计算节点的高度
        node.height = recalculateHeight(node);
        return node;
    }

    public void levelOrder() {
        this.levelOrder(root);
    }

    private void levelOrder(AVLNode<E> node) {
        Queue<AVLNode<E>> queue = new LinkedList<>();
        queue.add(node);
        Integer height = node.height;
        while (!queue.isEmpty()) {
            AVLNode<E> temp = queue.poll();
            if (!height.equals(temp.height)){
                height = temp.height;
                System.out.println();
            }
            System.out.print(temp.e + " ");
            if (temp.left != null) {
                queue.add(temp.left);
            }
            if (temp.right != null) {
                queue.add(temp.right);
            }
        }
    }

    public static void main(String[] args) {
        Integer[] array = {4, 2, 7, 6, 8, 5};
        AVL avl = new AVL();
        avl.addArray(array);
        avl.levelOrder();
    }
}
