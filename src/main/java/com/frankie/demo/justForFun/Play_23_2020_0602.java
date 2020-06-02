package com.frankie.demo.justForFun;

import java.util.Arrays;

/**
 * @author: Yao Frankie
 * @date: 2020/6/1 9:40
 */
public class Play_23_2020_0602 {

    public static void main(String[] args) {
//        p474(); // 474. Ones and Zeroes todo: waiting
//        p322(); // 322. Coin Change
        p518(); // 518. Coin Change 2
    }

    private static void p518() {
        int[] coins = {1, 2, 5};
        int amount = 5;
        int ret1 = change(amount, coins);
        System.out.println(ret1);
    }

    private static int change(int amount, int[] coins) {
        int len = coins.length;
        int[][] dp = new int[len + 1][amount + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= len; i++){
            dp[i][0] = 1;
            for (int a = 1; a <= amount; a++){
                int rest = a - coins[i - 1];
                if (rest >= 0){
                    dp[i][a] = dp[i - 1][a] + dp[i][rest];
                } else {
                    dp[i][a] = dp[i - 1][a];
                }
            }
        }
        return dp[len][amount];
    }

    private static void p322() {
//        int[] coins = {1, 2, 5};
//        int amount = 6;
//        int[] coins = {2};
//        int amount = 3;
        int[] coins = {2, 5, 10, 1};
        int amount = 27;
        int ret1 = coinChange(coins, amount);
        System.out.println(ret1);
    }

    /**
     * https://leetcode.com/problems/coin-change/discuss/77360/C%2B%2B-O(n*amount)-time-O(amount)-space-DP-solution
     */
    private static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        // If use Integer.MAX_VALUE, it will cause an overflow.
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int c: coins){
            for (int a = c; a <= amount; a++){
                dp[a] = Math.min(dp[a], dp[a - c] + 1);
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    /**
     * 使用二维数组，边界条件极其复杂！
     * coins  = [2,5,10,1]
     * amount = 27
     * Expected = 4
     */
    private static int coinChangeNotWork(int[] coins, int amount) {

        int len = coins.length;
        int[][] dp = new int[len + 1][amount + 1];
        Arrays.fill(dp[0], Integer.MAX_VALUE);

        for (int i = 1; i <= len; i++){
            for (int a = 1; a <= amount; a++){
                // If has some rest.
                if (a - coins[i - 1] >= 0){
                    // Think about: coins = [2], amount = 3.
                    if (dp[i][a - coins[i - 1]] == -1){
                        dp[i][a] = -1;
                        continue;
                    }
                    dp[i][a] = Math.min(dp[i - 1][a], dp[i][a - coins[i - 1]] + 1);
                } else {
                    // Think about: coins = [2], amount = 3.
                    if (dp[i - 1][a] == Integer.MAX_VALUE){
                        dp[i][a] = -1;
                        continue;
                    }
                    dp[i][a] = dp[i - 1][a];
                }
            }
        }

        return dp[len][amount];
    }

    private static void p474() {

    }
}
