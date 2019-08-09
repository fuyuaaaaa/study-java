package top.fuyuaaa.study.leetcode;

import com.alibaba.fastjson.JSON;

/**
 * @description :  
 * @author : fuyuaaa
 * @create : 2019-08-08 17:09
 */
public class Solution115 {

    public static void main(String[] args) {
        Solution115 solution115 = new Solution115();
        System.out.println(solution115.numDistinct("rabbbit", "rabbit"));
        System.out.println(solution115.numDistinct("rabbbit", "rabbat"));
    }
    public int numDistinct(String s, String t) {
        int[][] dp = new int[t.length() + 1][s.length() + 1];
        //初始化第一行
        for(int j = 0; j <= s.length(); j++){
            dp[0][j] = 1;
        }

        for(int i = 1; i <= t.length(); i++){
            for(int j = 1; j <= s.length(); j++){
                if(t.charAt(i-1) == s.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] + dp[i][j-1];
                }else {
                    dp[i][j] = dp[i][j-1];
                }
            }
        }
        System.out.println(JSON.toJSONString(dp).replaceAll("]","]\n"));
        return dp[t.length()][s.length()];
    }
}
