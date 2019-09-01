package top.fuyuaaa.study.leetcode;

/**
 * @description :  
 * @author : fuyuaaa
 * @create : 2019-08-30 11:36
 */
public class Solution767 {
    public String reorganizeString(String S) {
        int[] array = new int[25];
        for (int i = 0; i < S.length(); i++) {
            char current = S.charAt(i);
            array[(int)current-97] ++;
        }



        return "";
    }

    public static void main(String[] args) {
        Solution767 solution767 = new Solution767();
        solution767.reorganizeString("aab");
    }
}
