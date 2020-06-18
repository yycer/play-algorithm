package com.frankie.demo.contest;

import java.util.*;

/**
 * @author: Yao Frankie
 * @date: 2020/6/18 11:17
 */
public class LC_193 {

    public static void main(String[] args) {
        p1481(); // 1481. Least Number of Unique Integers after K Removals
    }

    private static void p1481() {
        int[] arr = {1, 2, 2, 3, 4, 4, 5};
        int k = 1;
        findLeastNumOfUniqueInts(arr, k);
    }

    private static int findLeastNumOfUniqueInts(int[] arr, int k) {

        int len = arr.length;
        HashMap<Integer, Integer> map = new HashMap<>(len);
        for (int i: arr){
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        PriorityQueue<Object> pq = new PriorityQueue<>(Comparator.comparing(map::get));
        pq.addAll(map.keySet());

        while (k > 0){
            k -= map.get(pq.poll());
        }
        return k < 0 ? pq.size() + 1 : pq.size();
    }
}
