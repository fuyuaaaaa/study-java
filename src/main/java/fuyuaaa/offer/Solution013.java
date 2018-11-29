package fuyuaaa.offer;

/**
 * @author: fuyuaaaaa
 * @description:
 * 题目：输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，
 * 所有的偶数位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变
 * @program: study-java
 * @creat: 2018-11-29 10:31
 **/
public class Solution013 {
    public void reOrderArray(int [] array) {
        int length = array.length;
        for (int i = 0; i < length; i++) {
            for (int j = 1; j < length; j++) {
                if (!isEven(array[j]) && isEven(array[j - 1])) {
                    swap(array,j ,j-1 );
                }
            }

        }
    }
    public boolean isEven(int num){
        return num % 2 == 0;
    }

    public void swap(int a[], int l, int r) {
        int temp = a[l];
        a[l] = a[r];
        a[r] = temp;
    }

    public static void main(String[] args) {
        int a[] = {2,4,6,1,3,5,7};
        Solution013 solution013 = new Solution013();
        solution013.reOrderArray(a);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]+" ");
        }
    }
}
