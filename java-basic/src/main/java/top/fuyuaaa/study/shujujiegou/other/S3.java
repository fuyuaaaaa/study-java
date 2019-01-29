package top.fuyuaaa.study.shujujiegou.other;

/**
 * @Package_Name: com.fuyu
 * @Auther: fuyua
 * @Date: created in 20:45 2018/5/22
 */
class S3 {
    public void deleteNode(ListNode node) {
    }
    public static void main(String[] args) {
        S3 s3 = new S3();
        int[] a = {1,2,3,4};
        ListNode node = new ListNode(a);
        s3.deleteNode(node);
        System.out.println(node);
    }
}
