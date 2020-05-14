package com.frankie.demo.sortTest;

import com.frankie.demo.utils.Utils;

import javax.rmi.CORBA.Util;
import java.util.Arrays;

/**
 * @author: Yao Frankie
 * @date: 2020/5/6 10:20
 */
public class BubbleSortTest {

    /**
     * 5, 3, 5, 2, 4
     * @param args
     */
    public static void main(String[] args) {
        int[] arr = {5, 3, 5, 2, 4};
        bubbleSort(arr);
//        bubbleSortOptimize1(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void bubbleSort(int[] arr) {
        int len = arr.length;
        // 外层遍历次数递减
        for (int i = len - 1; i > 0; i--){
            int j = 1;
            // 内层从第二个元素开始，依次和前一个元素进行比较，若小于，则互换两者位置。
            while (j <= i){
                if (arr[j] < arr[j - 1]){
                    Utils.swap(arr, j, j - 1);
                }
                j++;
            }
        }
    }

    private static void bubbleSortOptimize1(int[] arr) {
        for (int i = arr.length - 1; i > 0; i--){
            boolean flag = false;
            for (int j = 1; j <= i; j++){
                if (arr[j - 1] > arr[j]){
                    Utils.swap(arr, j - 1, j);
                    flag = true;
                }
            }
            if (!flag) break;
        }
    }
}
