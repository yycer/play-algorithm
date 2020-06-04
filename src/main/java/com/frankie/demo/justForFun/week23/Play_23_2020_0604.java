package com.frankie.demo.justForFun.week23;

/**
 * @author: Yao Frankie
 * @date: 2020/6/1 9:40
 */
public class Play_23_2020_0604 {

    public static void main(String[] args) {
//        p70();  // 70. Climbing Stairs
//        p746(); // 746. Min Cost Climbing Stairs
    }

    private static void p746() {
        int[] cost = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        int ret1 = minCostClimbingStairs(cost);
        System.out.println(ret1);
    }

    private static int minCostClimbingStairs(int[] cost) {

        int len = cost.length;
        // Corner case.
        if (len == 0) return 0;
        if (len == 1) return cost[0];

        // Initialization.
        int[] dp = new int[len];
        dp[0] = cost[0];
        dp[1] = cost[1];

        for (int i = 2; i < len; i++){
            // Formula
            dp[i] = Math.min(dp[i - 1], dp[i - 2]) + cost[i];
        }
        return Math.min(dp[len - 1], dp[len - 2]);
    }

    private static void p70() {
        int n = 3;
        int ret1 = climbStairs(n);
        System.out.println(ret1);
    }

    private static int climbStairs(int n) {
        if (n <= 0) return 0;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++){
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }
}











