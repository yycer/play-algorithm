package com.frankie.demo.justForFun;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * @author: Yao Frankie
 * @date: 2020/6/1 9:40
 */
public class Play_23_2020_0601 {

    public static void main(String[] args) {
//        p413();  // 413. Arithmetic Slices
//        p343();  // 343. Integer Break
//        p279();  // 279. Perfect Squares
//        p300();  // 300. Longest Increasing Subsequence
//        p376();  // 376. Wiggle Subsequence
//        p1143(); // 1143. Longest Common Subsequence
//        p416();  // 416. Partition Equal Subset Sum
    }

    private static void p416() {
//        int[] nums = {1, 5, 2, 4};
//        int[] nums = {1, 5, 2, 3};
        int[] nums = {1, 2, 5};
//        boolean ret1 = canPartition(nums);
        boolean ret1 = canPartitionUsingOneDimensional(nums);
        System.out.println(ret1);
    }

    private static boolean canPartitionUsingOneDimensional(int[] nums) {

        int sum = IntStream.of(nums).sum();
        if ((sum & 1) == 1) return false;
        int half = sum >> 1;

        int len = nums.length;
        boolean[] dp = new boolean[half + 1];
        dp[0] = true;

        // One-dimensional: <----------------
        for (int n: nums){
            for (int a = half; a >= n; a--){
                dp[a] |= dp[a - n];
                if (a == half && dp[a]){
                    return true;
                }
            }
        }
        return dp[half];
    }

    private static boolean canPartition(int[] nums) {

        int sum = IntStream.of(nums).sum();
        if ((sum & 1) == 1) return false;
        int half = (sum >> 1);

        int len = nums.length;
        boolean[][] dp = new boolean[len + 1][half + 1];
        dp[0][0] = true;
        for (int i = 1; i <= len; i++){
            dp[i][0] = true;
            for (int a = 1; a <= half; a++){
                // Bag has some rest.
                if (a - nums[i - 1] >= 0){
                    dp[i][a] = dp[i - 1][a] || dp[i - 1][a - nums[i - 1]];
                } else {
                    dp[i][a] = dp[i - 1][a];
                }
                // Return result as soon as possible.
                if (a == half && dp[i][a]){
                    return true;
                }
            }
        }
        return dp[len][half];
    }

    private static void p1143() {
        String text1 = "ace";
        String text2 = "acdef";
        int ret1 = longestCommonSubsequence(text1, text2);
        System.out.println(ret1);
    }

    private static int longestCommonSubsequence(String text1, String text2) {

        int len1 = text1.length();
        int len2 = text2.length();
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
     * Exception:
     * text1 = "bsbininm"
     * text2 = "jmjkbkjkv"
     * Output   = 2
     * Expected = 1
     * -------------------------------
     */
    private static int longestCommonSubsequenceNotWork(String text1, String text2) {

        int len1 = text1.length();
        int len2 = text2.length();
        if (len1 == 0 || len2 == 0) return 0;

        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 1; i <= len1; i++){
            for (int j = 1; j <= len2; j++){
                int inc = text1.charAt(i - 1) == text2.charAt(j - 1) ? 1 : 0;
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + inc;
            }
        }
        return dp[len1][len2];
    }

    /**
     * https://leetcode.com/problems/wiggle-subsequence/discuss/84843/Easy-understanding-DP-solution-with-O(n)-Java-version
     */
    private static void p376() {
//        int[] nums = {1, 17, 5, 10, 13, 15, 10, 5, 16, 8};
//        int[] nums = {0, 0, 0};
//        int[] nums = {0, 0};
//        int[] nums = {1, 2, 3, 4, 5};
        int[] nums = {3, 3, 3, 2, 5};
//        int ret1 = wiggleMaxLengthNotWork(nums);
//        int ret1 = wiggleMaxLengthUpDown(nums);
        int ret1 = wiggleMaxLengthUpDownOptimize(nums);
        System.out.println(ret1);
    }

    private static int wiggleMaxLengthUpDown(int[] nums) {

        int len = nums.length;
        // Think about []
        if (len == 0) return 0;
        int[] riseDp = new int[len];
        int[] dropDp = new int[len];

        riseDp[0] = 1;
        dropDp[0] = 1;

        for (int i = 1; i < len; i++){
            int pre = nums[i - 1];
            int cur = nums[i];
            if (cur > pre){
                riseDp[i] = dropDp[i - 1] + 1;
                dropDp[i] = dropDp[i - 1];
            } else if (cur < pre){
                dropDp[i] = riseDp[i - 1] + 1;
                riseDp[i] = riseDp[i - 1];
            } else {
                riseDp[i] = riseDp[i - 1];
                dropDp[i] = dropDp[i - 1];
            }
        }
        return Math.max(riseDp[len - 1], dropDp[len - 1]);
    }

    private static int wiggleMaxLengthUpDownOptimize(int[] nums) {

        int len = nums.length;
        // Think about []
        if (len == 0) return 0;
        int up = 1, down = 1;

        for (int i = 1; i < len; i++){
            int cur = nums[i];
            int pre = nums[i - 1];
            if (cur > pre){
                up   = down + 1;
            } else if (cur < pre){
                down = up + 1;
            }
        }
        return Math.max(up, down);
    }

    /**
     * nums = [0, 0]          return 1;
     * nums = [0, 0, 0]       return 1;
     * nums = [1, 2, 3, 4, 5] return 2;
     * nums = [3, 3, 3, 2, 5] return 2;
     */
    private static int wiggleMaxLengthNotWork(int[] nums) {

        int len  = nums.length;
        if (len < 2) return len;
        int end  = 1;
        int[] dp = new int[len];
        dp[1]    = 2;

        for (int i = 2; i < len; i++){
            int cur = nums[i];
            if ((cur - nums[end]) * (nums[end] - nums[end - 1]) < 0){
                dp[i] = dp[end] + 1;
                end   = i;
            }
        }
        dp[1] = 1;
        long distinctCount = Arrays.stream(nums).distinct().count();
        return dp[len - 1] > 0 ? dp[len - 1] : distinctCount == 1 ? 1 : 2;
    }

    private static void p300() {
        int[] nums = {10,  9, 2, 5, 3, 7, 101, 18};
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
