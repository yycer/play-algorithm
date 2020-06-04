package com.frankie.demo.justForFun.week23;

/**
 * @author: Yao Frankie
 * @date: 2020/6/1 9:40
 */
public class Play_23_2020_0604 {

    public static void main(String[] args) {
//        p70();  // 70. Climbing Stairs
//        p746(); // 746. Min Cost Climbing Stairs
//        p198(); // 198. House Robber
//        p213(); // 213. House Robber II
        p509(); // 509. Fibonacci Number
    }

    private static void p509() {
        int N = 7;
        int ret1 = fib(N);
        System.out.println(ret1);
    }

    private static int fib(int N) {

        if (N == 0) return 0;
        int[] dp = new int[N + 1];
        dp[1] = 1;

        for (int i = 2; i <= N; i++){
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[N];
    }

    private static int fibUsingTwoVariable(int N) {

        if (N == 0) return 0;
        int pre = 0;
        int cur = 1;

        for (int i = 2; i <= N; i++){
            int tmp = cur;
            cur += pre;
            pre = tmp;
        }
        return cur;
    }

    private static void p213() {
        int[] nums = {1, 2, 3, 1};
        int ret1 = rob_213(nums);
        System.out.println(ret1);
    }

    private static int rob_213(int[] nums) {
        int len = nums.length;
        if (len == 0) return 0;
        if (len == 1) return nums[0];
        return Math.max(robCircle(nums, 0, len - 1), robCircle(nums, 1, len));
    }

    private static int robCircle(int[] nums, int lo, int hi) {
        int pre = 0;
        int max = nums[lo];

        for (int i = lo + 1; i < hi; i++){
            int tmp = max;
            max = Math.max(max, pre + nums[i]);
            pre = tmp;
        }
        return max;
    }

    private static void p198() {
        int[] nums = {1, 2, 3, 1};
        int ret1 = rob_198(nums);
        System.out.println(ret1);
    }

    private static int rob_198(int[] nums) {
        int len = nums.length;
        // Corner case.
        if (len == 0) return 0;
        if (len == 1) return nums[0];

        // Initialization dp.
        int[] dp = new int[len];
        dp[0] = nums[0];
        // Think about nums = [2, 1]
        dp[1] = Math.max(dp[0], nums[1]);

        for (int i = 2; i < len; i++){
            // formula.
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[len - 1];
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











