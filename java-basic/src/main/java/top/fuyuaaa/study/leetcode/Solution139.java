package top.fuyuaaa.study.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 */
/**
 * 为解决
 */
public class Solution139 {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            sb.append(c);
            if (set.contains(sb.toString())) {
                sb = new StringBuilder();
            }
        }
        return sb.length() == 0;
    }

    public static void main(String[] args) {
        Solution139 solution139 = new Solution139();
        String s = "leetcode";
        List<String> wordDoct = new ArrayList<>();
        wordDoct.add("leet");
        wordDoct.add("code");
        System.out.println(solution139.wordBreak(s, wordDoct));

    }
}
