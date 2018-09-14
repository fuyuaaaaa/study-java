package fuyuaaa.shujujiegou.heap;

import java.util.*;
import java.util.PriorityQueue;

/**
 * @Auther: fuyuaaaaa
 * @Description: leetcode solution347
 * @Package_Name: shujujiegou2
 * @Date: created in 2018-07-07 16:29
 */
public class Solution347_2 {

    private class Freq {
        int e, freq;
        public Freq(int e, int freq) {
            this.e = e;
            this.freq = freq;
        }
    }
    private class FreeComparator implements Comparator<Freq> {
        @Override
        public int compare(Freq a, Freq b) {
            return a.freq - b.freq;
        }
    }

    public List<Integer> topKFrequent(int[] nums, int k) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for( int num: nums){
            if (map.containsKey(num))
                map.put(num, map.get(num) + 1);
            else
                map.put(num, 1);
        }
        PriorityQueue<Freq> pq = new PriorityQueue<>(new FreeComparator());
        for (int key : map.keySet()) {
            if (pq.size() < k)
                pq.add(new Freq(key, map.get(key)));
            else if (map.get(key) > pq.peek().freq){
                pq.remove();
                pq.add(new Freq(key,map.get(key)));
            }
        }

        LinkedList<Integer> res = new LinkedList<>();
        while (!pq.isEmpty())
            res.add(pq.remove().e);
        return res;
    }
}
