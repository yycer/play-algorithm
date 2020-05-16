package com.frankie.demo.sortTest;

import com.frankie.demo.utils.Utils;

import java.util.Arrays;

/**
 * @author: Yao Frankie
 * @date: 2020/5/6 11:30
 */
public class InsertionSortTest {

    public static void main(String[] args) {
        int[] arr = {5, 3, 5, 2, 4};
        insertionSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 从第二个元素开始，将当前元素插入有序区域。
     */
    private static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++){
            for (int j = i; j > 0; j--){
                if (arr[j] < arr[j - 1]){
                    Utils.swap(arr, j, j - 1);
                }
            }
        }
    }
}
