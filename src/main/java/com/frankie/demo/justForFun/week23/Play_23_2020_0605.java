package com.frankie.demo.justForFun.week23;

/**
 * @author: Yao Frankie
 * @date: 2020/6/1 9:40
 */
public class Play_23_2020_0605 {

    public static void main(String[] args) {
        p64(); // 64. Minimum Path Sum
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











