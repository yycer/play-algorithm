package com.frankie.demo.dp;

import java.util.Arrays;

/**
 * @author: Yao Frankie
 * @date: 2020/5/25 9:42
 */
public class DynamicProgrammingLC {

    /**
     * https://www.cnblogs.com/miserweyte/p/11681350.html
     * 子串：  字符串x在字符串s中出现，所以子串是连续的。如："ab"是"dabc"的子串。
     * 子序列：字符串x可以由字符串s删除某些字符后得到。  如："ac"是"dabc"的子序列。
     */
    public static void main(String[] args) {
//        p509();  // Fibonacci Number
//        p322();  // Coin Change
//        p300();  // Longest Increasing Subsequence
//        p53();   // Maximum Subarray
//        p518();  // Coin Change 2
//        p416();  // Partition Equal Subset Sum
//        p1143(); // Longest Common Subsequence
//        p516();  // Longest Palindromic Subsequence
//        p70();   // Climbing Stairs
//        p746();  // Min Cost Climbing Stairs
//        p198();  // House Robber
//        p213();  // House Robber II
//        p64();   // Minimum Path Sum
//        p62();   // Unique Paths
//        p413();  // Arithmetic Slices
    }

    /**
     * 413. Arithmetic Slices
     */
    private static void p413() {
        int[] A = {1, 2, 3, 4};
//        int ret1 = numberOfArithmeticSlices(A);
        int ret1 = numberOfArithmeticSlicesOptimize1(A);
        System.out.println(ret1);
    }

