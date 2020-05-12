package com.frankie.demo.greedy;

import java.util.Arrays;
import java.util.Comparator;

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
    }

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
