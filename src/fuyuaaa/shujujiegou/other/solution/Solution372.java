package fuyuaaa.shujujiegou.other.solution;

/**
 * super row
 * @Package_Name: com.fuyu.solution
 * @Auther: fuyua
 * @Date: created in 22:33 2018/5/23
 */
class Solution372 {
    public int superPow(int a, int[] b) {
        long bsuper = 0;
        int j = 0;
        for (int i = b.length-1; i > -1; i--) {
            bsuper = bsuper + b[i]*((long)Math.pow(10, j));
            j = j+1;
        }
        System.out.println(Math.pow(a, bsuper));
        int res = (int)(Math.pow(a, bsuper) % 1337);
        return res;
    }

    public static void main(String[] args) {
        Solution372 solution372 = new Solution372();
        int[] b = {2,0,0};
        System.out.println(solution372.superPow(2147483647, b));
    }
}