package com.frankie.demo.justForFun;

import java.util.Arrays;

/**
 * @author: Yao Frankie
 * @date: 2020/6/1 9:40
 */
public class Play_23_2020_0603 {

    public static void main(String[] args) {
        p322(); // 322. Coin Change
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











