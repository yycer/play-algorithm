package com.frankie.demo.search;

/**
 * @author: Yao Frankie
 * @date: 2020/5/11 10:20
 */
public class MostRightIndexUsingBS {

    public static void main(String[] args) {
        // 常规情况
        int[] arr1 = {1, 2, 3, 4}; // 1
        // 多个val
        int[] arr2 = {1, 2, 2, 2, 4}; // 3
        // 无val，往左查
        int[] arr3 = {1, 3, 4, 5}; // -1
        // 无val，往右查
        int[] arr4 = {1, 1, 1, 1}; // -1
        // 全val
        int[] arr5 = {2, 2, 2}; // 2
        // 仅一个最右val
        int[] arr6 = {1, 1, 1, 2}; // 3

        int ret1 = getMostRightIndexOptimize(arr1, 2);
        System.out.println(ret1);
        int ret2 = getMostRightIndexOptimize(arr2, 2);
        System.out.println(ret2);
        int ret3 = getMostRightIndexOptimize(arr3, 2);
        System.out.println(ret3);
        int ret4 = getMostRightIndexOptimize(arr4, 2);
        System.out.println(ret4);
        int ret5 = getMostRightIndexOptimize(arr5, 2);
        System.out.println(ret5);
        int ret6 = getMostRightIndexOptimize(arr6, 2);
        System.out.println(ret6);
    }

    private static int getMostRightIndexOptimize(int[] arr, int num) {

        int start = 0, len = arr.length, end = len - 1;

        while (start <= end){
            int mid = start + ((end - start) >> 1);
            if (arr[mid] <= num){
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        if (end < len && arr[end] == num){
            return end;
        }

        return -1;
    }
}
