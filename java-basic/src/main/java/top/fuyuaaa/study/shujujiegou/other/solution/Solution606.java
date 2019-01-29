package top.fuyuaaa.study.shujujiegou.other.solution;

/**
 * @Package_Name: com.fuyu.solution
 * @Auther: fuyua
 * @Date: created in 22:40 2018/5/24
 */
class Solution606 {
    public String tree2str(TreeNode t) {
        tree2str1(t);
        return "success";
    }

    private void tree2str1(TreeNode node) {
        if (node == null) {
            return;
        }

        System.out.println(node.val);
        tree2str1(node.left);
        tree2str1(node.right);
    }

//    public static void main(String[] args) {
//        Solution606 solution606 = new Solution606();
//
//        solution606.tree2str(t);
//    }
}