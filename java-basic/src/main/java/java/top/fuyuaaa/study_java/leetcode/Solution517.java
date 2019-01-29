package java.top.fuyuaaa.study_java.leetcode;

/**
 * 2018-11-6 16：19
 * 假设有 n 台超级洗衣机放在同一排上。开始的时候，每台洗衣机内可能有一定量的衣服，也可能是空的。
 * 在每一步操作中，你可以选择任意 m （1 ≤ m ≤ n） 台洗衣机，与此同时将每台洗衣机的一件衣服送到相邻的一台洗衣机。
 * 给定一个非负整数数组代表从左至右每台洗衣机中的衣物数量，请给出能让所有洗衣机中剩下的衣物的数量相等的最少的操作步数。如果不能使每台洗衣机中衣物的数量相等，则返回 -1。
 */
public class Solution517 {
    public int findMinMoves(int[] machines) {
        int size = machines.length;
        int total = 0;
        for (int i = 0; i < size; i++) {
            total += machines[i];
        }
        if (total % size != 0) {
            return -1;
        }

        int minMove = 0, ave = total / size, temp = 0;
        for (int i = 0; i < size; i++) {
            temp += machines[i] - ave;
            minMove = Math.max(minMove, Math.max(machines[i] - ave, Math.abs(temp)));
        }

        return minMove;
    }

    public static void main(String[] args) {
        Solution517 solution517 = new Solution517();
        int[] a = {0, 0, 11, 5};
        System.out.println(solution517.findMinMoves(a));
    }
}
