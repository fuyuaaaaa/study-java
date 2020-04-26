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
    }
}
