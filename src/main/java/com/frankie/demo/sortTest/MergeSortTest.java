package com.frankie.demo.sortTest;

import java.util.Arrays;

/**
 * @author: Yao Frankie
 * @date: 2020/5/6 22:09
 */
public class MergeSortTest {

    public static void main(String[] args) {
//        int[] arr = {11, 8, 3, 9, 7, 1, 2, 5, 6};
        int[] arr = {4, 3, 5, 2, 6, 4};
        mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void mergeSort(int[] arr) {
        sep(arr, 0, arr.length - 1);
    }

    private static void sep(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }

        int mid = l + ((r - l) >> 1);
        sep(arr, l, mid);
        sep(arr, mid + 1, r);
        merge(arr, l, mid + 1, r);
    }

    private static void merge(int[] arr, int l, int mid, int r) {
        int[] left  = new int[mid - l];
        int[] right = new int[r - mid + 1];

        // Fill left and right.
        for (int i = l; i < mid; i++){
            left[i - l] = arr[i];
        }

        for (int j = mid; j <= r; j++){
            right[j - mid] = arr[j];
        }

        // Adjust arr using left and right.
        int i = 0, j = 0, k = l;
        while (i < left.length && j < right.length){
            if (left[i] < right[j]){
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }

        while (i < left.length){
            arr[k++] = left[i++];
        }

        while (j < right.length){
            arr[k++] = right[j++];
        }
    }
}
