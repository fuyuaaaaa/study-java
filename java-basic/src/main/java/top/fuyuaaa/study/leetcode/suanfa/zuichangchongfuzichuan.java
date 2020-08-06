package top.fuyuaaa.study.leetcode.suanfa;

import java.util.HashMap;

/**
 * @author : fuyuaaa
 * @date : 2020-07-09 15:07
 */
public class zuichangchongfuzichuan {
    public int maxUnique(String str) {
        if (str == null || str.equals("")) {
            return 0;
        }
        char[] chas = str.toCharArray();
        int[] map = new int[256];
        for (int i = 0; i < 256; i++) {
            map[i] = -1;
        }
        int len = 0;
        int pre = -1;
        int cur = 0;
        for (int i = 0; i != chas.length; i++) {
            pre = Math.max(pre, map[chas[i]]);
            cur = i - pre;
            len = Math.max(len, cur);
            map[chas[i]] = i;
        }

        return len;
    }

}
