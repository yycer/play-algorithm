package com.frankie.demo.justForFun.week23;

import java.util.stream.IntStream;

/**
 * @author: Yao Frankie
 * @date: 2020/6/1 9:40
 */
public class Play_23_2020_0606 {

    public static void main(String[] args) {
//        p494(); // 494. Target Sum
//        p516(); // 516. Longest Palindromic Subsequence
//        p474(); // 474. Ones and Zeroes
    }

    private static void p474() {
        String[] strs = {"10", "0001", "111001", "1", "0"};
        int m = 5;
        int n = 3;
        int a = findMaxForm(strs, m, n);
        System.out.println(a);
    }

    private static int findMaxForm(String[] strs, int m, int n) {

        int[][] dp = new int[m + 1][n + 1];

        for (String s: strs){
            // Step1: count zero and one.
            int[] arr = countZeroAndOne(s);

            for (int i = m; i >= arr[0]; i--){
                for (int j = n; j >= arr[1]; j--){
                    dp[i][j] = Math.max(dp[i][j], dp[i - arr[0]][j - arr[1]] + 1);
                }
            }
        }
        return dp[m][n];
    }

    private static int[] countZeroAndOne(String s) {
        int zero = 0;
        int one  = 0;
        for (char c: s.toCharArray()){
            if (c == '0'){
                zero++;
            } else if (c == '1'){
                one++;
            }
        }
        return new int[]{zero, one};
    }

    private static void p516() {
//        String s = "xbcacby";
        String s = "bbbab";
        int a = longestPalindromeSubseq(s);
        System.out.println(a);
    }

    private static int longestPalindromeSubseq(String s) {
        int len = s.length();
        // Corner case
        if (len == 0) return 0;
        if (len == 1) return 1;
        int[][] dp = new int[len][len];

        /**
         * ------->
         *        ^
         *        |
         *        |
         */
        for (int i = len - 2; i >= 0; i--){
            dp[i][i] = 1;
            for (int j = i + 1; j < len; j++){
                char row = s.charAt(i);
                char col = s.charAt(j);
                if (row == col){
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[0][len - 1];
    }

    private static void p494() {
//        int[] nums = {1, 1, 1, 1, 1};
        int[] nums = {1000};
//        int S = 3;
        int S = 1000;
        int a = findTargetSumWays(nums, S);
        System.out.println(a);
    }

    /**
     * nums = [1, 2, 7, 9, 981]
     * S    = 1000000000
     */
    private static int findTargetSumWaysNotWork(int[] nums, int S) {

        int len = nums.length;
        if (len == 0) return 0;
        int sum = IntStream.of(nums).sum();
        int all = sum + S;
        // Think about nums=[1, 2, 3], S = 10000000
        // Memory Limit Exceeded
        if ((sum < S) || (all & 1) == 1) return 0;
        int half = (all >> 1);

        int[][] dp = new int[len + 1][half + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= len; i++){
            dp[i][0] = 1;
            // Be careful j = 0, think about nums=[0,0,1], Expected 4.
            for (int j = 0; j <= half; j++){
                // Bag has rest.
                if (j - nums[i - 1] >= 0){
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i - 1]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[len][half];
    }

    private static int findTargetSumWays(int[] nums, int S) {

        int len = nums.length;
        if (len == 0) return 0;
        int sum = IntStream.of(nums).sum();
        int all = sum + S;
        // Think about nums=[1, 2, 3], S = 10000000
        // Memory Limit Exceeded
        if ((sum < S) || (all & 1) == 1) return 0;
        int half = (all >> 1);

        int[] dp = new int[half + 1];
        dp[0] = 1;
        for (int n: nums){
            for (int j = half; j >= 0; j--){
                // Bag has rest.
                if (j - n >= 0){
                    dp[j] += dp[j - n];
                }
            }
        }
        return dp[half];
    }
}