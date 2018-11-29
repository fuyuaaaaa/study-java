package fuyuaaa.offer;

/**
 * @author: fuyuaaaaa
 * @description:
 * 题目: 输入一个链表，输出该链表中倒数第 k 个结点
 * @program: study-java
 * @creat: 2018-11-29 10:50
 **/
public class Solution014 {
    /**
     * 思路：1、统计总长度
     * 2、遍历length-k次，找到
     */
    public ListNode FindKthToTail(ListNode head,int k) {
        int length = 0;
        ListNode temp = head;
        while (temp != null) {
            length++;
            temp = temp.next;
        }
        if (k>length)
            return null;
        ListNode res = head;
        for (int i = 0; i < length - k; i++) {
            res = res.next;
        }
        return res;
    }

    /**
     *思路二：
     * 1、两个指针p1、p2，相隔 k；
     * 2、p2为空时，p1就是倒数第 k 个
     */


    /**
     *节点类
     */
    class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
}


