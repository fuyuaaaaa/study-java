package java.top.fuyuaaa.study_java.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 如果序列 X_1, X_2, ..., X_n 满足下列条件，就说它是 斐波那契式 的：
 * <p>
 * n >= 3
 * 对于所有 i + 2 <= n，都有 X_i + X_{i+1} = X_{i+2}
 * 给定一个严格递增的正整数数组形成序列，找到 A 中最长的斐波那契式的子序列的长度。如果一个不存在，返回  0 。
 * <p>
 * （回想一下，子序列是从原序列 A 中派生出来的，它从 A 中删掉任意数量的元素（也可以不删），而不改变其余元素的顺序。例如， [3, 5, 8] 是 [3, 4, 5, 6, 7, 8] 的一个子序列
 */
/**
 *  分析： 使用set降低查询时复杂度
 */
public class Solution873 {
    public int lenLongestFibSubseq(int[] A) {

        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < A.length; i++) {
            set.add(A[i]);
        }
        int res = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = i + 1; j < A.length; j++) {
                int resTemp = 2;
                int temp = A[i] + A[j];
                int left = A[j];
                while (set.contains(temp)) {
                    int mid = temp;
                    temp = temp + left;
                    left = mid;
                    resTemp++;
                }
                if (resTemp > res && resTemp > 2) {
                    res = resTemp;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution873 solution873 = new Solution873();
        int A[] = {2,5,6,11,13,14,15,17,18,20,22,27,28,31,32,33,38,41,45,46,50,51,55,56,58,61,68,69,73,77,78,84,96,97,107,114,118,122,128,135,151,154,163,166,182,199,206,219,250,263,270,296,321,334,354,404,429,433,478,520,540,573,692,703,774,841,927,1121,1136,1252,1361,1500,1813,1839,2202,2427,2934,2975,3563,3927,4747,4814,5765,6354,7681,7789,9328,10281,12428,12603,15093,16635,20109,20392,24421,26916,32537,39514,52646,63935};
        System.out.println(solution873.lenLongestFibSubseq(A));
        int B[] = {1, 2, 3, 4, 5, 6, 7, 8,13};
        System.out.println(solution873.lenLongestFibSubseq(B));
    }
}
