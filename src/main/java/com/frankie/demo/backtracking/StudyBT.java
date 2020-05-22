package com.frankie.demo.backtracking;

/**
 * @author: Yao Frankie
 * @date: 2020/5/21 16:47
 */

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * https://labuladong.gitbook.io/algo/di-ling-zhang-bi-du-xi-lie/hui-su-suan-fa-xiang-jie-xiu-ding-ban
 */
public class StudyBT {

    private static List<List<Integer>> ret = new LinkedList<>();
    private final static int queenNum = 8;
    private static int queenPlacedCount = 0;

    public static void main(String[] args) {
//        permutations();
        putNQueen(queenNum);
    }

    /**
     * https://juejin.im/post/
     * https://www.cnblogs.com/newflydd/p/5091646.html
     * https://en.wikipedia.org/wiki/Eight_queens_puzzle
     * https://leetcode.wang/leetCode-51-N-Queens.html
     * https://labuladong.gitbook.io/algo/di-ling-zhang-bi-du-xi-lie/hui-su-suan-fa-xiang-jie-xiu-ding-ban
     * n = 8 , using 62
     * n = 14, using 36459
     */
    private static void putNQueen(int n) {
        short[][] board = new short[n][n];
        putQueen(board, 0);
    }


    private static void putQueen(short[][] board, int row) {

        if (row == board.length){
            // Find a solution.
            queenPlacedCount++;
            // Print the board.
            printBoard(board);
            return;
        }

        for (int col = 0; col < board.length; col++){
            // Check the current place is valid.
            if (!isValid(board, row, col)){
                continue;
            }
            // Make a decision.
            board[row][col] = 1;
            // Enter a next decision tree.
            putQueen(board, row + 1);
            // Undo the decision.
            board[row][col] = 0;
        }
    }

    private static void printBoard(short[][] board) {
        System.out.println(String.format("=============%s===============", queenPlacedCount));
        for (int i = 0; i < board.length; i++){
            System.out.println(Arrays.toString(board[i]));
        }
        System.out.println("=============================");
    }

    /**
     * 对于当前节点来说，只需要校验前半部分的列元素，左上角和右上角。
     */
    private static boolean isValid(short[][] board, int row, int col) {
        // Step1: Check the column element above.
        for (int i = 0; i < row; i++){
            if (board[i][col] == 1) return false;
        }

        // Step2: Check the element in the upper left corner.
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--){
            if (board[i][j] == 1) return false;
        }

        // Step3: Check the element in the upper right corner.
        for (int i = row - 1, j = col + 1; i >= 0 && j < board.length; i--, j++){
            if (board[i][j] == 1) return false;
        }

        return true;
    }

    private static void permutations() {
        int[] nums = {1, 2, 3};
        // Selected list.
        LinkedList<Integer> selectedList = new LinkedList<>();
        doFullArrange(nums, selectedList);
        for (List<Integer> l: ret){
            System.out.println(l);
        }
    }

    private static void doFullArrange(int[] nums, LinkedList<Integer> selectedList) {
        // Break condition.
        if (selectedList.size() == nums.length){
            ret.add(new LinkedList<>(selectedList));
            return;
        }

        for (int n: nums){
            // Add unique element into selected list.
            if (selectedList.contains(n)){
                continue;
            }
            selectedList.add(n);
            // Move to next level.
            doFullArrange(nums, selectedList);
            // Remove last element from selected list.
            selectedList.removeLast();
        }
    }
}
