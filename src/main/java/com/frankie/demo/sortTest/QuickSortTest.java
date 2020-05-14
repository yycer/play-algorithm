package com.frankie.demo.sortTest;

import com.frankie.demo.utils.Utils;

import java.util.Arrays;

/**
 * @author: Yao Frankie
 * @date: 2020/5/14 22:28
 */
public class QuickSortTest {

    public static void main(String[] args) {
        int[] nums = {5, 3, 8, 4, 1, 7, 9};
        quickSortUsingDoublePointer(nums);
        System.out.println(Arrays.toString(nums));
    }

    private static void quickSortUsingDoublePointer(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }

    private static void quickSort(int[] nums, int lo, int hi) {
        if (lo >= hi){
            return;
        }

        int pivotIndex = getPivotIndex(nums, lo, hi);
        quickSort(nums, lo, pivotIndex - 1);
        quickSort(nums, pivotIndex + 1, hi);
    }

    private static int getPivotIndex(int[] nums, int lo, int hi) {
        int pivot = nums[lo];
        int start = lo;
        int end   = hi;

        while (start != end){
            while (start < end && nums[end] > pivot){
                end--;
            }
            while (start < end && nums[start] <= pivot){
                start++;
            }
            if (start < end){
                Utils.swap(nums, start, end);
            }
        }

        nums[lo]    = nums[start];
        nums[start] = pivot;

        return start;
    }
}
