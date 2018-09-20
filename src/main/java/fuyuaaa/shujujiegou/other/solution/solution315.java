package fuyuaaa.shujujiegou.other.solution;

import java.util.ArrayList;
import java.util.List;

/**
 * @Package_Name: com.fuyu.solution
 * @Auther: fuyua
 * @Date: created in 23:44 2018/6/2
 */
public class solution315 {
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < nums.length; i++) {
            int a = 0;
            for (int j = i; j < nums.length; j++) {
                if (nums[i] > nums[j]) {
                    a++;
                }
            }
            list.add(a);
        }
        return list;
    }

    public static void main(String[] args) {
        solution315 s = new solution315();
        int[] nums = {5, 2, 6, 1};
        System.out.println(s.countSmaller(nums));
    }
}
