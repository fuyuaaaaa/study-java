package top.fuyuaaa.study.leetcode;

/**
 * @author : fuyuaaa
 * @date : 2020-07-09 15:23
 */

import java.util.HashMap;

public class Sulution3 {
    public int lengthOfLongestSubstring(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }
        HashMap<Character,Integer> map = new HashMap<>();
        char[] sc = s.toCharArray();
        int left = -1;
        int max = 0;
        for(int i = 0; i < sc.length; i++) {
            if(!map.containsKey(sc[i])) {
                map.put(sc[i], i);
                max = Math.max(max,i - left);
                continue;
            } else {
                int index = map.get(sc[i]);
                for(int j = left + 1;j <= index; j++){
                    if(j == -1) {
                        continue;
                    }
                    map.remove(sc[j]);
                }
                left = index;
                map.put(sc[i], i);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Sulution3 sulution3 = new Sulution3();
        System.out.println(sulution3.lengthOfLongestSubstring("tmmzuxt"));
    }
}
