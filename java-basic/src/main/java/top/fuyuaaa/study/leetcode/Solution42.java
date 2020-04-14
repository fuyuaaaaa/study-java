package top.fuyuaaa.study.leetcode;

import java.util.Stack;

/**
 * @author : fuyuaaa
 * @date : 2020-04-04 19:18
 */
public class Solution42 {
    /**
     * 会超时...
     */
    public int trap1(int[] height) {
        int max = 0;
        for (int i = 0; i < height.length; i++) {
            max = Math.max(max, height[i]);
        }
        int maxWater = 0;
        for (int i = 1; i <= max; i++) {
            int leftIndex = -1;
            int rightIndex = -1;
            for (int j = 0; j < height.length; j++) {
                if (height[j] >= i) {
                    leftIndex = j;
                    break;
                }
            }

            for (int j = height.length - 1; j >= 0; j--) {
                if (height[j] >= i) {
                    rightIndex = j;
                    break;
                }
            }
            if (leftIndex == -1  || rightIndex == -1 || leftIndex == rightIndex) {
                break;
            }

            for (int j = leftIndex; j < rightIndex; j++) {
                if (height[j] < i) {
                    maxWater++;
                }
            }

        }
        return maxWater;
    }

    /**
     * 栈解法
     */
    public int trap2(int[] height) {
        if (height == null) {
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        int ans = 0;
        for (int i = 0; i < height.length; i++) {
            while(!stack.isEmpty() && height[stack.peek()] < height[i]) {
                int curIdx = stack.pop();
                // 如果栈顶元素一直相等，那么全都pop出去，只留第一个。
                while (!stack.isEmpty() && height[stack.peek()] == height[curIdx]) {
                    stack.pop();
                }
                if (!stack.isEmpty()) {
                    int stackTop = stack.peek();
                    // stackTop此时指向的是此次接住的雨水的左边界的位置。右边界是当前的柱体，即i。
                    // Math.min(height[stackTop], height[i]) 是左右柱子高度的min，减去height[curIdx]就是雨水的高度。
                    // i - stackTop - 1 是雨水的宽度。
                    ans += (Math.min(height[stackTop], height[i]) - height[curIdx]) * (i - stackTop - 1);
                }
            }
            stack.add(i);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution42 solution42 = new Solution42();
        int[] height = new int[]{0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(solution42.trap1(height));
        System.out.println(solution42.trap2(height));
    }
}
