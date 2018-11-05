package fuyuaaa.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 2018-11-5
 *格雷编码是一个二进制数字系统，在该系统中，两个连续的数值仅有一个位数的差异。
 *给定一个代表编码总位数的非负整数 n，打印其格雷编码序列。格雷编码序列必须以 0 开头。
 */
public class Solution89 {

    public List<Integer> grayCode(int n) {

        List<Integer> result = new ArrayList<>();

        if (n == 0) {
            result.add(0);
            return result;
        }
        if (n == 1) {
            result.add(0);
            result.add(1);
            return result;
        }

        List<Integer> preGrayCode = grayCode(n - 1);
        for (int i = 0; i < Math.pow(2, n); i++) {
            if (i < Math.pow(2, n - 1)) {
                result.add(preGrayCode.get(i));
            } else {
                int res =  preGrayCode.get( (int)Math.pow(2, n) - i - 1 ) + (int)Math.pow(2, n - 1);
                result.add(res);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Solution89 solution89 = new Solution89();
        System.out.println(solution89.grayCode(3));
    }
}
