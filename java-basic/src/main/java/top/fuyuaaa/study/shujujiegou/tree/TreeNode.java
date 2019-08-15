package top.fuyuaaa.study.shujujiegou.tree;

import lombok.Data;

/**
 * @description :  
 * @author : fuyuaaa
 * @create : 2019-08-15 15:35
 */
@Data
public class TreeNode {
    private String value;
    private TreeNode left;
    private TreeNode right;
    public TreeNode(String value) {
        this.value = value;
    }
}
