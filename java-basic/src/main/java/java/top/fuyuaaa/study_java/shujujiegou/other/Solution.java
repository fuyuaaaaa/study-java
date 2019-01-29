package java.top.fuyuaaa.study_java.shujujiegou.other;

/**
 * @Package_Name: com.fuyu
 * @Auther: fuyua
 * @Date: created in 15:50 2018/5/22
 */
class Solution {
    public int lengthOfLastWord(String s) {
            String[] words = s.split(" ");
            if (words.length > 1)
                return words[words.length-1].length();
            else if (words.length == 1)
                return words[0].length();
            else
                return 0;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.lengthOfLastWord("123 "));
    }

}