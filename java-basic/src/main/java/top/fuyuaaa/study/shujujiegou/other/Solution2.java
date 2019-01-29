package top.fuyuaaa.study.shujujiegou.other;

/**
 * @Package_Name: com.fuyu
 * @Auther: fuyua
 * @Date: created in 19:37 2018/5/22
 */

class Solution2 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;
        if (l1.val >= l2.val) {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        } else {
            l1.next = mergeTwoLists(l2, l1.next);
            return l1;
        }
    }


    public static void main(String[] args) {
        int a1[] = {1,2,4};
        ListNode l1 = new ListNode(a1);
        int a2[] = {1,3,4};
        ListNode l2 = new ListNode(a2);

        System.out.println(l1);
        System.out.println(l2);

        Solution2 solution = new Solution2();
        System.out.println(solution.mergeTwoLists(l1, l2));
    }
}