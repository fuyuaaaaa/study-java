package top.fuyuaaa.study.leetcode.suanfa;

/**
 * @author: fuyuaaaaa
 * @description: 冒泡排序
 * @program: study-java
 * @creat: 2018-11-25 15:47
 **/
public class BubbleSort {
    public static void main(String[] args) {
        int a[] = {1, 5, 2, 8, 1, 6, 8, 7, 2, 6};
        bubbleSort(a);
        for (int i : a) {
            System.out.println(i);
        }
    }

    public static void bubbleSort(int []a){
        for (int i = 0; i < a.length; i++) {
            for (int j = 1; j < a.length; j++) {
                if (a[j] > a[j-1]) {
                    int temp = a[j];
                    a[j] = a[j-1];
                    a[j-1] = temp;
                }
            }
        }
    }
}
