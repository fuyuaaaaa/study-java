package top.fuyuaaa.study.leetcode;

/**
 * 892. 三维形体的表面积
 * 在 N * N 的网格上，我们放置一些 1 * 1 * 1  的立方体。
 *
 * 每个值 v = grid[i][j] 表示 v 个正方体叠放在对应单元格 (i, j) 上。
 *
 * 请你返回最终形体的表面积。
 *
 *
 *
 * 示例 1：
 *
 * 输入：[[2]]
 * 输出：10
 * 示例 2：
 *
 * 输入：[[1,2],[3,4]]
 * 输出：34
 * 示例 3：
 *
 * 输入：[[1,0],[0,2]]
 * 输出：16
 * 示例 4：
 *
 * 输入：[[1,1,1],[1,0,1],[1,1,1]]
 * 输出：32
 * 示例 5：
 *
 * 输入：[[2,2,2],[2,1,2],[2,2,2]]
 * 输出：46
 *
 *
 * 提示：
 *
 * 1 <= N <= 50
 * 0 <= grid[i][j] <= 50
 * @author : fuyuaaa
 * @date : 2020-03-25 22:23
 */
public class Solution892 {

    public int surfaceArea(int[][] grid) {
        int max = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                int now = grid[i][j];
                if (now == 0) {
                    continue;
                }
                max += calculateMianji(now);
                if (j != grid[i].length - 1) {
                    int right = grid[i][j + 1];
                    if (right > 0) {
                        max -= 2 * Math.min(right, now);
                    }
                }
                if (i != grid.length - 1) {
                    int down = grid[i + 1][j];
                    if (down > 0) {
                        max -= 2 * Math.min(down, now);
                    }
                }
            }
        }
        return max;
    }

    /**
     * 计算面积
     */
    private int calculateMianji(int num) {
        if (num <=0) {
            throw new RuntimeException("你太抽象了");
        }
        return num * 6 - 2 * (num - 1);
    }

    public static void main(String[] args) {
        Solution892 solution892 = new Solution892();
        int[][] grid = {{2}};
        System.out.println(solution892.surfaceArea(grid));
        grid = new int[][]{{1, 2}, {3, 4}};
        System.out.println(solution892.surfaceArea(grid));
    }
}
