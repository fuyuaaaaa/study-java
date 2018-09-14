package fuyuaaa.shujujiegou.other;

/**
 * @Package_Name: com.fuyu
 * @Auther: fuyua
 * @Date: created in 19:37 2018/5/22
 */
public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) {
          this.val = x;
          this.next = null;
      }
      ListNode(int[] a) {
          if (a == null && a.length == 0)
              throw new IllegalArgumentException("a can not be empty");

          this.val = a[0];
          ListNode cur = this;

          for (int i = 1; i < a.length; i++) {
              cur.next = new ListNode(a[i]);
              cur = cur.next;

          }

      }

    @Override
    public String toString() {
          StringBuilder rel = new StringBuilder();
          ListNode cur = this;
          while (cur != null){
              rel.append(cur.val + "-->");
              cur = cur.next;
          }
          rel.append("null");
        return rel.toString();
    }
}
