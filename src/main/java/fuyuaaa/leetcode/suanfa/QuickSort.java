package fuyuaaa.leetcode.suanfa;

/**
 * @author: fuyuaaaaa
 * @description: 快速排序
 * @program: study-java
 * @creat: 2018-11-07 09:15
 **/
public class QuickSort {

    public void quickSort(int[] a, int left, int right) {

        if (left < right) {
            int i = left, j = right, x = a[left];
            while (i < j && a[j] >= x) {
                j--;
            }
            if (i < j) {
                swap(a, i, j);
            }
            while (i < j && a[i] <= x) {
                i++;
            }
            if (i < j) {
                swap(a, i, j);
            }

            a[i] = x;
            quickSort(a, left, right - 1);
            quickSort(a, left + 1, right);
        }
    }

    public void swap(int[] a, int index1, int index2) {
        int temp = a[index1];
        a[index1] = a[index2];
        a[index2] = temp;
    }

    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
        int a[] = {15, 3, 4, 1, 3, 2, 5, 1, 68, 41, 5, 48, 8};
        quickSort.quickSort2(a, 0, a.length - 1);
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }

    public void quickSort2(int a[], int left, int right) {
        if (left < right) {
            int mid = quick2(a, left, right);
            quickSort2(a, left, mid - 1);
            quickSort2(a, mid + 1, right);
        }
    }

    public int quick2(int a[], int left, int right) {
        int x = a[left];
        while (left < right) {
            while (left < right && a[right] >= x) {
                right--;
            }
            swap(a, left, right);
            while (left < right && a[left] <= x) {
                left++;
            }
            swap(a, left, right);
        }
        return left;
    }
}
