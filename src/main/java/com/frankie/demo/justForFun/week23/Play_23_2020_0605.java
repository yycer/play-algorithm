package com.frankie.demo.justForFun.week23;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * @author: Yao Frankie
 * @date: 2020/6/1 9:40
 */
public class Play_23_2020_0605 {

    public static void main(String[] args) {
//        p64();  // 64. Minimum Path Sum
//        p62();  // 62. Unique Paths
//        p413(); // 413. Arithmetic Slices
//        p343(); // 343. Integer Break
//        P279(); // 279. Perfect Squares
//        p300(); // 300. Longest Increasing Subsequence
        p376(); // 376. Wiggle Subsequence
    }

    private static void p376() {
        int[] nums = {1, 17, 5, 10, 13, 15, 10, 5, 16, 8};
        int a = wiggleMaxLength(nums);
        System.out.println(a);
    }

    private static int wiggleMaxLength(int[] nums) {

        // Corner case.
        int len = nums.length;
        // Think about []
        if (len == 0) return 0;

        // Initialize rise and drop array.
        int[] rise = new int[len];
        int[] drop = new int[len];
        rise[0] = 1;
        drop[0] = 1;

        for (int i = 1; i < len; i++){
            int cur = nums[i];
            int pre = nums[i - 1];
            // rise
            if (cur > pre){
                rise[i] = drop[i - 1] + 1;
                drop[i] = drop[i - 1];
            }
            // drop
            else if (cur < pre){
                drop[i] = rise[i - 1] + 1;
                rise[i] = rise[i - 1];
            }
            // Two adjacent elements are equal.
            else {
                rise[i] = rise[i - 1];
                drop[i] = drop[i - 1];
            }
        }
        return Math.max(rise[len - 1], drop[len - 1]);
    }

    private static int wiggleMaxLengthUsingTwoVariable(int[] nums) {

        // Corner case.
        int len = nums.length;
        if (len == 0) return 0;

        int rise = 1, drop = 1;
        for (int i = 1; i < len; i++){
            int cur = nums[i];
            int pre = nums[i - 1];
            // rise
            if (cur > pre){
                rise = drop + 1;
            }
            // drop
            else if (cur < pre){
                drop = rise + 1;
            }
        }
        return Math.max(rise, drop);
    }

    private static void p300() {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        int a = lengthOfLIS(nums);
        System.out.println(a);
    }

    // 10, 9, 2, 5, 3, 7, 101, 18
    //  1  0  0
    private static int lengthOfLIS(int[] nums) {
        int len = nums.length;
        if (len == 0) return 0;
        int[] dp = new int[len];
        dp[0] = 1;

        for (int i = 1; i < len; i++){
            int cur = nums[i];
            // Be careful.
            int max = 1;
            for (int j = i - 1; j >= 0; j--){
                if (cur > nums[j]){
                    max = Math.max(max, dp[j] + 1);
                }
            }
            dp[i] = max;
        }
        // Think about [2, 4, 5, 3]
        return IntStream.of(dp).max().orElse(0);
    }

    private static int lengthOfLISOptimize(int[] nums) {
        // Corner case.
        int len = nums.length;
        if (len == 0) return 0;
        int realMax = 1;

        // Initialize dp.
        int[] dp = new int[len];
        dp[0] = 1;

        for (int i = 1; i < len; i++){
            int cur = nums[i];
            // Be careful.
            int max = 1;
            for (int j = i - 1; j >= 0; j--){
                if (cur > nums[j]){
                    max = Math.max(max, dp[j] + 1);
                }
            }
            dp[i]   = max;
            realMax = Math.max(realMax, max);
        }
        // Think about [2, 8, 9, 3]
        return realMax;
    }

    private static void P279() {
        int n = 12;
        int a = numSquares(n);
        System.out.println(a);
    }

