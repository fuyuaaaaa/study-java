package fuyuaaa.leetcode;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 如果单词列表（words）中的一个单词包含牌照（licensePlate）中所有的字母，那么我们称之为完整词。在所有完整词中，最短的单词我们称之为最短完整词。
 * <p>
 * 单词在匹配牌照中的字母时不区分大小写，比如牌照中的 "P" 依然可以匹配单词中的 "p" 字母。
 * <p>
 * 我们保证一定存在一个最短完整词。当有多个单词都符合最短完整词的匹配条件时取单词列表中最靠前的一个。
 * <p>
 * 牌照中可能包含多个相同的字符，比如说：对于牌照 "PP"，单词 "pair" 无法匹配，但是 "supper" 可以匹配。
 */
 /**
  * 对题目有疑问，提交不正确
  */
public class Solution748 {

    public String shortestCompletingWord(String licensePlate, String[] words) {
        licensePlate = licensePlate.toLowerCase();
        ArrayList<Character> chars = new ArrayList<>();
        for (int i = 0; i < licensePlate.length(); i++) {
            if (Character.isLetter(licensePlate.charAt(i))) {
                chars.add(licensePlate.charAt(i));
            }
        }

        for (String word : words) {
            HashMap<Character, Integer> map = new HashMap<>();
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (map.containsKey(c)) {
                    map.put(c, map.get(c) + 1);
                } else {
                    map.put(c, 1);
                }
            }
            for (int i = 0; i < chars.size(); i++) {
                char c = chars.get(i);
                if (!map.containsKey(c) || map.get(c) <= 0) {
                    break;//不存在
                }
                map.put(c, map.get(c) - 1);
                if (i == chars.size() - 1) {
                    return word;
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Solution748 solution748 = new Solution748();


        String[] words = {"looks", "pest", "stew", "show"};
        String licensePlate = "1s3 456";
        System.out.println(solution748.shortestCompletingWord(licensePlate, words));
    }
}
