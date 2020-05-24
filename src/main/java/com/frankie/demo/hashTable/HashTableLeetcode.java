package com.frankie.demo.hashTable;

import java.util.HashMap;

/**
 * @author: Yao Frankie
 * @date: 2020/5/24 22:43
 */
public class HashTableLeetcode {

    public static void main(String[] args) {
        p128();
    }

    /**
     * 128. Longest Consecutive Sequence
     */
    private static void p128() {
        int[] nums = {2, 3, 1, 6, 5, 4};
        int ret1 = longestConsecutive(nums);
        System.out.println(ret1);
    }

    private static int longestConsecutive(int[] nums) {
        HashMap<Integer, Integer> hMap = new HashMap<>(64);
        int max = 0;
        for (int n: nums){
            if (hMap.containsKey(n)){
                continue;
            }
            int l = hMap.getOrDefault(n - 1, 0);
            int r = hMap.getOrDefault(n + 1, 0);
            int sum = l + r + 1;
            max = Math.max(max, sum);
            hMap.put(n, sum);
            if (l > 0) hMap.put(n - l, sum);
            if (r > 0) hMap.put(n + r, sum);
        }
        return max;
    }
}