    private static int numberOfArithmeticSlices(int[] A) {
        int len = A.length;
        if (len < 3) return 0;
        int[] dp = new int[len];

        for (int i = 2; i < len; i++){
            if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]){
                dp[i] = dp[i - 1] + 1;
            }
        }
        return Arrays.stream(dp).sum();
    }

    private static int numberOfArithmeticSlicesOptimize1(int[] A) {
        int len = A.length;
        if (len < 3) return 0;
        int[] dp = new int[len];
        int ans = 0;

        for (int i = 2; i < len; i++){
            if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]){
                // dp[i] represents: The number of arithmetic slices when ends of the A[i].
                // dp[i]表示以A[i]结尾的等差递增子区间的个数！！！
                dp[i] = dp[i - 1] + 1;
                ans += dp[i];
            }
        }
        return ans;
    }

    /**
     * 62. Unique Paths
     */
    private static void p62() {
        int m = 7;
        int n = 3;
        int ret1 = uniquePaths(m, n);
        System.out.println(ret1);
    }

    /**
     * Exception: m = 1, n = 1
     */
    private static int uniquePaths(int m, int n) {
        if (m < 1 || n < 1) return 0;
        int[][] grid = new int[m][n];
        // Step1: Set first row.
        for (int i = 0; i < n; i++){
            grid[0][i] = 1;
        }

        // Step2: Set first column.
        for (int i = 1; i < m; i++){
            grid[i][0] = 1;
        }

        // Step3: Set first others.
        for (int i = 1; i < m; i++){
            for (int j = 1; j < n; j++){
                grid[i][j] = grid[i - 1][j] + grid[i][j - 1];
            }
        }
        return grid[m - 1][n - 1];
    }

    /**
     * 64. Minimum Path Sum
     */
    private static void p64() {
        int[][] grid = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        int ret1 = minPathSum(grid);
        System.out.println(ret1);
    }

    private static int minPathSum(int[][] grid) {
        int rowNum = grid.length, colNum = grid[0].length;
        if (rowNum == 0 || colNum == 0) return 0;

        // Step1: Set first row.
        for (int i = 1; i < colNum; i++){
            grid[0][i] += grid[0][i - 1];
        }

        // Step2: Set first column.
        for (int i = 1; i < rowNum; i++){
            grid[i][0] += grid[i - 1][0];
        }

        // Step3: Set others.
        for (int i = 1; i < rowNum; i++){
            for (int j = 1; j < colNum; j++){
                grid[i][j] = Math.min(grid[i - 1][j], grid[i][j - 1]) + grid[i][j];
            }
        }
        return grid[rowNum - 1][colNum - 1];
    }

    /**
     * 213. House Robber II
     */
    private static void p213() {
//        int[] nums = {2, 3, 2};
        int[] nums = {100, 5, 3, 7, 6, 4};
//        int[] nums = {1, 2, 3, 1};
//        int ret1 = rob_213_notwork(nums);
        int ret1 = rob_213(nums);
        System.out.println(ret1);
    }

    /**
     * Split the nums to nums[0...len-1] and nums[1...len]
     * https://leetcode.com/problems/house-robber-ii/discuss/59934/Simple-AC-solution-in-Java-in-O(n)-with-explanation
     */
    private static int rob_213(int[] nums) {
        int len = nums.length;
        if (len == 0) return 0;
        if (len == 1) return nums[0];
        return Math.max(subRobLeft(nums, 0, len - 2), subRobRight(nums, 1, len - 1));
    }

    private static int subRobRight(int[] nums, int lo, int hi) {
        int len = hi - lo + 1;
        int[] dp = new int[len + 1];
        dp[0] = 0;
        dp[1] = nums[lo];

        for (int i = 2; i <= len; i++){
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[len];
    }

    /**
     * [100, 5, 3, 7, 6, 4]
     * sub1: [100, 5, 3, 7, 6]
     * sub2: [5, 3, 7, 6, 4]
     * ---------------------------------------------
     * sub1: [100, 5, 3, 7, 6]
     *         0   1  2  3  4
     * ---------------------------------------------
     * Exception: [1, 2, 3, 1]
     * len = 4
     * subRob(nums, 0, 2) | subRob(nums, 1, 3)
     * ---------------------------------------------
     * left:  subRob(nums, 0, 2)
     *    [1, 2, 3]: range[0, 2]
     * [0, 1, 2, 4]
     * ---------------------------------------------
     * Right: subRob(nums, 1, 3)
     *    [2, 3, 1]: range[1, 3]
     * [0, 2, 3, 3]
     */
    private static int subRobLeft(int[] nums, int lo, int hi) {
        int len = hi - lo + 1;
        int[] dp = new int[len + 1];
        dp[0] = 0;
        dp[1] = nums[lo];

        for (int i = 2; i <= len; i++){
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i - 1]);
        }
        return dp[len];
    }

    private static int rob_213_notwork(int[] nums) {
        //    [100,  5,   3,   7,   6,   4]
        //      0,   1,   2,   3,   4,   5
        // [0, 100, 100, 103, 107, 109, 109]
        int len = nums.length;
        int[] dp = new int[len + 1];
        dp[0] = 0;
        dp[1] = nums[0];
        for (int i = 2; i <= len; i++){
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return -1;
    }

    /**
     * 198. House Robber
     */
    private static void p198() {
        int[] nums = {2, 7, 9, 3, 1};
        int ret1 = rob_198(nums);
        System.out.println(ret1);
    }

    /**
     *    [2, 7, 9,  3,  1]
     * [0, 2, 7, 11, 11, 12]
     *
     * Exception: [2, 1, 1, 2]
     *         [0, 2, 2, 3, 4]
     */
    private static int rob_198(int[] nums) {
        int len = nums.length;
        if (len == 0) return 0;
        int[] dp = new int[len + 1];
        dp[0] = 0;
        dp[1] = nums[0];

        for (int i = 2; i <= len; i++){
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i - 1]);
        }
        return dp[len];
    }

    /**
     * Exception: [2, 1, 1, 2]
     *         [0, 2, 2, 3, 4]
     */
    private static int robNotWork(int[] nums) {
        int len = nums.length;
        if (len == 0) return 0;
        int[] dp = new int[len + 1];
        dp[0] = 0;
        dp[1] = nums[0];

        for (int i = 2; i <= len; i++) {
            dp[i] = dp[i - 2] + nums[i - 1];
        }
        return Arrays.stream(dp).max().orElse(0);
    }

    /**
     * 746. Min Cost Climbing Stairs
     */
    private static void p746() {
//        int[] cost = {10, 15, 20};
        int[] cost = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        int ret1 = minCostClimbingStairs(cost);
        System.out.println(ret1);
    }

    /**
     * Thanks for myself!!!
     */
    private static int minCostClimbingStairs(int[] cost) {
        int len = cost.length;
        if (len == 0) return 0;
        if (len == 1) return cost[0];
        int[] dp = new int[len];
        dp[0] = cost[0];
        dp[1] = cost[1];
        for (int i = 2; i < len; i++){
            dp[i] = Math.min(dp[i - 1], dp[i - 2]) + cost[i];
        }
        return Math.min(dp[len - 1], dp[len - 2]);
    }

    /**
     * 70. Climbing Stairs
     */
    private static void p70() {
        int n = 4;
        int ret1 = climbStairs(n);
        System.out.println(ret1);
    }

    /**
     * 给你一个n阶楼梯，允许你一次走1阶或2阶，请问登顶的所有唯一选择
     * f(n) = f(n - 1) + f(n - 2)
     * 因为，当你在第n-1和n-2阶时，分别走1和2阶楼梯，就一定可以登顶！
     * https://leetcode.com/problems/climbing-stairs/discuss/25299/Basically-it's-a-fibonacci.
     * ----------------------------------------
     * n = 4
     * -oo 0 1 2 3 4
     *   0 1 1 2 3 5
     * ----------------------------------------
     * n = 1 -> 1
     * n = 2 -> 1+1; 2
     * n = 3 -> 1+1+1; 1+2; 2+1
     * n = 4 -> 1+1+1+1; 1+1+2; 1+2+1; 2+1+1; 2+2
     */
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

    private static void p121() {
//        int[] prices = {7, 1, 5, 3, 6, 4};
//        int ret1 = maxProfit2(prices);
//        System.out.println(ret1);
//        int[] prices2 = {1, 2};
//        int ret2 = maxProfit2(prices2);
//        System.out.println(ret2);
//        int[] prices2 = {1, 4, 2};
//        int ret2 = maxProfit20200527(prices2);
//        System.out.println(ret2);
        int[] prices3 = {3, 2, 6, 5, 0, 3};
//        int ret3 = maxProfit20200527(prices3);
//        System.out.println(ret3);
    }

    private static int maxProfit(int[] prices) {
        // 7, 1, 5, 3, 6, 4
        // 0  1  2  3  4  5
        if (prices == null || prices.length == 0) return 0;
        int max = 0;
        int len = prices.length;
        for (int i = 0; i < len - 1; i++){
            int cur = prices[i];
            for (int j = i + 1; j < len; j++){
                max = Math.max(max, prices[j] - cur);
            }
        }
        return max;
    }

    /**
     * Border case: prices: [1, 2]
     * Border case: prices: [1, 4, 2]
     * Not work: [3,2,6,5,0,3]
     */
    private static int maxProfitNotWork(int[] prices) {
        // 7, 1, 5, 3, 6, 4
        // 0  1  2  3  4  5
        if (prices == null || prices.length == 0) return 0;
        int len = prices.length;
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        int lo = 0, hi = len - 1;
        while (lo <= hi){
            if (prices[lo] < min){
                min = prices[lo];
            }
            if (prices[hi] > max){
                max = prices[hi];
            }
            lo++;
            hi--;
        }
        return max > min ? max - min : 0;
    }

    /**
     * 516. Longest Palindromic Subsequence
     */
    private static void p516() {
        String s = "bbbab";
        longestPalindromeSubseq(s);
    }

    /**
     * 在子串s[i...j]中，最长回文子序列为 dp[i][j]。
     *
     */
    private static int longestPalindromeSubseq(String s) {
        return 1;
    }

    /**
     * 1143. Longest Common Subsequence
     */
    private static void p1143() {
        // longest common subsequence is ace.
        String text1 = "ab";
        String text2 = "cac";
        int ret1 = longestCommonSubsequence(text1, text2);
        System.out.println(ret1);
    }

    /**
     * k = dp[i][j]: 对于s1[1...i]和s2[1...j]，他们的LCS长度为k。
     * eg. text1 = "ab", text2 = "cac";
     *       c a c
     *     0 0 0 0
     *  a: 0 0 1 1
     *  b: 0 0 1 1
     *
     *  s1 = "bsbininm";
     *  s2 = "jmjkbkjkv";
     *      b s b i n i n m
     *    0 0 0 0 0 0 0 0 0
     *  j 0 0 0 0 0 0 0 0 0
     *  m 0 0 0 0 0 0 0 0 1
     *  j 0 0 0 0 0 0 0 0 1
     *  k 0 0 0 0 0 0 0 0 1
     *  b 0 1 0 1 0 0 0 0 1
     *  k 0 1 0 1 0 0 0 0 1
     *  j 0 0 0 0 0 0 0 0 0
     *  k 0 0 0 0 0 0 0 0 0
     *  v 0 0 0 0 0 0 0 0 0
     *
     */
    private static int longestCommonSubsequence(String text1, String text2) {
        int len1 = text1.length(), len2 = text2.length();
        if (len1 == 0 || len2 == 0) return 0;
        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int i = 1; i <= len1; i++){
            for (int j = 1; j <= len2; j++){
                if (text1.charAt(i - 1) == text2.charAt(j - 1)){
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[len1][len2];
    }

    /**
     * Not work.
     * eg. text1 = "ab", text2 = "cac";
     * Output  : 0
     * Expected: 1
     */
    private static int longestCommonSubsequenceNotWork(String text1, String text2) {
        int len1 = text1.length(), len2= text2.length();
        if (len1 == 0 || len2 == 0) return 0;
        int i = len1 - 1, j = len2 - 1;
        int ans = 0;
        while (i >= 0 || j >= 0){
            if (text1.charAt(i) == text2.charAt(j)){
                i--;
                j--;
                ans++;
            } else {
                i--;
            }
        }
        return ans;
    }

    /**
     * 416. Partition Equal Subset Sum
     */
    private static void p416() {
        int[] nums = {1, 2, 6, 3};
        boolean ret1 = canPartition(nums);
//        boolean ret1 = canPartitionOptimize1(nums);
        System.out.println(ret1);
    }

    /**
     * https://labuladong.gitbook.io/algo/dong-tai-gui-hua-xi-lie/bei-bao-zi-ji
     */
    private static boolean canPartition(int[] nums) {
        // ---------------------------------------------------------------------------
        // dp[i][j]: 对于容量为j的背包，若只是使用前i个物品，是否有一种方法将其装满。
        // If bag has rest:     dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
        // If bag has not rest: dp[i][j] = dp[i - 1][j]
        // ---------------------------------------------------------------------------
        // [1, 3, 2, 2], half = 4
        //       0 1 2 3 4
        //    0: 0 0 0 0 0
        // [1]1: 1 1 0 0 0
        // [3]2: 1 1 1 1 0
        // [2]3: 1 1 1 1 1
        // [2]4: 1 1 1 1 1

        int sum = Arrays.stream(nums).sum();
        // sum is odd.
        if ((sum & 1) == 1){
            return false;
        }
        int len  = nums.length;
        int half = sum / 2;
        boolean[][] dp = new boolean[len + 1][half + 1];
        for (int i = 1; i <= len; i++){
            dp[i][0] = true;
            for (int j = 1; j <= half; j++){
                // If bag has not rest.
                if (j - nums[i - 1] < 0){
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        return dp[len][half];
    }

    private static boolean canPartitionOptimize1(int[] nums) {
        // [1, 3, 2, 2], half = 4
        // <-----------
        //    0 1 2 3 4 (Amount)
        // 1: 1 1 0 0 0
        // 3: 1 1 0 1 1
        // 2: 1 1 1 1 1
        // 2: 1 1 1 1 1

        int sum = Arrays.stream(nums).sum();
        // sum is odd.
        if ((sum & 1) == 1){
            return false;
        }
        int half = sum / 2;
        boolean[] dp = new boolean[half + 1];
        dp[0] = true;
        for (int n: nums){
            // Big to small.
            for (int j = half; j >= n; j--){
                dp[j] |= dp[j - n];
            }
        }
        return dp[half];
    }

    /**
     * 518. Coin Change 2
     * https://labuladong.gitbook.io/algo/dong-tai-gui-hua-xi-lie/bei-bao-ling-qian
     * https://leetcode.com/problems/coin-change-2/discuss/99212/Knapsack-problem-Java-solution-with-thinking-process-O(nm)-Time-and-O(m)-Space
     * https://leetcode.com/problems/coin-change-2/discuss/99222/Video-explaining-how-dynamic-programming-works-with-the-Coin-Change-problem
     */
    private static void p518() {
        int amount = 4;
        int[] coins = {1, 2, 5};
//        int ret1 = change(amount, coins);
//        int ret1 = changeUsingDP(amount, coins);
        int ret1 = changeUsingDPOptimize(amount, coins);
        System.out.println(ret1);
    }

    private static int changeUsingDP(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int c: coins){
            for (int a = 1; a <= amount; a++){
                if (a - c >= 0){
                    dp[a] += dp[a - c];
                }
            }
        }
        return dp[amount];
    }

    private static int changeUsingDPOptimize(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int c: coins){
            for (int a = c; a <= amount; a++){
                dp[a] += dp[a - c];
            }
        }
        return dp[amount];
    }

    private static int change(int amount, int[] coins) {
        int coinSize = coins.length;
        int[][] dp = new int[coinSize + 1][amount + 1];
        for (int i = 0; i <= coinSize; i++){
            dp[i][0] = 1;
        }
        for (int c = 1; c <= coinSize; c++){
            for (int a = 1; a <= amount; a++){
                if (a - coins[c - 1] >= 0){
                    dp[c][a] = dp[c - 1][a] + dp[c][a - coins[c - 1]];
                } else {
                    dp[c][a] = dp[c - 1][a];
                }
            }
        }
        return dp[coinSize][amount];
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

        int[] coins3 = {1, 2, 5};
        int amount3  = 6;
//        int[] memo3 = new int[amount3 + 1];
//        int ret3 = coinChangeUsingMemoWorks(coins3, amount3, memo3);
//        int ret3 = coinChangeUsingMemoTE(coins3, amount3, memo3);
//        int ret3 = coinChangeUsingDP(coins3, amount3);
//        System.out.println(ret3);

//        int[] coins4 = {2};
//        int amount4 = 2;
//        int ret4 = coinChangeUsingDP(coins4, amount4);
//        System.out.println(ret4);

        int ret5 = coinChangeUsingDP20200527_1(coins3, amount3);
        System.out.println(ret5);
    }

    /**
     * coins: [1, 2, 5], amount = 6
     * 选择： 你做出什么行为，才能改变状态的值。
     * 1. 状态：目标金额
     * 2. dp函数定义：若当前目标金额为n,至少需要dp(n)个硬币凑出该金额。
     * 3. 选择：选择从面额列表中选一个硬币，导致目标金额减少。
     *
     * Border case:
     * coins: [3], amount = 2;
     * coins: [2], amount = 3;
     * dp: 0 -1  1  0
     *     0  1  2  3
     */
    private static int coinChangeUsingDP20200527_1(int[] coins, int amount) {

        int[] dp = new int[amount + 1];
        for (int a = 1; a <= amount; a++){
            int min = Integer.MAX_VALUE;
            for (int c: coins){
                int cap = a - c;
                // Border case: coins: [2], amount = 3;
                if (cap < 0 || dp[cap] == -1){
                    continue;
                }
                min = Math.min(min, dp[cap] + 1);
            }
            dp[a] = min == Integer.MAX_VALUE ? -1 : min;
        }
        return dp[amount];
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
