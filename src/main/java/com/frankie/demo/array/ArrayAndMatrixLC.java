package com.frankie.demo.array;

import com.frankie.demo.utils.Utils;

import java.util.Arrays;

/**
 * @author: Yao Frankie
 * @date: 2020/5/15 15:06
 */
public class ArrayAndMatrixLC {

    public static void main(String[] args) {
//        p283(); // Move Zeroes
//        p485(); // Max Consecutive Ones
//        p645(); // Set Mismatch
//        p240(); // Search a 2D Matrix II
//        p287(); // Find the Duplicate Number
    }

    /**
     * 287. Find the Duplicate Number
     */
    private static void p287() {
        int[] nums = {1, 3, 4, 2, 2};
        int ans1 = findDuplicate(nums);
        System.out.println(ans1);
    }

    private static int findDuplicate(int[] nums) {
        if (nums.length == 0) return 0;
        int len = nums.length;
        int lo = 1, hi = len - 1;
        while (lo < hi){
            int lessThanCount = 0;
            int mid = (lo + hi) >>> 1;
            for (int n: nums){
                if (n <= mid) lessThanCount++;
            }
            if (lessThanCount > mid){
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }

    /**
     * Search a 2D Matrix II
     */
    private static void p240() {
        int[][] matrix = {{1, 4, 7}, {2, 5, 8}, {3, 6, 9}};
        int target = 7;
        boolean ans1 = searchMatrix(matrix, target);
        System.out.println(ans1);
    }

    private static boolean searchMatrix(int[][] matrix, int target) {
        // target = 23
//        [                <------.
//          [1,   4,  7, 11, 15], |
//          [2,   5,  8, 12, 19], |
//          [3,   6,  9, 16, 22], |
//          [10, 13, 14, 17, 24], ↓
//          [18, 21, 23, 26, 30]
//        ]
        if (matrix.length == 0) return false;
        int row = matrix.length;
        int col = matrix[0].length;

        int r = 0, c = col - 1;
        while (r < row && c >= 0){
            int cur = matrix[r][c];
            if (cur == target){
                return true;
            } else if (cur > target){
                c--;
            } else {
                r++;
            }
        }
        return false;
    }

    /**
     * 645. Set Mismatch
     */
    private static void p645() {
//        int[] nums1 = {4, 2, 2, 1};
//        int[] ret1 = findErrorNumsAmazing1(nums1);
//        System.out.println(Arrays.toString(ret1));
        int[] nums2 = {5, 2, 6, 1, 3, 3};
        int[] ret2 = findErrorNumsAmazing1(nums2);
        System.out.println(Arrays.toString(ret2));
    }

    private static int[] findErrorNumsAmazing1(int[] nums) {
        int dupIndex  = getDuplicateIndex(nums);
        int lostIndex = getLostIndex(nums);
        return new int[]{dupIndex, lostIndex};
    }

    private static int getLostIndex(int[] nums) {
        int lostIndex = -1;
        for (int i = 0; i < nums.length; i++){
            if (nums[i] > 0){
                lostIndex = i;
                break;
            }
        }
        return lostIndex + 1;
    }

    private static int getDuplicateIndex(int[] nums) {
        int dupIndex = -1;
        for (int i = 0; i < nums.length; i++){
            int jumpIndex = Math.abs(nums[i]);
            if (nums[jumpIndex - 1] < 0){
                dupIndex = jumpIndex;
            } else {
                nums[jumpIndex] = -nums[jumpIndex];
            }
        }
        return dupIndex;
    }

    private static int[] findErrorNums(int[] nums) {
        return new int[0];
    }

    /**
     * 485. Max Consecutive Ones
     */
    private static void p485() {
//        int[] nums1 = {1, 1, 0, 0, 1, 1, 1, 0};
//        int ret1 = findMaxConsecutiveOnes(nums1);
//        System.out.println(ret1);
        int[] nums2 = {1};
        int ret2 = findMaxConsecutiveOnes(nums2);
        System.out.println(ret2);
    }

    private static int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++){
            if (nums[i] == 0){
                count = 0;
            } else {
                max = Math.max(max, ++count);
            }
        }
        return max;
    }

    /**
     * 283. Move Zeroes
     */
    private static void p283() {
        int[] nums1 = {1, 0, 3, 0, 5};
        int[] nums2 = {0, 0};
        int[] nums3 = {1, 2};
        int[] nums5 = {1, 0, 0, 2, 3};

//        moveZeroesNotWork(nums1);
//        System.out.println(Arrays.toString(nums1));
//        moveZeroesNotWork(nums2);
//        System.out.println(Arrays.toString(nums2));
//        moveZeroesNotWork(nums3);
//        System.out.println(Arrays.toString(nums3));
//        moveZeros(nums4);
//        System.out.println(Arrays.toString(nums5));
//        moveZeros(nums5);
//        System.out.println(Arrays.toString(nums5));
//        moveZerosUsingInsertion(nums5);

//        moveZerosAmazing(nums5);
////        System.out.println(Arrays.toString(nums5));
        moveZeros20200526(nums1);
        System.out.println(Arrays.toString(nums1));
    }

    // {1, 0, 3, 0, 5}
    private static void moveZeros20200526(int[] nums) {
        int start = 0, len = nums.length;
        for (int i = 0; i < len; i++){
            if (nums[i] != 0){
                swap(nums, i, start++);
            }
        }
    }

    private static int findFirstZeroIndex(int[] nums, int lo){
        for (int i = lo; i < nums.length; i++){
            if (nums[i] == 0){
                return i;
            }
        }
        return -1;
    }

    private static void swap(int[] nums, int i, int j){
        int t   = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    /**
     * https://leetcode.com/problems/move-zeroes/discuss/72000/1ms-Java-solution
     */
    private static void moveZerosAmazing(int[] nums) {
        int lo = 0;
        for (int i = 0; i < nums.length; i++){
            if (nums[i] != 0){
                Utils.swap(nums, i, lo++);
            }
        }
    }

    /**
     * https://leetcode.com/problems/move-zeroes/discuss/72011/Simple-O(N)-Java-Solution-Using-Insert-Index
     */
    private static void moveZerosUsingInsertion(int[] nums) {
        int lo = 0;
        int[] ret = nums;
        for (int n: nums){
            if (n != 0){
                ret[lo++] = n;
            }
        }
        while (lo < nums.length){
            ret[lo++] = 0;
        }
    }

    private static void moveZeros(int[] nums) {
        if (nums == null || nums.length == 0) return;
        int len = nums.length;
        for (int i = 0; i < len; i++){
            if (nums[i] == 0){
                int j = i + 1;
                while (j < len && nums[j] == 0){
                    j++;
                }
                if (j < len){
                    Utils.swap(nums, i, j);
                }
            }
        }
    }

    /**
     *  maintaining the relative order of the non-zero elements.
     */
    private static void moveZeroesNotWork(int[] nums) {
        int lo = 0, hi = nums.length - 1;
        while (lo < hi){
            while (lo <= hi && nums[lo] != 0){
                lo++;
            }
            while (hi >= 0 && nums[hi] == 0){
                hi--;
            }
            if (lo < hi){
                Utils.swap(nums, lo, hi);
            }
        }
    }
}
