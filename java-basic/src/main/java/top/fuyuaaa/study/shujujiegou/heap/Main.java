package top.fuyuaaa.study.shujujiegou.heap;

import java.util.Random;

/**
 * @Package_Name: heap
 * @Auther: fuyua
 * @Date: created in 14:33 2018/7/7
 */
public class Main {

    private static  double testHeap(Integer[] testData, boolean isHeapify){
        long start = System.nanoTime();

        MaxHeap<Integer> maxHeap;
        if (isHeapify)
            maxHeap = new MaxHeap<>(testData);
        else {
            maxHeap = new MaxHeap<>();
            for(int num: testData)
                maxHeap.add(num);
        }

        int[] arr = new int[testData.length];
        for (int i = 0; i < testData.length; i++) {
            arr[i] = maxHeap.extractMax();
        }

        for (int i = 1; i < testData.length; i++) {
            if (arr[i-1] < arr[i]){
                throw new IllegalArgumentException("error");
            }
        }
        System.out.println("complete!");

        long end = System.nanoTime();

        return (end - start) / 1000000000.0;
    }

    public static void main(String[] args) {
        int n = 1000000;
        Random random = new Random();
        Integer[] testData = new Integer[n];
        for (int i = 0; i < n; i++) {
            testData[i] = random.nextInt(Integer.MAX_VALUE);
        }
        double time1 = testHeap(testData, false);
        System.out.println("withour heapify" + time1 + "s");
        double time2 = testHeap(testData, true);
        System.out.println("heapify" + time2 + "s");

    }


}
