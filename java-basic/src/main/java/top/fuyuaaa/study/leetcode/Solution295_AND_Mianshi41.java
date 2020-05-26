package top.fuyuaaa.study.leetcode;


import java.util.PriorityQueue;

/**
 * 数据流中的中位数
 *
 * @author : fuyuaaa
 * @date : 2020-05-26 11:32
 */
@SuppressWarnings("all")
public class Solution295_AND_Mianshi41 {

    static class MedianFinder {

        private PriorityQueue<Integer> maxHeap = new PriorityQueue<>((x, y) -> (y - x));
        private PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        public MedianFinder() {

        }

        public void addNum(int num) {
            maxHeap.offer(num);
            minHeap.offer(maxHeap.poll());
            if (minHeap.size() - maxHeap.size() >1) {
                maxHeap.offer(minHeap.poll());
            }
        }

        public double findMedian() {
            if (minHeap.size() == 0) {
                return 0d;
            }
            if (maxHeap.size() == minHeap.size()) {
                return (double) (maxHeap.peek() + minHeap.peek()) / 2;
            }
            return minHeap.peek();
        }
    }
}
