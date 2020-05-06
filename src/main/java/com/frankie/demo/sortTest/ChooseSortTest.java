package com.frankie.demo.sortTest;

import com.frankie.demo.utils.Utils;

import java.util.Arrays;

/**
 * @author: Yao Frankie
 * @date: 2020/5/6 13:43
 */
public class ChooseSortTest {

    public static void main(String[] args) {
        int[] arr = {5, 3, 5, 2, 4};
        chooseSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void chooseSort(int[] arr) {
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
