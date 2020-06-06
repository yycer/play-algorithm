package com.frankie.demo.justForFun.week23;

import java.util.stream.IntStream;

/**
 * @author: Yao Frankie
 * @date: 2020/6/1 9:40
 */
public class Play_23_2020_0606 {

    public static void main(String[] args) {
        p494(); // 494. Target Sum
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