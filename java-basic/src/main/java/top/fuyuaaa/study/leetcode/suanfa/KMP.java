package top.fuyuaaa.study.leetcode.suanfa;

/**
 * @author : fuyuaaa
 * @date : 2020-07-09 09:39
 */
public class KMP {

    public int getIndexOf(String s, String m) {
        if (s == null || m == null || m.length() == 0 || s.length() < m.length()) {
            return -1;
        }
        char[] sc = s.toCharArray();
        char[] mc = m.toCharArray();
        int si = 0, mi = 0;
        int[] next = getNextArray(mc);
        while (si < sc.length && mi < mc.length) {
            if (sc[si] == mc[mi]) {
                si++;
                mi++;
            } else if (next[mi] == -1){
                si++;
            } else {
                mi = next[mi];
            }
        }
        return mi == mc.length? si - mi : -1;
    }


    private int[] getNextArray(char[] ms) {
        if (ms.length == 1) {
            return new int[]{-1};
        }
        int[] next = new int[ms.length];
        next[0] = -1;
        next[1] = 0;
        int pos = 2;
        int cn = 0;
        while (pos < ms.length) {
            if (ms[pos - 1] == ms[cn]) {
                next[pos++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[pos++] = 0;
            }
        }
        return next;

    }
}
