package com.frankie.demo.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author: Yao Frankie
 * @date: 2020/5/12 11:41
 */
public class GreedyLeetcode {

    public static void main(String[] args) {
//        p455();
//        p435();
//        p452();
//        p121();
//        p122();
//        p605();
//        p392();
//        p665();
//        p53();
//        p763();
//        p406();
    }

    /**
     * 406. Queue Reconstruction by Height
     */
    private static void p406() {
        int[][] people = {{7,0}, {4,4}, {7,1}, {5,0}, {6,1}, {5,2}};
        int[][] ret = reconstructQueue(people);
        for (int[] child: ret){
            System.out.println(Arrays.toString(child));
        }
    }

    private static void listAddIndexTest() {
        ArrayList<Integer> list = new ArrayList<>(10);
        list.add(0);
        list.add(1);
        list.add(2);

        list.add(1, 3);
        System.out.println(list); // [0, 3, 1, 2]
    }

    private static void sort406Test() {
        int[][] people = {{7,0}, {4,4}, {7,1}, {5,0}, {6,1}, {5,2}};

        // [h, k]，先按照h倒序，若h相等，则根据k升序排列。
        Arrays.sort(people, (a, b) -> (a[0] == b[0]) ? a[1] - b[1] : b[0] - a[0]);

        for (int[] child: people){
            System.out.println(Arrays.toString(child));
        }
    }

    private static int[][] reconstructQueue(int[][] people) {
        ArrayList<int[]> retList = new ArrayList<>();
        if (people == null || people.length == 0){
            return new int[0][0];
        }
        Arrays.sort(people, (a, b) -> (a[0] == b[0]) ? a[1] - b[1] : b[0] - a[0]);
        for (int[] p: people){
            retList.add(p[1], p);
        }
        return retList.toArray(new int[people.length][]);
    }

    /**
     * 763. Partition Labels
     */
    private static void p763() {
//        String S = "aaa";
//        partitionLabelsOriginNotWork(S);
        String S1 = "ababcbacadefegdehijhklij";
        List<Integer> ret = partitionLabels(S1);
        System.out.println(ret);
    }

    private static List<Integer> partitionLabels(String S) {

        List<Integer> retList = new ArrayList<>();
        int[] lastIndexArr = new int[26];
        for (int i = 0; i < S.length(); i++){
            lastIndexArr[S.charAt(i) - 97] = i;
        }

        int firstIndex = 0;
        while (firstIndex < S.length()){
            int lastIndex = firstIndex;
            for (int j = firstIndex; j < S.length() && j <= lastIndex; j++){
                int index = lastIndexArr[S.charAt(j) - 'a'];
                if (index > lastIndex){
                    lastIndex = index;
                }
            }
            retList.add(lastIndex - firstIndex + 1);
            firstIndex = lastIndex + 1;
        }

        return retList;
    }

    private static List<Integer> partitionLabelsOriginNotWork(String S) {
        ArrayList<Integer> ret = new ArrayList<>();
        if (S == null || S.length() == 0){
            return ret;
        }

        for (int i = 0; i < S.length();){
            int curLastIndex = S.lastIndexOf(S.charAt(i));
            if (curLastIndex > i){
                for (int j = i + 1; j < curLastIndex; j++){

                }
            }

        }

        return null;
    }

    /**
     * 53. Maximum Subarray
     */
    private static void p53() {
//        int[] nums1 = {5, -2, 4, 9, -3};
//        int ret1 = maxSubArrayOriginNotWork(nums1);
//        System.out.println(ret1);
        int[] nums2 = {5, -2, 4, 9, -3};
        int ret2 = maxSubArray(nums2);
        System.out.println(ret2);
    }

