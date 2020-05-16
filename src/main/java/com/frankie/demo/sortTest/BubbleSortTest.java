package com.frankie.demo.sortTest;

import com.frankie.demo.utils.Utils;

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
//        int[] arr = {5, 3, 5, 2, 4};
//        int[] arr = {5, 8, 3, 9, 2, 3, 7};
//        int[] arr = {6, 1, 2, 3, 4, 5};
        int[] arr = {3, 2, 1, 4, 5, 6, 7, 8};
//        int[] arr = {2, 3, 4, 5, 6, 7, 8, 1};


//        bubbleSort(arr);
//        playBubble(arr);
        playBubbleJudgeSorted(arr);
        playBubbleUsingBorder(arr);
//        System.out.println(Arrays.toString(arr));

        playBubbleUsingCocktail(arr);
//        System.out.println(Arrays.toString(arr));
    }

    /**
     * 元素的比较与互换是双向的。
     */
    private static void playBubbleUsingCocktail(int[] arr) {
        int compareCount = 0;
        int len = arr.length;
        for (int i = 0; i < len / 2; i++){
            // 1. Left to right.
            boolean isSort = true;
            for (int j = i + 1; j <= len - i - 1; j++){
                compareCount++;
                if (arr[j] < arr[j - 1]){
                    Utils.swap(arr, j, j - 1);
                    isSort = false;
                }
            }
            if (isSort){
                break;
            }

            // 2. Right to left.
            isSort = true;
            for (int j = len - 1 - i - 1; j > i; j--){
                compareCount++;
                if (arr[j] < arr[j - 1]){
                    Utils.swap(arr, j, j - 1);
                    isSort = false;
                }
            }
            if (isSort){
                break;
            }
        }
        System.out.println(compareCount);
    }

    private static void playBubbleUsingBorder(int[] arr) {

        int len = arr.length;
        int compareCount      = 0;
        int lastExchangeIndex = 0;
        for (int border = len - 1; border > 0;){
           boolean isSorted = true;
           for (int j = 1; j <= border; j++){
               compareCount++;
               if (arr[j] < arr[j - 1]){
                   Utils.swap(arr, j, j - 1);
                   isSorted = false;
                   lastExchangeIndex = j - 1;
               }
           }
           border = lastExchangeIndex;
           if (isSorted){
               break;
           }
        }
        System.out.println(compareCount);
    }

    private static void playBubbleJudgeSorted(int[] arr) {
        int len = arr.length;
        int compareCount = 0;
        for (int i = len - 1; i > 0; i--){
            boolean isSorted = true;
            for (int j = 1; j <= i; j++){
                compareCount++;
                if (arr[j] < arr[j - 1]){
                    Utils.swap(arr, j, j - 1);
                    isSorted = false;
                }
            }
            if (isSorted){
                break;
            }
        }
        System.out.println(compareCount);
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

    /**
     * 2020_0516_0844
     * 将当前元素与前一个元素进行比较，若小于，则互换位置，否则，继续遍历下一个元素。
     */
    public static void playBubble(int[] arr){
        int compareCount = 0;
        int len = arr.length;
        for (int i = len - 1; i > 0; i--){
            for (int j = 1; j <= i; j++){
                compareCount++;
                if (arr[j] < arr[j - 1]){
                    Utils.swap(arr, j, j- 1);
                }
            }
        }
        System.out.println(compareCount);
    }
}
