package top.fuyuaaa.study.leetcode.suanfa;

/**
 * Manacher算法 ，马拉车算法
 *
 * @author : fuyuaaa
 * @date : 2020-06-12 15:15
 */
@SuppressWarnings("all")
public class ManacherAlgorithm {
    public String preProcess(String s) {
        int n = s.length();
        if (n == 0) {
            return "^$";
        }
        String ret = "^";
        for (int i = 0; i < n; i++)
            ret += "#" + s.charAt(i);
        ret += "#$";
        return ret;
    }

    // 马拉车算法
    public String longestPalindrome(String s) {
        String T = preProcess(s);
        int n = T.length();
        int[] P = new int[n];
        int C = 0, R = 0;
        for (int i = 1; i < n - 1; i++) {
            int i_mirror = 2 * C - i;
            if (R > i) {
                P[i] = Math.min(R - i, P[i_mirror]);// 防止超出 R
            } else {
                P[i] = 0;// 等于 R 的情况
            }

            // 碰到之前讲的三种情况时候，需要利用中心扩展法
            while (T.charAt(i + 1 + P[i]) == T.charAt(i - 1 - P[i])) {
                P[i]++;
            }

            // 判断是否需要更新 R
            if (i + P[i] > R) {
                C = i;
                R = i + P[i];
            }

        }

        // 找出 P 的最大值
        int maxLen = 0;
        int centerIndex = 0;
        for (int i = 1; i < n - 1; i++) {
            if (P[i] > maxLen) {
                maxLen = P[i];
                centerIndex = i;
            }
        }
        int start = (centerIndex - maxLen) / 2; //最开始讲的求原字符串下标
        return s.substring(start, start + maxLen);
    }

    public static void main(String[] args) {
        ManacherAlgorithm manacherAlgorithm = new ManacherAlgorithm();
        System.out.println(manacherAlgorithm.manacherAlgorithm("abcba"));
        System.out.println(manacherAlgorithm.manacherAlgorithm(""));
        System.out.println(manacherAlgorithm.manacherAlgorithm("cbbd"));
    }

    public String manacherAlgorithm(String s) {

        if (s == null || s.length() == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("$");
        for (int i = 0; i < s.length(); i++) {
            sb.append("#" + s.charAt(i));
        }
        sb.append("#^");

        int n = sb.length();
        int[] p = new int[n];
        int c = 0, r = 0;
        for (int i = 1; i < n - 1; i++) {
            int i_mimir = 2 * c - i;

            if (r > i) {
                p[i] = Math.min(r - i, p[i_mimir]);
            } else {
                p[i] = 0;
            }

            while (sb.charAt(i + 1 + p[i]) == sb.charAt(i - 1 - p[i])) {
                p[i]++;
            }

            if (i + p[i] > r) {
                r = i + p[i];
                c = i;
            }
        }

        int maxLength = 0;
        int maxCenter = 0;

        for (int i = 1; i < n - 1; i++) {
            if (p[i] > maxLength) {
                maxLength = p[i];
                maxCenter = i;
            }
        }

        int start = (maxCenter - maxLength) / 2;
        return s.substring(start, start + maxLength);
    }
}
