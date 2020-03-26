package top.fuyuaaa.study.leetcode;

/**
 *
 * 999. 车的可用捕获量
 * 在一个 8 x 8 的棋盘上，有一个白色车（rook）。也可能有空方块，白色的象（bishop）和黑色的卒（pawn）。它们分别以字符 “R”，“.”，“B” 和 “p” 给出。大写字符表示白棋，小写字符表示黑棋。
 *
 * 车按国际象棋中的规则移动：它选择四个基本方向中的一个（北，东，西和南），然后朝那个方向移动，直到它选择停止、到达棋盘的边缘或移动到同一方格来捕获该方格上颜色相反的卒。另外，车不能与其他友方（白色）象进入同一个方格。
 *
 * 返回车能够在一次移动中捕获到的卒的数量。
 *
 *
 * 车可以捕获位置 b5，d6 和 f5 的卒。
 * @author : fuyuaaa
 * @date : 2020-03-26 19:00
 */
public class Solution999 {

    @SuppressWarnings("all")
    public int numRookCaptures(char[][] board) {
        int x = -1;
        int y = -1;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 'R') {
                    x = i;
                    y = j;
                }
            }
        }

        if (-1 == x) {
            return 0;
        }
        int max = 0;

        for (int i = y; i >= 0; i--) {
            char current = board[x][i];
            //🐘
            if (current == 'B') {
                break;
            }
            //卒
            if (current == 'p') {
                max++;
                break;
            }
        }

        for (int i = y; i < board[x].length; i++) {
            char current = board[x][i];
            //🐘
            if (current == 'B') {
                break;
            }
            //卒
            if (current == 'p') {
                max++;
                break;
            }
        }

        for (int i = x; i >= 0; i--) {
            char current = board[i][y];
            //🐘
            if (current == 'B') {
                break;
            }
            //卒
            if (current == 'p') {
                max++;
                break;
            }
        }

        for (int i = x; i < board.length; i++) {
            char current = board[i][y];
            //🐘
            if (current == 'B') {
                break;
            }
            //卒
            if (current == 'p') {
                max++;
                break;
            }
        }

        return max;
    }


    public static void main(String[] args) {
        Solution999 solution999 = new Solution999();
        char[][] board = new char[][]{{'.', '.', '.', '.', '.', '.', '.', '.'}, {'.', 'p', 'p', 'p', 'p', 'p', '.', '.'},
            {'.', 'p', 'p', 'B', 'p', 'p', '.', '.'}, {'.', 'p', 'B', 'R', 'B', 'p', '.', '.'}, {'.', 'p', 'p', 'B', 'p', 'p', '.', '.'},
            {'.', 'p', 'p', 'p', 'p', 'p', '.', '.'}, {'.', '.', '.', '.', '.', '.', '.', '.'}, {'.', '.', '.', '.', '.', '.', '.', '.'}};
        System.out.println(solution999.numRookCaptures(board));
    }
}
