package java.top.fuyuaaa.study_java.leetcode;

/**
 * @author: fuyuaaaaa
 * @description: leetcode 翻转矩阵后的得分
 * 题目：有一个二维矩阵 A 其中每个元素的值为 0 或 1 。
 * 移动是指选择任一行或列，并转换该行或列中的每一个值：将所有 0 都更改为 1，将所有 1 都更改为 0。
 * 在做出任意次数的移动后，将该矩阵的每一行都按照二进制数来解释，矩阵的得分就是这些数字的总和。
 * 返回尽可能高的分数。
 * 分析：1、把第一列的通过行翻转全换为1；2、便利后面的列，如果1的数量小于0的数量，反转该列；3、计算值
 * @program: study-java
 * @creat: 2018-11-13 10:51
 **/
public class Solution861 {
    public int matrixScore(int[][] A) {
        for (int i = 0; i < A.length; i++) {
            if (A[i][0] == 0) {//翻转行
                swapRow(A, i);
            }
        }
        for (int i = 1; i < A[0].length; i++) {
            int num0 = 0;
            int num1 = 0;
            for (int j = 0; j < A.length; j++) {
                if (A[j][i] == 0) num0++;
                if (A[j][i] == 1) num1++;
            }
            if (num0 > num1) {
                swapCol(A, i);
            }
        }

        return result(A);
    }

    public void swapRow(int[][] A, int row) {
        for (int i = 0; i < A[row].length; i++) {
            if (0 == A[row][i]) {
                A[row][i] = 1;
            } else {
                A[row][i] = 0;
            }
        }
    }

    public void swapCol(int[][] A, int col) {
        for (int i = 0; i < A.length; i++) {
            if (A[i][col] == 0) {
                A[i][col] = 1;
            } else {
                A[i][col] = 0;
            }
        }
    }

    public int result(int[][] A) {
        int res = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                if (A[i][j] == 0)
                    continue;
                res += Math.pow(2, A[0].length - 1 - j);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] A = {{0, 0, 1, 1}, {1, 0, 1, 0}, {1, 1, 0, 0}};
        Solution861 solution861 = new Solution861();
        System.out.println(solution861.matrixScore(A));
    }
}
