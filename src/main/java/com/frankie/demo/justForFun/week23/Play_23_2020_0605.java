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











