package top.fuyuaaa.study.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 你这个学期必须选修 numCourse 门课程，记为 0 到 numCourse-1 。
 * <p>
 * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们：[0,1]
 * <p>
 * 给定课程总量以及它们的先决条件，请你判断是否可能完成所有课程的学习？
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: 2, [[1,0]]
 * 输出: true
 * 解释: 总共有 2 门课程。学习课程 1 之前，你需要完成课程 0。所以这是可能的。
 * 示例 2:
 * <p>
 * 输入: 2, [[1,0],[0,1]]
 * 输出: false
 * 解释: 总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0；并且学习课程 0 之前，你还应先完成课程 1。这是不可能的。
 *  
 * <p>
 * 提示：
 * <p>
 * 输入的先决条件是由 边缘列表 表示的图形，而不是 邻接矩阵 。详情请参见图的表示法。
 * 你可以假定输入的先决条件中没有重复的边。
 * 1 <= numCourses <= 10^5
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/course-schedule
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author : fuyuaaa
 * @date : 2020-05-29 18:01
 */
public class Solution207 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] inDegrees = new int[numCourses];
        HashMap<Integer, HashSet<Integer>> graph = new HashMap<>(numCourses * 4 / 3 + 1);
        for (int i = 0; i < numCourses; i++) {
            graph.put(i, new HashSet<>());
        }
        for (int[] prerequisite : prerequisites) {
            //统计入度
            inDegrees[prerequisite[1]]++;
            //构建图
            graph.get(prerequisite[0]).add(prerequisite[1]);
        }


        //学习课程，入度为0为可学
        Queue<Integer> queue = new LinkedList<>();
        //找出入度为0的, 加到队列里
        for (int i = 0; i < numCourses; i++) {
            if (inDegrees[i] == 0) {
                queue.add(i);
            }
        }

        //统计学过的课
        HashSet<Integer> hasStudy = new HashSet<>(numCourses * 4 / 3 + 1);

        //从队列取元素, 减少元素的出元素 对应的入度。
        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            hasStudy.add(poll);
            HashSet<Integer> out = graph.get(poll);
            for (int o : out) {
                //out的入度-1
                inDegrees[o]--;
                //如果=0，说明这个课可以学了，加到队列
                if (inDegrees[o] == 0) {
                    queue.add(o);
                }
            }
        }

        return hasStudy.size() == numCourses;
    }
}
