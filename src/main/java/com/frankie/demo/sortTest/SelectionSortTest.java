package com.frankie.demo.sortTest;

import com.frankie.demo.utils.Utils;

import java.util.Arrays;

/**
 * @author: Yao Frankie
 * @date: 2020/5/6 13:43
 */
public class SelectionSortTest {

    public static void main(String[] args) {
        int[] arr = {5, 3, 5, 2, 4};
        selectionSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 从无序区域选择最小的元素，并将其放至左侧。
     */
    private static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++){
            int min = i;
            for (int j = i + 1; j < arr.length; j++){
                if (arr[j] < arr[min]){
                    min = j;
                }
            }
            if (min != i){
                Utils.swap(arr, i, min);
            }
        }
    }
}
