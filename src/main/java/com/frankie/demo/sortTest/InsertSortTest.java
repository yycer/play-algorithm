package com.frankie.demo.sortTest;

import com.frankie.demo.utils.Utils;

import java.util.Arrays;

/**
 * @author: Yao Frankie
 * @date: 2020/5/6 11:30
 */
public class InsertSortTest {

    public static void main(String[] args) {
        int[] arr = {5, 3, 5, 2, 4};
        insertSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++){
            for (int j = i; j > 0; j--){
                if (arr[j] < arr[j - 1]){
                    Utils.swap(arr, j, j - 1);
                }
            }
        }
    }
}
