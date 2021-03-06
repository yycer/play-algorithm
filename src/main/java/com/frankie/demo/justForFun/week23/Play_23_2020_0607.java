package com.frankie.demo.justForFun.week23;

import java.util.Arrays;
import java.util.List;

/**
 * @author: Yao Frankie
 * @date: 2020/6/1 9:40
 */
public class Play_23_2020_0607 {

    public static void main(String[] args) {
//        p322(); // 322. Coin Change
//        p518(); // 518. Coin Change 2
//        p139(); // 139. Word Break
        p377(); // 377. Combination Sum IV
    }

    private static void p377() {
        int[] nums = {1, 2, 3};
        int target = 4;
        int a = combinationSum4(nums, target);
        System.out.println(a);
    }

    private static int combinationSum4(int[] nums, int target) {

        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int j = 1; j <= target; j++){
            for (int n: nums){
                if (j - n >= 0){
                    dp[j] += dp[j - n];
                }
            }
        }
        return dp[target];
    }

    private static void p139() {
        String s = "catsandog";
        List<String> wordDict = Arrays.asList("cats", "dog", "sand", "and", "cat");
        boolean a = wordBreak(s, wordDict);
        System.out.println(a);
    }

    private static boolean wordBreak(String s, List<String> wordDict) {
        int len = s.length();
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;

        for (int i = 1; i <= len; i++){
            for (int j = 0; j < i; j++){
                if (dp[j] && wordDict.contains(s.substring(j, i))){
                    dp[i] = true;
                }
            }
        }
        return dp[len];
    }

    private static void p518() {
        int[] coins = {1, 2, 5};
        int amount = 5;
        int a = change_518(amount, coins);
        System.out.println(a);
    }

    private static int change_518(int amount, int[] coins) {

        int len = coins.length;
        int[][] dp = new int[len + 1][amount + 1];
        dp[0][0] = 1;

        for (int i = 1; i <= len; i++){
            dp[i][0] = 1;
            for (int j = 1; j <= amount; j++){
                // Has rest.
                if (j - coins[i - 1] >= 0){
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i - 1]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[len][amount];
    }

    private static int change_518_using_one_dimension(int amount, int[] coins) {

        int[] dp = new int[amount + 1];
        dp[0] = 1;

        for (int c: coins){
            for (int j = 1; j <= amount; j++){
                if (j - c >= 0){
                    dp[j] += dp[j - c];
                }
            }
        }
        return dp[amount];
    }


    private static int change_518_using_one_dimension_optimize(int amount, int[] coins) {

        int[] dp = new int[amount + 1];
        dp[0] = 1;

        for (int c: coins){
            for (int j = c; j <= amount; j++){
                // Bag has rest.
                dp[j] += dp[j - c];
            }
        }
        return dp[amount];
    }

    private static void p322() {
//        int[] coins = {1, 2, 5};
        int[] coins = {2};
//        int amount = 6;
        int amount = 3;
//        int a = coinChange_322(coins, amount);
        int a = coinChange_322(coins, amount);
        System.out.println(a);
    }

    private static int coinChange_322_not_work(int[] coins, int amount) {

        int len = coins.length;
        int[][] dp = new int[len + 1][amount + 1];

        for (int i = 1; i <= len; i++){
            for (int j = 1; j <= amount; j++){
                // Bag has rest.
                if (j - coins[i - 1] >= 0){
                    dp[i][j] = dp[i][j - coins[i - 1]] + 1;
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[len][amount];
    }

    private static int coinChange_322(int[] coins, int amount) {

        int len = coins.length;
        int[][] dp = new int[len + 1][amount + 1];
        Arrays.fill(dp[0], Integer.MAX_VALUE);
        dp[0][0] = 0;

        for (int i = 1; i <= len; i++){
            int min;
            for (int j = 1; j <= amount; j++){
                int coin = coins[i - 1];
                int top  = dp[i - 1][j];
                int jump = dp[i][j - coin];
                // Bag has rest.
                if (j - coin >= 0){
                    if (top == -1 && jump == -1){
                        min = -1;
                    } else if (top == -1){
                        min = jump + 1;
                    } else if (jump == -1){
                        min = top;
                    } else {
                        min = Math.min(top, jump + 1);
                    }
                } else {
                    min = top;
                }
                min = (min == Integer.MAX_VALUE) ? -1 : min;
                dp[i][j] = min;
            }
        }
        return dp[len][amount];
    }

    private static int coinChange_322_optimize(int[] coins, int amount) {

        int len = coins.length;
        int[][] dp = new int[len + 1][amount + 1];
        Arrays.fill(dp[0], amount + 1);
        dp[0][0] = 0;

        for (int i = 1; i <= len; i++){
            for (int j = 1; j <= amount; j++){
                int coin = coins[i - 1];
                int top  = dp[i - 1][j];
                // Bag has rest.
                if (j - coin >= 0){
                    dp[i][j] = Math.min(top, dp[i][j - coin] + 1);
                } else {
                    dp[i][j] = top;
                }
            }
        }
        return dp[len][amount] > amount ? -1 : dp[len][amount];
    }

    private static int coinChange_322_usingOneDimensinal(int[] coins, int amount) {

        int len = coins.length;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        for (int i = 1; i <= len; i++){
            for (int j = 1; j <= amount; j++){
                int coin = coins[i - 1];
                // Bag has rest.
                if (j - coin >= 0){
                    dp[j] = Math.min(dp[j], dp[j - coin] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    private static int coinChange_322_usingOneDimensinal_optimize(int[] coins, int amount) {

        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        for (int c: coins){
            for (int j = c; j <= amount; j++){
                // Bag has rest.
                dp[j] = Math.min(dp[j], dp[j - c] + 1);
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}