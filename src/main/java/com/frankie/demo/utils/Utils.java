package com.frankie.demo.utils;

/**
 * @author: Yao Frankie
 * @date: 2020/5/6 10:25
 */
public class Utils {

    public static void swap(int[] arr, int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
