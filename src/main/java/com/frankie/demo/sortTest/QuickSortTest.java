package com.frankie.demo.sortTest;

import com.frankie.demo.utils.Utils;

import java.util.Arrays;

/**
 * @author: Yao Frankie
 * @date: 2020/5/14 22:28
 */
public class QuickSortTest {

    public static void main(String[] args) {
//        int[] nums = {5, 3, 8, 4, 1, 7, 9};
//        quickSortUsingDoublePointer(nums);

        int[] nums = {4, 3, 5, 2, 4};
        quickSortUsingSinglePointer(nums);
        System.out.println(Arrays.toString(nums));
    }

    private static void quickSortUsingSinglePointer(int[] nums) {
        quickSortUsingSP(nums, 0, nums.length - 1);
    }

    private static void quickSortUsingSP(int[] nums, int lo, int hi) {
        if (lo >= hi) return;

        int pivotIndex = getPivotIndexUsingSP(nums, lo, hi);
        quickSortUsingSP(nums, lo, pivotIndex - 1);
        quickSortUsingSP(nums, pivotIndex + 1, hi);
    }

    private static int getPivotIndexUsingSP(int[] nums, int lo, int hi) {
        int pivot = nums[lo];
        int mark  = lo;

        for (int i = lo + 1; i <= hi; i++){
            if (nums[i] < pivot){
                mark++;
                Utils.swap(nums, i, mark);
            }
        }

        nums[lo]   = nums[mark];
        nums[mark] = pivot;

        return mark;
    }

    private static void quickSortUsingDoublePointer(int[] nums) {
        quickSortUsingDP(nums, 0, nums.length - 1);
    }

    private static void quickSortUsingDP(int[] nums, int lo, int hi) {
        if (lo >= hi){
            return;
        }

        int pivotIndex = getPivotIndexUsingDP(nums, lo, hi);
        quickSortUsingDP(nums, lo, pivotIndex - 1);
        quickSortUsingDP(nums, pivotIndex + 1, hi);
    }

    private static int getPivotIndexUsingDP(int[] nums, int lo, int hi) {
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