    private static int numSquares(int n) {

        // Think about n = 9, we will use f(0) + 1 = 1.
        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++){
            int sqrt = (int) Math.sqrt(i);
            int min  = Integer.MAX_VALUE;
            for (int j = 1; j <= sqrt; j++){
                min = Math.min(min, dp[i - j * j] + 1);
            }
            dp[i] = min;
        }

        return dp[n];
    }

    private static void p343() {
//        int n = 10;
        int n = 5;
        int ret1 = integerBreak(n);
        System.out.println(ret1);
    }

    private static int integerBreak(int n) {

        if (n <= 1) return 0;
        if (n == 2) return 1;
        if (n == 3) return 2;
        if (n == 4) return 4;
        int[] dp = new int[n + 1];
        dp[2] = 1;
        dp[3] = 2;
        dp[4] = 4;

        for (int i = 5; i <= n; i++){
            // Why j >= i / 2? Think about n = 6.
            for (int j = i - 1; j >= i / 2; j--){
                // Why using max3? Think about n = 5.
                dp[i] = max3(dp[i], j * (i - j), dp[j] * (i - j));
            }
        }
        return dp[n];
    }

    private static int integerBreakOptimize(int n) {

        int[] dp = new int[n + 1];

        for (int i = 2; i <= n; i++){
            // Why j >= i / 2? Think about n = 6.
            for (int j = i - 1; j >= i / 2; j--){
                // Why using max3? Think about n = 5.
                dp[i] = max3(dp[i], j * (i - j), dp[j] * (i - j));
            }
        }
        return dp[n];
    }

    private static int max3(int a, int b, int c){
        return Math.max(a, Math.max(b, c));
    }

    private static void p413() {
        int[] A = {1, 2, 3, 4};
        int ret1 = numberOfArithmeticSlices(A);
        System.out.println(ret1);
    }

    private static int numberOfArithmeticSlices(int[] A) {
        int len = A.length;
        int[] dp = new int[len];
        for (int i = 2; i < len; i++){
            // formula
            if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]){
                dp[i] = dp[i - 1] + 1;
            }
        }
        return IntStream.of(dp).sum();
    }

    private static int numberOfArithmeticSlicesOptimize(int[] A) {

        int ans  = 0;
        int len  = A.length;
        int[] dp = new int[len];

        for (int i = 2; i < len; i++){
            // formula
            if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]){
                dp[i] = dp[i - 1] + 1;
                ans += dp[i];
            }
        }
        return ans;
    }

    private static void p62() {
        int m = 2;
        int n = 4;
//        int ret1 = uniquePaths(m, n);
        int ret1 = uniquePathsUsingOneDimension(m, n);
        System.out.println(ret1);
    }

    private static int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        dp[0][0] = 1;

        // Step1: Set row.
        for (int i = 1; i < n; i++){
            dp[0][i] = 1;
        }

        // Step2: Set column.
        for (int i = 1; i < m; i++){
            dp[i][0] = 1;
        }

        // Step3: Set others.
        for (int i = 1; i < m; i++){
            for (int j = 1; j < n; j++){
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    private static int uniquePathsUsingOneDimension(int m, int n) {
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 1; i < m; i++){
            for (int j = 1; j < n; j++){
                dp[j] += dp[j - 1];
            }
        }
        return dp[n - 1];
    }

    private static void p64() {
        int[][] grid = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };
        int ret1 = minPathSum(grid);
        System.out.println(ret1);
    }

    private static int minPathSum(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;

        // Step1: Set row.
        for (int i = 1; i < col; i++){
            grid[0][i] += grid[0][i - 1];
        }

        // Step2: Set column.
        for (int i = 1; i < row; i++){
            grid[i][0] += grid[i - 1][0];
        }

        // Step3: Set others.
        for (int i = 1; i < row; i++){
            for (int j = 1; j < col; j++){
                grid[i][j] += Math.min(grid[i - 1][j], grid[i][j - 1]);
            }
        }
        return grid[row - 1][col - 1];
    }

}











