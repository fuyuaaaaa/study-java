package top.fuyuaaa.study.offer;


/**
 *在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 *分析：从右上角开始查找，因为右上角的顶点数据小于下方，大于左边，所以可以一直缩小区间。
 */
public class Solution001 {

    public boolean Find(int target, int[][] array) {
        int row = array.length;
        int col = array[0].length;

        int i = 0, j = col - 1;
        while (i <= row - 1 && j >= 0) {
            int temp = array[i][j];
            if (target > temp) {
                i++;
            } else if (target < temp) {
                j--;
            } else {
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        Solution001 solution001 = new Solution001();
        int a[][] = {
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };
        System.out.println(solution001.Find(18, a));
    }
}
