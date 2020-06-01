package com.frankie.demo.justForFun;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * @author: Yao Frankie
 * @date: 2020/6/1 9:40
 */
public class Play_23_2020_0601 {

    public static void main(String[] args) {
//        p413(); // 413. Arithmetic Slices
//        p343(); // 343. Integer Break
//        p279(); // 279. Perfect Squares
        p300(); // 300. Longest Increasing Subsequence
    }

    private static void p300() {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        int ret1 = lengthOfLIS(nums);
        System.out.println(ret1);
    }

    private static int lengthOfLIS(int[] nums) {
        int len  = nums.length;
        // Think about []
        if (len == 0) return 0;
        int[] dp = new int[len];
        dp[0] = 1;
        for (int i = 1; i < len; i++){
            int cur = nums[i];
            int end = i;
            // Think about the same element, eg: [4, 10, 4, 3, 8, 9]
            // int max = dp[i - 1];
            int max = 1;
            while (--end >= 0){
                if (cur > nums[end]){
                    max = Math.max(max, dp[end] + 1);
                }
            }
            dp[i] = max;
        }
        return IntStream.of(dp).max().orElse(0);
    }

    private static void p279() {
        int n = 12;
//        int ret1 = numSquares(n);
        int ret1 = numSquaresOptimize(n);
        System.out.println(ret1);
    }

    /**
     * 118 ms
     */
    private static int numSquares(int n) {

        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++){
            for (int j = 1; j <= (int) Math.sqrt(i); j++){
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }

    private static int numSquaresOptimize(int n) {

        int[] dp = new int[n + 1];
        dp[1] = 1;
        for (int i = 2; i <= n; i++){
            int min  = Integer.MAX_VALUE;
            int sqrt = (int) Math.sqrt(i);
            while (sqrt > 0){
                min = Math.min(min, dp[i - sqrt * sqrt] + 1);
                sqrt--;
            }
            dp[i] = min;
        }
        return dp[n];
    }


    private static void p343() {
        int n = 10;
        integerBreak(n);
    }

    private static int integerBreak(int n) {

        if (n == 1) return 0;
        if (n == 2) return 1;
        int[] dp = new int[n + 1];
        dp[2] = 1;
        dp[3] = 2;

        for (int i = 4; i <= n; i++){
            for (int j = 2; j <= (i >> 1); j++){
                // Think about n = 4, dp[2] = 1;
                // max3(0, 2 * dp[2], 2 * (4 - 2));
                dp[i] = max3(dp[i], j * dp[i - j], j * (i - j));
            }
        }
        return dp[n];
    }

    private static int integerBreakOptimize(int n) {

        int[] dp = new int[n + 1];
        dp[1] = 1;
        for (int i = 2; i <= n; i++){
            for (int j = 1; j <= (i >> 1); j++){
                // Think about n = 4, dp[2] = 1;
                // max3(0, 2 * dp[2], 2 * (4 - 2));
                dp[i] = max3(dp[i], j * dp[i - j], j * (i - j));
            }
        }

        return dp[n];
    }

    private static int max3(int a, int b, int c) {
        return Math.max(a, Math.max(b, c));
    }


    private static void p413() {
        int[] A = {1, 2, 3, 4};
//        int ret1 = numberOfArithmeticSlices(A);
        int ret1 = numberOfArithmeticSlicesOptimize(A);
        System.out.println(ret1);
    }

    private static int numberOfArithmeticSlicesOptimize(int[] A) {
        int ans  = 0;
        int len  = A.length;
        int[] dp = new int[len];
        for (int i = 2; i < len; i++){
            if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]){
                dp[i] = dp[i - 1] + 1;
                ans += dp[i];
            }
        }
        return ans;

    }

    private static int numberOfArithmeticSlices(int[] A) {

        int len  = A.length;
        // dp[i]: Arithmetic slices ending with [i]th element.
        int[] dp = new int[len];
        for (int i = 2; i < len; i++){
            if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]){
                dp[i] = dp[i - 1] + 1;
            }
        }
        return Arrays.stream(dp).sum();
    }
}
