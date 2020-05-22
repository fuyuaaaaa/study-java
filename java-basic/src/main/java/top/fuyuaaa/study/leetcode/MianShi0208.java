package top.fuyuaaa.study.leetcode;

import java.util.HashSet;

/**
 * 给定一个有环链表，实现一个算法返回环路的开头节点。
 * 有环链表的定义：在链表中某个节点的next元素指向在它前面出现过的节点，则表明该链表存在环路
 *
 * @author : fuyuaaa
 * @date : 2020-05-22 16:35
 */
public class MianShi0208 {
    public ListNode detectCycle(ListNode head) {
        HashSet<ListNode> set = new HashSet<>(16);
        ListNode temp = head;
        while (temp != null) {
            if (set.contains(temp)) {
                return temp;
            }
            set.add(temp);
            temp = temp.next;
        }
        return null;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    /**
     * 方法二 快慢指针
     */
    public ListNode detectCycle2(ListNode head) {

        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && slow != null) {
            fast = fast.next;
            slow = slow.next;
            if (fast == null) {
                return null;
            }
            fast = fast.next;

            if (fast == slow) {
                break;
            }
        }

        if (fast == null || slow == null) {
            return null;
        }
        slow = head;
        while (slow != fast) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
}


