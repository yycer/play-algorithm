package com.frankie.demo.dp;

import java.util.Arrays;

/**
 * @author: Yao Frankie
 * @date: 2020/5/25 9:42
 */
public class DynamicProgrammingLC {

    public static void main(String[] args) {
//        p509(); // Fibonacci Number
//        p322(); // Coin Change
//        p300(); // Longest Increasing Subsequence
//        p53();  // Maximum Subarray
    }

    /**
     * 53. Maximum Subarray
     */
    private static void p53() {
        int[] nums1 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
//        int ret1 = maxSubArray(nums1);
        int ret1 = maxSubArrayOptimize(nums1);
        System.out.println(ret1);
    }

    private static int maxSubArrayOptimize(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int len  = nums.length;
        int[] dp = new int[len];
        dp[0] = nums[0];
        for (int i = 1; i < len; i++){
            int cur = nums[i];
            int dpBefore = dp[i - 1];
            dp[i] = Math.max(cur, cur + dpBefore);
//            dp[i] = Math.max(nums[i], nums[i] + dp[i - 1]);
        }
        return Arrays.stream(dp).max().orElse(0);
    }

    private static int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int len  = nums.length;
        int[] dp = new int[len];
        dp[0] = nums[0];
        for (int i = 1; i < len; i++){
            int cur = nums[i];
            int dpBefore = dp[i - 1];
            /**
             * cur    dpBefore(*)    ret
             * 1      -1             cur
             * 1       1             cur + dpBefore
             * -1      1             cur + dpBefore
             * -1     -1             cur
             */
            if (dpBefore > 0){
                dp[i] = cur + dpBefore;
            } else {
                dp[i] = cur;
            }
        }
        return Arrays.stream(dp).max().orElse(0);
    }

    /**
     * 300. Longest Increasing Subsequence
     * Binary Search: https://leetcode.com/problems/longest-increasing-subsequence/discuss/74824/JavaPython-Binary-search-O(nlogn)-time-with-explanation
     */
    private static void p300() {
//        int[] nums1 = {10, 9, 2, 5, 3, 7, 101, 18};
//        int ret1 = lengthOfLIS(nums1);
//        System.out.println(ret1);
        int[] nums2 = {1, 3, 6, 7, 9, 4, 10, 5, 6};
        int ret2 = lengthOfLIS(nums2);
        System.out.println(ret2);
    }

    /**
     * 数学归纳法：根据f(0 ... n-1)，求出f(n)。
     */
    private static int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0){
            return 0;
        }
        int len = nums.length;
        // dp[i]的含义：当前元素nums[i]的最长递增子序列。
        int[] dp = new int[len];

        for (int i = 0; i < len; i++){
            int max = 1;
            int cur = nums[i];
            int end = i;
            // 求之前所有小于当前元素的最大最长递增子序列，并加一。
            while (--end >= 0){
                if (nums[end] < cur) {
                    max = Math.max(max, dp[end] + 1);
                }
            }
            dp[i] = max;
        }
        return Arrays.stream(dp).max().orElse(0);
    }

    /**
     * 322. Coin Change
     * https://leetcode.com/problems/coin-change/discuss/77368/*Java*-Both-iterative-and-recursive-solutions-with-explanations
     */
    private static void p322() {
//        int[] coins1 = {1, 2, 5};
//        int amount1  = 39; // 10175ms
//        int ret1 = coinChange(coins1, amount1);
//        System.out.println(ret1);

//        int[] coins2 = {3};
//        int amount2  = 2;
//        int ret2 = coinChange(coins2, amount2);
//        System.out.println(ret2);

//        int[] coins3 = {1, 2, 5};
//        int amount3  = 4;
//        int[] memo3 = new int[amount3 + 1];
//        int ret3 = coinChangeUsingMemoWorks(coins3, amount3, memo3);
//        int ret3 = coinChangeUsingMemoTE(coins3, amount3, memo3);
//        int ret3 = coinChangeUsingDP(coins3, amount3);
//        System.out.println(ret3);

        int[] coins4 = {2};
        int amount4 = 2;
        int ret4 = coinChangeUsingDP(coins4, amount4);
        System.out.println(ret4);
    }

    private static int coinChangeUsingDP(int[] coins, int amount) {
        if (amount <= 0) return 0;
        int[] dp = new int[amount + 1];
        for (int a = 1; a <= amount; a++){
            int min = Integer.MAX_VALUE;
            for (int c: coins){
                int diff = a - c;
                // Be careful dp[diff] == -1, eg. coins = {2}, amount = 3
                if (diff < 0 || dp[diff] == -1){
                    continue;
                }
                int cur = dp[diff] + 1;
                min = Math.min(min, cur);
            }
            dp[a] = min == Integer.MAX_VALUE ? -1 : min;
        }
        return dp[amount];
    }


    private static int coinChangeTimeExceeded(int[] coins, int amount) {
        if (amount  < 0) return -1;
        if (amount == 0) return 0;

        int min = Integer.MAX_VALUE;
        for (int c: coins){
            int cur = coinChangeTimeExceeded(coins, amount - c);
            if (cur == -1) continue;
            min = Math.min(min, 1 + cur);
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    /**
     * Time Exceeded.
     */
    private static int coinChangeUsingMemoTE(int[] coins, int amount, int[] memo) {
        if (amount  < 0) return -1;
        if (amount == 0) return 0;

        if (memo[amount] != 0) return memo[amount];

        int min = Integer.MAX_VALUE;
        for (int c: coins){
            int cur = coinChangeUsingMemoTE(coins, amount - c, memo);
            if (cur == -1) continue;
            min = Math.min(min, 1 + cur);
            memo[amount] = min;
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }


    private static int coinChangeUsingMemoWorks(int[] coins, int amount, int[] memo) {
        if (amount  < 0) return -1;
        if (amount == 0) return 0;

        if (memo[amount] != 0) return memo[amount];

        int min = Integer.MAX_VALUE;
        for (int c: coins){
            int cur = coinChangeUsingMemoWorks(coins, amount - c, memo);
            if (cur == -1) continue;
            min = Math.min(min, 1 + cur);
        }
        memo[amount] = min == Integer.MAX_VALUE ? -1 : min;
        return memo[amount];
    }


    /**
     * 509. Fibonacci Number
     *
     * F(0) = 0,   F(1) = 1
     * F(N) = F(N - 1) + F(N - 2), for N > 1.
     *
     * F(2) = F(1) + F(0) = 1 + 0 = 1.
     */
    private static void p509() {
        int N = 4;
//        int ret1 = fibUsingCache(N);
        int ret1 = fibUsingIteration(N);
        System.out.println(ret1);
    }

    private static int fib(int N) {
        if (N == 0) return 0;
        if (N == 1) return 1;
        return fib(N - 1) + fib(N - 2);
    }

    private static int fibUsingCache(int N) {
        int[] cacheArr = new int[N];
        if (N == 0) return 0;
        if (N == 1) return 1;
        int sum = 0;
        if (cacheArr[N - 1] != 0){
            sum += cacheArr[N - 1];
        } else {
            int cur = fib(N - 1);
            sum += cur;
            cacheArr[N - 1] = cur;
        }

        if (cacheArr[N - 2] != 0){
            sum += cacheArr[N - 2];
        } else {
            int cur = fib(N - 2);
            sum += cur;
            cacheArr[N - 2] = cur;
        }
        return sum;
    }

    private static int fibUsingMemorandum(int N) {
        if (N < 0) return 0;
        int[] memo = new int[N + 1];
        return helper(memo, N);
    }

    private static int helper(int[] memo, int n) {
        if (n == 0 || n == 1) return n;
        if (memo[n] != 0) return memo[n];
        memo[n] = helper(memo, n - 1) + helper(memo, n - 2);
        return memo[n];
    }

    private static int fibUsingIteration(int N){
        if (N <= 0) return 0;
        int x0 = 0, x1 = 1;
        while (--N > 0){
            int tmp = x1;
            x1 = x0 + x1;
            x0 = tmp;
        }
        return x1;
    }
}
