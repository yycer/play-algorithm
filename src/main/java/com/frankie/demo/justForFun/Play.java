package com.frankie.demo.justForFun;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author: Yao Frankie
 * @date: 2020/5/26 9:44
 */
public class Play {

    public static void main(String[] args) {
//        p509();
//        p118();
//        p53();
//        p189(); // Rotate Array
//        p268(); // Missing Number
//        p70();  // 70. Climbing Stairs
//        p746(); // 746. Min Cost Climbing Stairs
//        p198(); // 198. House Robber
//        p213();  // 213. House Robber II
        p64(); // 64. Minimum Path Sum
    }

    private static void p64() {
        int[][] grid = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
//        int ret1 = minPathSumTwoDimensional(grid);
        int ret1 = minPathSumOneDimensional(grid);
        System.out.println(ret1);
    }

    private static int minPathSumOneDimensional(int[][] grid) {
        int row = grid.length, col = grid[0].length;
        if (row == 0 || col == 0) return 0;

        int[] dp = new int[col + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= row; i++){
            for (int j = 1; j <= col; j++){
                dp[j] = grid[i - 1][j - 1] + Math.min(dp[j], dp[j - 1]);
            }
            dp[0] = Integer.MAX_VALUE;
        }
        return dp[col];
    }

    private static int minPathSumTwoDimensional(int[][] grid) {
        int row = grid.length, col = grid[0].length;
        if (row == 0 || col == 0) return 0;

        // Step1: Set row.
        for (int i = 1; i < col; i++){
            grid[0][i] += grid[0][i - 1];
        }

        // Step2: Set col.
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

    private static void p213() {
        int[] nums = {2, 7, 9, 3, 1, 6, 8};
        int ret1 = rob_213_2020_0530(nums);
        System.out.println(ret1);
    }

    private static int rob_213_2020_0530(int[] nums) {

        int len = nums.length;
        if (len == 0) return 0;
        if (len == 1) return nums[0];
        return Math.max(robSub(nums, 0, len - 1), robSub(nums, 1, len));
    }

    private static int robSub(int[] nums, int lo, int hi) {

        int pre = 0;
        int cur = nums[lo];
        int ans = 0;

        for (int i = lo + 1; i < hi; i++){
            ans = Math.max(pre + nums[i], cur);
            pre = cur;
            cur = ans;
        }
        return ans;
    }

    private static void p198() {
        int[] nums = {2, 7, 9, 3, 1};
        int ret1 = rob_198_2020_0530(nums);
        System.out.println(ret1);
    }

    private static int rob_198_2020_0530(int[] nums) {
        int len  = nums.length;
        if (len == 0) return 0;
        int[] dp = new int[len + 1];
        dp[1] = nums[0];
        for (int i = 2; i <= len; i++){
            int cur= nums[i - 1];
            dp[i] = Math.max(cur + dp[i - 2], dp[i - 1]);
        }
        return dp[len];
    }

    private static void p746() {
//        int[] cost = {3, 2, 3, 1, 2, 4};
        int[] cost = {0, 0, 0, 1};
        int ret1 = minCostClimbingStairs(cost);
        System.out.println(ret1);
    }

    /**
     * Exception: [0, 0, 0, 1]
     *          0  0  0  1  0
     */
    private static int minCostClimbingStairs(int[] cost) {
        //    3, 2, 3, 1, 2, 4 [cost] -> len = 6
        // 0, 3, 2, 5, 3, 5, 7 [dp]

        int len  = cost.length;
        int[] dp = new int[len + 1];
        dp[1] = cost[0];

        for (int i = 2; i <= len; i++){
            dp[i] = cost[i - 1] + Math.min(dp[i - 1], dp[i - 2]);
        }
        return Math.min(dp[len], dp[len - 1]);
    }

    private static void p70() {
        int n = 6;
        int ret1 = climbStairs(n);
        System.out.println(ret1);
    }

    /**
     * --------------------------------------------------------------
     * dp[i]: How many distinct ways can you climb to the ith step?
     * if (i < 0 ) return 0;
     * dp[0] = 1;
     * dp[1] = 1;
     * dp[i] = dp[i-1] + dp[i-2], when i > 1
     * Because when you stand on the [i-1]th step,
     * you only need to take one more step to reach the top!
     * --------------------------------------------------------------
     * Example:
     * n : 0 1 2 3 4 5  6
     * dp: 1 1 2 3 5 8 13
     * --------------------------------------------------------------
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

    private static void p268() {
        int[] nums = {3, 0, 1};
//        int ret1 = missingNumber1(nums);
//        int ret1 = missingNumberUsingXOR(nums);
        int ret1 = missingNumberUsingBS(nums);
        System.out.println(ret1);
    }

    private static int missingNumberUsingBS(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        int lo  = 0, hi = len - 1;
        while (lo <= hi){
            int mid = (lo + hi) >>> 1;
            if (nums[mid] <= mid){
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return lo;
    }

    private static int missingNumberUsingXOR(int[] nums) {
        int len = nums.length;
        int ans = len;
        for (int i = 0; i < len; i++){
            ans ^= i;
            ans ^= nums[i];
        }
        return ans;
    }

    private static int missingNumber1(int[] nums) {
        int len = nums.length;
//        return len * (len + 1) / 2 - Arrays.stream(nums).sum();
        return ((len * (len + 1)) >> 1) - IntStream.of(nums).sum();
    }

    /**
     * 189. Rotate Array
     */
    private static void p189() {
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        int k = 2;
        rotateAmazing(nums, k);
        System.out.println(Arrays.toString(nums));
    }

    private static void rotateAmazing(int[] nums, int k) {
        int len = nums.length;
        k %= len;
        reverse(nums, 0, len - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, len - 1);
    }

    private static void reverse(int[] nums, int lo, int hi) {
        while (lo < hi){
            int tmp = nums[lo];
            nums[lo++] = nums[hi];
            nums[hi--] = tmp;
        }
    }

    /**
     * nums = [1, 2]
     * k = 3
     * 体现出rotate Array!
     */
    private static void rotateNotWork(int[] nums, int k) {
        // len = 7, k = 2
        // 1, 2, 3, 4, 5, 6, 7
        // 0  1  2  3  4  5  6 index
        // 6, 7, 1, 2, 3, 4, 5

        int len = nums.length;
        if (len < k) return;
        // Step1: Create an array1 of size k.
        int[] arr1 = new int[k];
        for (int i = 0; i < k; i++){
            arr1[i] = nums[len - k + i];
        }

        // Step2: Move the first (len-k) element to k step.
        for (int i = 0; i < len - k; i++){
            nums[len - 1 - i] = nums[len - k - i - 1];
        }

        // Step3: Insert element from array1 to nums.
        for (int i = 0; i < k; i++){
            nums[i] = arr1[i];
        }
    }

    private static void p53() {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
//        int ret1 = maxSubArray(nums);
        int ret1 = maxSubArray_2020_0529(nums);
        System.out.println(ret1);
    }

    private static int maxSubArray_2020_0529(int[] nums) {
        // [-2, 1, -3, 4, -1, 2, 1, -5, 4]
        //  -2  1  -2  4   3  5  6   1  5
        int len  = nums.length;
        int[] dp = new int[len];

        dp[0] = nums[0];
        for (int i = 1; i < len; i++){
            dp[i] = nums[i] + Math.max(dp[i - 1], 0);
        }
        return IntStream.of(dp).max().orElse(0);
    }

    private static int maxSubArray(int[] nums) {
        // -2, 1, -3, 4, -1, 2, 1, -5, 4
        // -2  1  -2  4   3  5  6  -1  3
        int len = nums.length;
        int[] dp = new int[len];
        dp[0] = nums[0];

        for (int i = 1; i < len; i++){
            // preSum cur ret
            //    1    1   +
            //    1   -1   +
            //   -1    1  cur
            //   -1   -1  cur
            int cur = nums[i];
            int preSum = dp[i - 1];
            if (preSum < 0){
                dp[i] = cur;
            } else {
                dp[i] = cur + dp[i - 1];
            }
        }

        return Arrays.stream(dp).max().orElse(0);
    }

    private static int maxSubArrayOptimize(int[] nums) {
        // -2, 1, -3, 4, -1, 2, 1, -5, 4
        // -2  1  -2  4   3  5  6  -1  3
        int len  = nums.length;
        int[] dp = new int[len];
        dp[0] = nums[0];

        for (int i = 1; i < len; i++){
            // preSum cur ret
            //    1    1   +
            //    1   -1   +
            //   -1    1  cur
            //   -1   -1  cur
            int cur = nums[i];
            int preSum = dp[i - 1];
            dp[i] = cur + ((preSum > 0) ? dp[i - 1] : 0);
        }

        return Arrays.stream(dp).max().orElse(0);
    }

    /**
     * 118. Pascal's Triangle
     */
    private static void p118() {
        int numRows = 5;
//        List<List<Integer>> ans = generate(numRows);
        List<List<Integer>> ans = generateOptimize1(numRows);
        for (List<Integer> child: ans){
            System.out.println(child);
        }
    }

    private static List<List<Integer>> generate(int numRows) {

        List<List<Integer>> ans = new ArrayList<>();
        if (numRows == 0) return ans;
        if (numRows == 1){
            ans.add(Collections.singletonList(1));
            return ans;
        }
        if (numRows == 2){
            ans.add(Arrays.asList(1));
            ans.add(Arrays.asList(1, 1));
            return ans;
        }
        ans.add(Arrays.asList(1));
        ans.add(Arrays.asList(1, 1));
        for (int i = 3; i <= numRows; i++){
            ArrayList<Integer> tmpList = new ArrayList<>();
            tmpList.add(1);
            for (int j = 1; j < i - 1; j++){
                tmpList.add(ans.get(i - 2).get(j - 1) + ans.get(i - 2).get(j));
            }
            tmpList.add(1);
            ans.add(tmpList);
        }
        return ans;
    }

    private static List<List<Integer>> generateOptimize1(int numRows) {

        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < numRows; i++){
            ArrayList<Integer> tmp = new ArrayList<>(Arrays.asList(1));
            // Think about the second layer: [1, 1]
            for (int j = 1; j < i; j++){
                // a[i][j] = a[i-1][j-1] + a[i-1][j]
                tmp.add(ans.get(i - 1).get(j - 1) + ans.get(i - 1).get(j));
            }
            // Think about the first layer: [1]
            if (i > 0) tmp.add(1);
            ans.add(tmp);
        }
        return ans;
    }

    private static void p509() {
        int N = 4;
        int ret1 = fib(N);
        System.out.println(ret1);
    }

    // 0, 1, 1, 2, 3
    // f(3) = f(1) + f(2) = 1 + 1 = 2
    // f(2) = f(0) + f(1) = 0 + 1 = 1
    public static int fib(int N) {
        if (N <= 0) return 0;
        int a = 0, b = 1;
        while (--N > 0){
            int t = b;
            b = a + b;
            a = t;
        }
        return b;
    }


}