    private static int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0){
            return 0;
        }
        int preSum = nums[0];
        int maxSum = preSum;

        for (int i = 1; i < nums.length; i++){
            preSum = preSum > 0 ? preSum + nums[i] : nums[i];
            maxSum = Math.max(maxSum, preSum);
        }
        return maxSum;
    }

    private static int maxSubArrayOriginNotWork(int[] nums) {
         int max = 0, maxSoFar = 0;
         for (int i = 0; i < nums.length; i++){
             max += nums[i];
             maxSoFar = Math.max(maxSoFar, max);
         }
         return maxSoFar;
    }

    /**
     * 665. Non-decreasing Array
     */
    private static void p665() {
//        int[] nums1 = {4, 2, 3};
//        boolean ret1 = checkPossibilityOriginNotWork(nums1);
//        System.out.println(ret1);
//        int[] nums2 = {4, 3, 2};
//        boolean ret2 = checkPossibilityOriginNotWork(nums2);
//        System.out.println(ret2);
        int[] nums3 = {3, 4, 2, 3};
        boolean ret3 = checkPossibility(nums3);
        System.out.println(ret3);
    }

    private static boolean checkPossibility(int[] nums) {

        int threshold = 0;
        for (int i = 1; i < nums.length; i++){
            if (nums[i] >= nums[i - 1]){
                continue;
            }
            threshold++;
            if ((i - 2) >= 0 && nums[i - 2] > nums[i]){
                nums[i] = nums[i - 1];
            } else {
                nums[i - 1] = nums[i];
            }
        }
        return threshold <= 1;
    }

    private static boolean checkPossibilityOriginNotWork(int[] nums) {
        if (nums == null || nums.length == 0){
            return false;
        }
        int threshold = 0;
        for (int i = 1; i < nums.length; i++){
            if (nums[i] < nums[i - 1]){
                threshold++;
            }
        }
        return threshold <= 1;
    }

    /**
     * 392. Is Subsequence
     */
    private static void p392() {

//        String s1 = "abc";
//        String t1 = "ahbgdc";
//        boolean ret1 = isSubsequence(s1, t1);
//        System.out.println(ret1);
//        String s2 = "axc";
//        String t2 = "ahbgdc";
//        boolean ret2 = isSubsequence(s2, t2);
//        System.out.println(ret2);
//        String s3 = "";
//        String t3 = "ahbgdc";
//        boolean ret3 = isSubsequence(s3, t3);
//        System.out.println(ret3);
        String s4 = "acg";
        String t4 = "ahbgdc";
        boolean ret4 = isSubsequenceAmazing(s4, t4);
        System.out.println(ret4);
    }

    private static boolean isSubsequenceAmazing(String s, String t) {
        int index = -1;
        for (char c: s.toCharArray()){
            index = t.indexOf(c, index + 1);
            if (index == -1){
                return false;
            }
        }
        return true;
    }

    private static boolean isSubsequence(String s, String t) {
        if (s.length() == 0 && t.length() != 0){
            return true;
        }
        int i = 0, j = 0;
        while (i < s.length() && j < t.length()){
            if (s.charAt(i) == t.charAt(j)){
                i++;
            }
            j++;
        }
        return i >= s.length();
    }

    /**
     * 605. Can Place Flowers
     */
    private static void p605() {
//        int[] flowerbed1 = {1, 0, 1, 0, 1};
//        int n1 = 1;
//        boolean ret1 = canPlaceFlowers(flowerbed1, n1);
//        System.out.println(ret1);
//        int[] flowerbed2 = {0, 0, 1, 0, 1};
//        int n2 = 1;
//        boolean ret2 = canPlaceFlowers(flowerbed2, n2);
//        System.out.println(ret2);
//        int[] flowerbed3 = {0};
//        int n3 = 1;
//        boolean ret3 = canPlaceFlowers(flowerbed3, n3);
//        System.out.println(ret3);
        int[] flowerbed4 = {0, 0, 1, 0, 0, 0, 0, 1};
        int n4 = 3;
        boolean ret4 = canPlaceFlowers(flowerbed4, n4);
        System.out.println(ret4);
    }

    private static boolean canPlaceFlowers(int[] flowerbed, int n) {
        int len = flowerbed.length;
        int count = 0;
        for (int i = 0; i < len; i++) {
            if (flowerbed[i] == 1) {
                continue;
            }
            int pre  = i == 0 ? 0 : flowerbed[i - 1];
            int next = i == len - 1 ? 0 : flowerbed[i + 1];
            if (pre == 0 && next == 0) {
                count++;
                flowerbed[i] = 1;
            }
        }
        return count >= n;
    }

    private static boolean canPlaceFlowersOldVersion(int[] flowerbed, int n) {

        int len = flowerbed.length;
        if (len == 1 && flowerbed[0] == 0){
            return true;
        }
        int gap = 0;
        for (int i = 1; i < len; i++){
            if (flowerbed[i] == 0 && flowerbed[i - 1] == 0){
                gap++;
                if (i == 1 || i == len - 1){
                    gap++;
                }
            }
        }

        return gap / 2 >= n;
    }

    /**
     * 122. Best Time to Buy and Sell Stock II
     */
    private static void p122() {
        int[] prices = {7, 1, 5, 3, 6, 4};
        int ret1 = maxProfit122(prices);
        System.out.println(ret1);
    }

    private static int maxProfit122(int[] prices) {

        int max = 0;
        for (int i = 1; i < prices.length; i++){
            int diff = prices[i] - prices[i - 1];
            if (diff > 0){
                max += diff;
            }
        }
        return max;
    }

    /**
     * 121. Best Time to Buy and Sell Stock
     */
    private static void p121() {
        int[] prices = {7, 1, 5, 3, 6, 4};
        int ret1 = maxProfit(prices);
        System.out.println(ret1);
    }

    private static int maxProfit(int[] prices) {
        int max = 0, maxSoFar = 0;
        for (int i = 1; i < prices.length; i++){
            max = Math.max(0, max += (prices[i] - prices[i - 1]));
            maxSoFar = Math.max(maxSoFar, max);
        }
        return maxSoFar;
    }

    /**
     * 452. Minimum Number of Arrows to Burst Balloons
     */
    private static void p452() {
        int[][] points = {{2, 3}, {5, 8}, {7, 11}, {10, 13}, {15, 18}};
        int ret1 = findMinArrowShots(points);
        System.out.println(ret1);
    }

    private static int findMinArrowShots(int[][] points) {
        if (points == null || points.length == 0){
            return 0;
        }

        Arrays.sort(points, Comparator.comparing(x -> x[1]));
        int nonOverlapCount = 1;
        int end = points[0][1];

        for (int i = 1; i < points.length; i++){
            if (points[i][0] <= end){
                continue;
            }
            end = points[i][1];
            nonOverlapCount++;
        }
        return nonOverlapCount;
    }

    private static void comparing() {
        int[][] intervals = {{2, 3}, {1, 2}};
        Arrays.sort(intervals, Comparator.comparing(x -> x[1]));
        for (int i = 0; i < intervals.length; i++){
            System.out.println(Arrays.toString(intervals[i]));
        }
    }

    /**
     * 435. Non-overlapping Intervals
     */
    private static void p435() {
//        int[][] intervals1 = {{1, 2}, {2, 3}, {3, 4}, {1, 3}};
//        int ret1 = eraseOverlapIntervals(intervals1);
//        System.out.println(ret1);
        int[][] intervals2 = {{1, 10}, {11, 20}};
        int ret2 = eraseOverlapIntervals(intervals2);
        System.out.println(ret2);
    }

    private static int eraseOverlapIntervals(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        Arrays.sort(intervals, Comparator.comparing(x -> x[1]));
        int len = intervals.length;
        int end = intervals[0][1];
        int nonOverlapCount = 1;
        for (int i = 1; i < len; i++){
            if (intervals[i][0] < end){
                continue;
            }
            nonOverlapCount++;
            end = intervals[i][1];
        }
        return len - nonOverlapCount;
    }

    /**
     * 455. Assign Cookies
     */
    private static void p455() {
        int[] children1 = {1, 2};
        int[] cookies1  = {1, 1};
        int ret1 = findContentChildren(children1, cookies1); // 1
        System.out.println(ret1);
        int[] children2 = {};
        int[] cookies2  = {10};
        int ret2 = findContentChildren(children2, cookies2); // 0
        System.out.println(ret2);
        int[] children3 = {1, 2};
        int[] cookies3  = {};
        int ret3 = findContentChildren(children3, cookies3); // 0
        System.out.println(ret3);
        int[] children4 = {5};
        int[] cookies4  = {1};
        int ret4 = findContentChildren(children4, cookies4); // 0
        System.out.println(ret4);
        int[] children5 = {4, 5};
        int[] cookies5  = {1, 2};
        int ret5 = findContentChildren(children5, cookies5); // 0
        System.out.println(ret5);
        int[] children6 = {1};
        int[] cookies6  = {2};
        int ret6 = findContentChildren(children6, cookies6); // 1
        System.out.println(ret6);
        int[] children7 = {1, 2};
        int[] cookies7  = {2, 3};
        int ret7 = findContentChildren(children7, cookies7); // 2
        System.out.println(ret7);
        int[] children8 = {1, 2};
        int[] cookies8  = {1, 2, 3};
        int ret8 = findContentChildren(children8, cookies8); // 2
        System.out.println(ret8);
        int[] children9 = {1, 2, 3};
        int[] cookies9  = {1, 2};
        int ret9 = findContentChildren(children9, cookies9); // 2
        System.out.println(ret9);
    }

    private static int findContentChildren(int[] g, int[] s) {
        if (g == null || s == null || g.length == 0 || s.length == 0) return 0;
        Arrays.sort(g);
        Arrays.sort(s);
        int child = 0, cookie = 0;

        while (child < g.length && cookie < s.length){
            if (s[cookie] >= g[child]){
                child++;
            }
            cookie++;
        }
        return child;
    }
}
