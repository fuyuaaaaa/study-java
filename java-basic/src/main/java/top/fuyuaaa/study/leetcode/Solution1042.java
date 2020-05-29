package top.fuyuaaa.study.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * 不邻接植花
 * 有 N 个花园，按从 1 到 N 标记。在每个花园中，你打算种下四种花之一。
 * <p>
 * paths[i] = [x, y] 描述了花园 x 到花园 y 的双向路径。
 * <p>
 * 另外，没有花园有 3 条以上的路径可以进入或者离开。
 * <p>
 * 你需要为每个花园选择一种花，使得通过路径相连的任何两个花园中的花的种类互不相同。
 * <p>
 * 以数组形式返回选择的方案作为答案 answer，其中 answer[i] 为在第 (i+1) 个花园中种植的花的种类。花的种类用  1, 2, 3, 4 表示。保证存在答案。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：N = 3, paths = [[1,2],[2,3],[3,1]]
 * 输出：[1,2,3]
 * 示例 2：
 * <p>
 * 输入：N = 4, paths = [[1,2],[3,4]]
 * 输出：[1,2,1,2]
 * 示例 3：
 * <p>
 * 输入：N = 4, paths = [[1,2],[2,3],[3,4],[4,1],[1,3],[2,4]]
 * 输出：[1,2,3,4]
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= N <= 10000
 * 0 <= paths.size <= 20000
 * 不存在花园有 4 条或者更多路径可以进入或离开。
 * 保证存在答案。
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/flower-planting-with-no-adjacent
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author : fuyuaaa
 * @date : 2020-05-29 09:27
 */
public class Solution1042 {

    public int[] gardenNoAdj(int N, int[][] paths) {
        HashMap<Integer, Set<Integer>> graph = new HashMap<>(N * 4 / 3 + 1);
        for (int i = 0; i < N; i++) {
            graph.put(i, new HashSet<>());
        }

        for (int[] path : paths) {
            //无向图, 所以需要两个节点都加上
            graph.get(path[0] - 1).add(path[1] - 1);
            graph.get(path[1] - 1).add(path[0] - 1);
        }

        //结果数组
        int[] res = new int[N];
        for (int i = 0; i < N; i++) {

            boolean[] used = new boolean[5];
            for(int adj : graph.get(i)) {
                //如果当前没有被染色, res[adj] = 0, used[0] 就是用来冗余这个数据的。
                used[res[adj]] = true;
            }

            //找个颜色给当前节点染色，根据题意一定不会重复。
            for (int j = 1; j <= 4; j++) {
                if (!used[j]){
                    res[i] = j;
                }
            }
        }
        return res;
    }
}
