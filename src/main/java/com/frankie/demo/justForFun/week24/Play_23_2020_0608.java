package com.frankie.demo.justForFun.week24;

import java.util.Arrays;

/**
 * @author: Yao Frankie
 * @date: 2020/6/1 9:40
 */
public class Play_23_2020_0608 {

    public static void main(String[] args) {
//        p121(); // 121. Best Time to Buy and Sell Stock
//        p122(); // 122. Best Time to Buy and Sell Stock II
//        p123(); // 123. Best Time to Buy and Sell Stock III
//        p309(); // 309. Best Time to Buy and Sell Stock with Cooldown
//        p714(); // 714. Best Time to Buy and Sell Stock with Transaction Fee
//        p583(); // 583. Delete Operation for Two Strings
    }

    private static void p583() {
//        String word1 = "sea";
        String word1 = "";
//        String word2 = "eat";
        String word2 = "e";
        int a = minDistance(word1, word2);
        System.out.println(a);
    }

    private static int minDistanceNotWork(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        if (len1 == 0 && len2 == 0) return 0;
        if (len1 == 0) return len2;
        if (len2 == 0) return len1;

        int[][] dp = new int[len1 + 1][len2 + 1];
        Arrays.fill(dp[0], 1);
        dp[0][0] = 0;
        for (int i = 1; i <= len1; i++){
            dp[i][0] = 1;
            for (int j = 1; j <= len2; j++){
                char row = word1.charAt(i - 1);
                char col = word2.charAt(j - 1);
                if (row == col){
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + 1;
                }
            }
        }
        return dp[len1][len2];
    }

    private static int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        if (len1 == 0) return len2;
        if (len2 == 0) return len1;

        int[][] dp = new int[len1 + 1][len2 + 1];

        // Set first row.
        for (int i = 1; i <= len1; i++){
            dp[0][i] = i;
        }
        // Set first col.
        for (int i = 1; i <= len2; i++){
            dp[i][0] = i;
        }

        for (int i = 1; i <= len1; i++){
            for (int j = 1; j <= len2; j++){
                char row = word1.charAt(i - 1);
                char col = word2.charAt(j - 1);
                if (row == col){
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + 1;
                }
            }
        }
        return dp[len1][len2];
    }

    private static void p714() {
        int[] prices = {1, 3, 2, 8, 4, 9};
        int fee = 2;
        int a = maxProfit(prices, fee);
        System.out.println(a);
    }

    private static int maxProfit(int[] prices, int fee) {

        int len = prices.length;
        if (len == 0) return 0;
        int[] hold   = new int[len];
        int[] unhold = new int[len];
        hold[0] = -prices[0];

        for (int i = 1; i < len; i++){
            int cur = prices[i];
            hold[i]   = Math.max(hold[i - 1], unhold[i - 1] - cur);
            unhold[i] = Math.max(unhold[i - 1], hold[i - 1] + cur - fee);
        }

        return unhold[len - 1];
    }

    private static void p309() {
        int[] prices = {1, 2, 3, 0, 2};
        int a = maxProfit_309(prices);
        System.out.println(a);
    }

    private static int maxProfit_309(int[] prices) {
        int len = prices.length;
        if (len < 2) return 0;
        int[] hold   = new int[len];
        int[] unHold = new int[len];

        hold[0] = -prices[0];
        hold[1] = -Math.min(prices[0], prices[1]);
        unHold[1] = Math.max(0, hold[0] + prices[1]);

        for (int i = 2; i < len; i++){
            hold[i]   = Math.max(hold[i - 1], unHold[i - 2] - prices[i]);
            unHold[i] = Math.max(unHold[i - 1], hold[i - 1] + prices[i]);
        }

        return unHold[len - 1];
    }

    private static void p123() {
//        int[] prices = {3, 3, 5, 0, 0, 3, 1, 4};
        int[] prices = {1, 2, 3, 4, 5};
        int a = maxProfit_123(prices);
        System.out.println(a);
    }

    private static int maxProfit_123(int[] prices) {

        int b1 = Integer.MIN_VALUE, s1 = 0;
        int b2 = Integer.MIN_VALUE, s2 = 0;

        for (int i = 0; i < prices.length; i++){
            int cur = prices[i];
            b1 = Math.max(b1, -cur);
            s1 = Math.max(s1, b1 + cur);
            b2 = Math.max(b2, s1 - cur);
            s2 = Math.max(s2, b2 + cur);
        }
        return s2;
    }

    private static int maxProfit_123_not_work(int[] nums) {

        int first = 0, second = 0;
        int len = nums.length;

        for (int i = 1; i < len; i++){
            int diff = Math.max(0, nums[i] - nums[i - 1]);
            if (diff >= first){
                first   = diff;
                second = first;
            } else {
                second = Math.max(second, diff);
            }
        }
        return first + second;
    }

    private static void p122() {
        int[] prices = {7, 1, 5, 3, 6, 4};
        int a = maxProfit_122(prices);
        System.out.println(a);
    }

    private static int maxProfit_122(int[] prices) {
        int len = prices.length;
        int ans = 0;

        for (int i = 1; i < len; i++){
            ans += Math.max(0, prices[i] - prices[i - 1]);
        }
        return ans;
    }

    private static int maxProfit_122_not_work(int[] prices) {

        int len = prices.length;
        int[] hold   = new int[len];
        int[] unhold = new int[len];
        hold[0] = -prices[0];

        for (int i = 1; i < len; i++){
            int cur = prices[i];
            hold[i] = Math.max(-cur, hold[i - 1]);

            // 卖了就赚钱
            if (cur + hold[i - 1] > 0){
                unhold[i] = unhold[i - 1] + cur + hold[i - 1];
                hold[i]   = Integer.MIN_VALUE;
            } else {
                unhold[i] = unhold[i - 1];
            }
        }

        return unhold[len - 1];
    }

    private static void p121() {
        int[] prices = {7, 1, 5, 3, 6, 4};
//        int a = maxProfit_121(prices);
        int a = maxProfit_121_optimize(prices);
        System.out.println(a);
    }

    private static int maxProfit_121(int[] prices) {
        int max = 0;
        int len = prices.length;
        for (int i = 0; i < len; i++){
            for (int j = i + 1; j < len; j++){
                max = Math.max(max, prices[j] - prices[i]);
            }
        }
        return max;
    }

    private static int maxProfit_121_optimize(int[] prices) {
        int len = prices.length;
        if (len == 0) return 0;
        int preMin = prices[0];
        int maxPro = 0;

        for (int i = 1; i < len; i++){
            maxPro = Math.max(maxPro, prices[i] - preMin);
            preMin = Math.min(preMin, prices[i]);
        }

        return maxPro;
    }
}