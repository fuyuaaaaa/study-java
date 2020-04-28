package top.fuyuaaa.study.leetcode.suanfa;

import com.alibaba.fastjson.JSONObject;

/**
 * @author : fuyuaaa
 * @date : 2020-04-26 15:51
 */
@SuppressWarnings("all")
public class SortingAlgorithm {

    public void bubbleSort(int[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (array[j] > array[j + 1]) {
                    swap(array, j, j + 1);
                }
            }
        }
    }

    public void selectSort(int[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            int maxIndex = 0;
            for (int j = 1; j <= i; j++) {
                if (array[maxIndex] < array[j]) {
                    maxIndex = j;
                }
            }
            swap(array, maxIndex, i);
        }
    }

    public void insertSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            for (int j = i; j > 0; j--) {
                if (array[j] < array[j - 1]) {
                    swap(array, j, j - 1);
                } else {
                    break;
                }
            }
        }
    }

    private void swap(int[] array, int j, int i) {
        if (i == j) {
            return;
        }
        array[j] = array[j] ^ array[i];
        array[i] = array[j] ^ array[i];
        array[j] = array[j] ^ array[i];
    }


    /**
     * master公式的使用
     * T(N) = a*T(N/b) + O(N^d)1)
     * log(b,a) > d -> 复杂度为O(N^log(b,a))
     * log(b,a) = d -> 复杂度为O(N^d * logN)
     * log(b,a) < d -> 复杂度为O(N^d)
     * 归并排序的细节讲解与复杂度分析
     * 时间复杂度O(N*logN)，额外空间复杂度O(N)
     */
    public void mergeSort(int[] array) {
        if (array.length == 1) {
            return;
        }

        mergeProcess(array, 0, array.length - 1);
    }

    private void mergeProcess(int[] array, int l, int r) {
        if (l == r) {
            return;
        }
        int[] temp = new int[array.length];

        int mid = l + ((r - l) >> 1);
        mergeProcess(array, l, mid);
        mergeProcess(array, mid + 1, r);

        int p1 = l;
        int p2 = mid + 1;
        int index = l;
        while (p1 <= mid && p2 <= r) {
            temp[index++] = array[p1] > array[p2] ? array[p2++] : array[p1++];
        }
        while (p1 <= mid) {
            temp[index++] = array[p1++];
        }
        while (p2 <= r) {
            temp[index++] = array[p2++];
        }
        for (int i = l; i <= r; i++) {
            array[i] = temp[i];
        }
    }


    public void heapSort(int[] array) {

        for (int i = 0; i < array.length; i++) {
            heapInsert(array, i);
        }

        int size = array.length;
        swap(array, 0, --size);
        while (size > 0) {
            heapify(array, 0, size);
            swap(array, 0, --size);
        }

    }

    private void heapInsert(int[] array, int index) {
        //(index-1)/2 -> 父节点的index
        while (array[index] > array[(index - 1) / 2]) {
            swap(array, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    private void heapify(int[] array, int index, int size) {
        int left = index << 1 + 1;
        while (left < size) {
            int largest = left + 1 < size && array[left] < array[left + 1] ? left + 1 : left;
            largest = array[index] > array[largest] ? index : largest;
            if (largest == index) {
                return;
            }
            swap(array, largest, index);
            index = largest;
            left = index << 1 + 1;
        }
    }


    public static void main(String[] args) {
        SortingAlgorithm sortingAlgorithm = new SortingAlgorithm();
        int[] array = new int[]{4, 3, 2, 1};
        sortingAlgorithm.bubbleSort(array);
        System.out.println(JSONObject.toJSONString(array));


        array = new int[]{4, 3, 2, 1};
        sortingAlgorithm.selectSort(array);
        System.out.println(JSONObject.toJSONString(array));


        array = new int[]{4, 3, 2, 1};
        sortingAlgorithm.insertSort(array);
        System.out.println(JSONObject.toJSONString(array));


        array = new int[]{4, 3, 2, 1};
        sortingAlgorithm.mergeSort(array);
        System.out.println(JSONObject.toJSONString(array));


        array = new int[]{4, 3, 2, 1};
        sortingAlgorithm.heapSort(array);
        System.out.println(JSONObject.toJSONString(array));
    }
}
