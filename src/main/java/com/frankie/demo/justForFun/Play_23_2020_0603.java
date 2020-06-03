package com.frankie.demo.justForFun;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author: Yao Frankie
 * @date: 2020/6/1 9:40
 */
public class Play_23_2020_0603 {

    public static void main(String[] args) {
//        p322(); // 322. Coin Change
//        p518(); // 518. Coin Change 2
//        p139(); // 139. Word Break todo: Not really understand
//        p377(); // 377. Combination Sum IV
//        p121(); // 121. Best Time to Buy and Sell Stock
    }

    private static void p121() {
        int[] prices = {7, 1, 5, 3, 6, 4};
//        int ret1 = maxProfit(prices);
//        int ret1 = maxProfitOptimize(prices);
        int ret1 = maxProfitUsingPreMin(prices);
        System.out.println(ret1);
    }

    private static int maxProfit(int[] prices) {
        int len = prices.length;
        int[] dp = new int[len];
        for (int i = 0; i < len - 1; i++){
            for (int j = i + 1; j < len; j++){
                dp[j] = Math.max(dp[j], prices[j] - prices[i]);
            }
        }
        return IntStream.of(dp).max().orElse(0);
    }

    private static int maxProfitOptimize(int[] prices) {
        int len = prices.length;
        int max = 0;
        for (int i = 0; i < len - 1; i++){
            for (int j = i + 1; j < len; j++){
                max = Math.max(max, prices[j] - prices[i]);
            }
        }
        return max;
    }

    private static int maxProfitUsingPreMin(int[] prices) {
        int len = prices.length;
        if (len == 0) return 0;
        int preMin = prices[0];
        int maxPro = 0;

        for (int i = 1; i < len; i++){
            int cur = prices[i];
            maxPro = Math.max(maxPro, cur - preMin);
            preMin = Math.min(preMin, cur);
        }
        return maxPro;
    }

    private static void p377() {
        int[] nums = {1, 2, 3};
        int target = 4;
        int ret1 = combinationSum4(nums, target);
        System.out.println(ret1);
    }

    private static int combinationSum4(int[] nums, int target) {

        int len = nums.length;
        int[][] dp = new int[len + 1][target + 1];
        dp[0][0] = 1;

        for (int i = 1; i <= len; i++){
            dp[i][0] = 1;
            for (int j = 1; j <= target; j++){
                // has rest
                if (j - nums[i - 1] >= 0){
                    dp[i][j] = dp[i - 1][j] + dp[i][j - nums[i - 1]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[len][target];
    }

    private static void p139() {
        String s = "catsandog";
        List<String> wordDict = Arrays.asList("cats", "dog", "sand", "and", "cat");
        boolean ret1 = wordBreak(s, wordDict);
        System.out.println(ret1);
    }

    private static boolean wordBreak(String s, List<String> wordDict) {

        int len = s.length();
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;

        for (int i = 1; i <= len; i++){
            for (int j = 0; j < i; j++){
                if (dp[j] && wordDict.contains(s.substring(j, i))){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[len];
    }

    private static void p518() {
//        int[] coins = {1, 2, 5};
//        int[] coins = {5};
        int[] coins = {2};
//        int amount = 5;
//        int amount = 5;
        int amount = 3;
//        int ret1 = change(coins, amount);
        int ret1 = changeOneDimension(coins, amount);
        System.out.println(ret1);
    }

    private static int changeOneDimension(int[] coins, int amount) {

        int[] dp = new int[amount + 1];
        dp[0] = 1;

        for (int c: coins){
            for (int i = 1; i <= amount; i++){
                // If bag has some rest.
                if (i - c >= 0){
                    dp[i] += dp[i - c];
                }
            }
        }
        return dp[amount];
    }

    private static int changeOneDimensionOptimize(int[] coins, int amount) {

        int[] dp = new int[amount + 1];
        dp[0] = 1;

        for (int c: coins){
            for (int i = c; i <= amount; i++){
                dp[i] += dp[i - c];
            }
        }
        return dp[amount];
    }

    private static int change(int[] coins, int amount) {

        int len = coins.length;
        int[][] dp = new int[len + 1][amount + 1];
        // Think about coins = [], amount = 0.
        dp[0][0] = 1;

        for (int i = 1; i <= len; i++){
            dp[i][0] = 1;
            for (int j = 1; j <= amount; j++){
                // Bag has some rest.
                if (j - coins[i - 1] >= 0){
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i - 1]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[len][amount];
    }

    private static void p322() {
//        int[] coins = {1, 2, 5};
        int[] coins = {2};
//        int amount = 6;
        int amount = 3;
//        int ret1 = coinChangeTwoDimension(coins, amount);
//        int ret1 = coinChangeOneDimension(coins, amount);
        int ret1 = coinChangeOneDimensionOptimize(coins, amount);
        System.out.println(ret1);
    }

    private static int coinChangeOneDimension(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        for (int c: coins){
            for (int i = 1; i <= amount; i++){
                // If bag has rest.
                if (i - c >= 0){
                    dp[i] = Math.min(dp[i], dp[i - c] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    private static int coinChangeOneDimensionOptimize(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        for (int c: coins){
            for (int i = c; i <= amount; i++){
                dp[i] = Math.min(dp[i], dp[i - c] + 1);
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    /**
     * Thanks myself!
     * Corner Case: coins = [2], amount= 3;
     */
    private static int coinChangeTwoDimension(int[] coins, int amount) {
        int len = coins.length;
        int[][] dp = new int[len + 1][amount + 1];
        // Amazing
        Arrays.fill(dp[0], amount + 1);

        for (int i = 1; i <= len; i++){
            for (int j = 1; j <= amount; j++){
                // Bag has rest.
                if (j - coins[i - 1] >= 0){
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - coins[i - 1]] + 1);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[len][amount] > amount ? -1 : dp[len][amount];
    }
}











