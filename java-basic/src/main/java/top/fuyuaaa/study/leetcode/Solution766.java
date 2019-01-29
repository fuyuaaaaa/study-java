package top.fuyuaaa.study.leetcode;

/**
 * @author: fuyuaaaaa
 * @description: 托普利茨矩阵
 * 题目：如果一个矩阵的每一方向由左上到右下的对角线上具有相同元素，那么这个矩阵是托普利茨矩阵。
 * 给定一个 M x N 的矩阵，当且仅当它是托普利茨矩阵时返回 True。
 * @program: study-java
 * @creat: 2018-11-28 15:46
 **/
public class Solution766 {
    public static boolean isToeplitzMatrix(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        for (int i = 0; i < row-1; i++) {
            for (int j = 0; j < col-1; j++) {
                if (matrix[i][j] != matrix[i + 1][j + 1]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int n[][] = {
                {1, 2, 3, 4},
                {5, 1, 2, 3},
                {9, 5, 1, 2}
        };
        System.out.println(isToeplitzMatrix(n));
    }
}
