package top.fuyuaaa.study.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 914. 卡牌分组
 * 给定一副牌，每张牌上都写着一个整数。
 * <p>
 * 此时，你需要选定一个数字 X，使我们可以将整副牌按下述规则分成 1 组或更多组：
 * <p>
 * 每组都有 X 张牌。
 * 组内所有的牌上都写着相同的整数。
 * 仅当你可选的 X >= 2 时返回 true。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：[1,2,3,4,4,3,2,1]
 * 输出：true
 * 解释：可行的分组是 [1,1]，[2,2]，[3,3]，[4,4]
 * 示例 2：
 * <p>
 * 输入：[1,1,1,2,2,2,3,3]
 * 输出：false
 * 解释：没有满足要求的分组。
 * 示例 3：
 * <p>
 * 输入：[1]
 * 输出：false
 * 解释：没有满足要求的分组。
 * 示例 4：
 * <p>
 * 输入：[1,1]
 * 输出：true
 * 解释：可行的分组是 [1,1]
 * 示例 5：
 * <p>
 * 输入：[1,1,2,2,2,2]
 * 输出：true
 * 解释：可行的分组是 [1,1]，[2,2]，[2,2]
 * <p>
 * 提示：
 * <p>
 * 1 <= deck.length <= 10000
 * 0 <= deck[i] < 10000
 *
 * @author : fuyuaaa
 * @date : 2020-03-27 18:09
 */
public class Solution914 {

    public boolean hasGroupsSizeX(int[] deck) {
        if (deck.length <= 1) {
            return false;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < deck.length; i++) {
            int current = deck[i];
            if (map.containsKey(current)) {
                map.put(current, map.get(current) + 1);
            } else {
                map.put(current, 1);
            }
        }
        int X = Integer.MAX_VALUE;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            X = Math.min(X, entry.getValue());
        }
        if (X == 1) {
            return false;
        }
        for (int i = 2; i <= X; i++) {
            boolean temp = true;
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                if (entry.getValue() % i != 0) {
                    temp = false;
                }
            }
            if (temp) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Solution914 solution914 = new Solution914();
        int[] desk = {0,0,0,1,1,1,2,2,2};
        System.out.println(solution914.hasGroupsSizeX(desk));
    }

}
