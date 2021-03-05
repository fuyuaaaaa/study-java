package top.fuyuaaa.study.leetcode.suanfa;

/**
 * @author: fuyuaaaaa
 * @description: 冒泡排序
 * @program: study-java
 * @creat: 2018-11-25 15:47
 **/
public class BubbleSort {
    public static void main(String[] args) {
        String s = "codeleet";
        int[] indices = new int[]{4, 5, 6, 7, 0, 2, 1, 3};
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < indices.length; i++) {
            sb.append(s.charAt(indices[i]));
        }
        System.out.println(sb.toString());
    }

    public static void bubbleSort(int[] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 1; j < a.length; j++) {
                if (a[j] > a[j - 1]) {
                    int temp = a[j];
                    a[j] = a[j - 1];
                    a[j - 1] = temp;
                }
            }
        }
    }
}
