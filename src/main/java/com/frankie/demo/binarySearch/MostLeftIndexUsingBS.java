package com.frankie.demo.binarySearch;

/**
 * @author: Yao Frankie
 * @date: 2020/5/8 13:16
 */
public class MostLeftIndexUsingBS {

    public static void main(String[] args) {
        // 常规情况
        int[] arr1 = {1, 2, 3, 4}; // 1
        // 多个val
        int[] arr2 = {1, 2, 2, 2, 4}; // 1
        // 无val，往左查
        int[] arr3 = {1, 3, 4, 5}; // -1
        // 无val，往右查
        int[] arr4 = {1, 1, 1, 1}; // -1
        // 全val
        int[] arr5 = {2, 2, 2}; // 0
        // 仅一个最右val
        int[] arr6 = {1, 1, 1, 2}; // 3

        int ret1 = getMostLeftIndexOptimize(arr1, 2);
        System.out.println(ret1);
        int ret2 = getMostLeftIndexOptimize(arr2, 2);
        System.out.println(ret2);
        int ret3 = getMostLeftIndexOptimize(arr3, 2);
        System.out.println(ret3);
        int ret4 = getMostLeftIndexOptimize(arr4, 2);
        System.out.println(ret4);
        int ret5 = getMostLeftIndexOptimize(arr5, 2);
        System.out.println(ret5);
        int ret6 = getMostLeftIndexOptimize(arr6, 2);
        System.out.println(ret6);
    }

    private static int getMostLeftIndex(int[] arr, int num) {

        int start = 0, end = arr.length - 1;
        while (start <= end){
            int mid = start + ((end - start) >> 1);
            if (arr[mid] > num){
                end = mid - 1;
            } else if (arr[mid] < num) {
                start = mid + 1;
            } else {
                while (mid >= 0){
                    if (mid == 0 || arr[mid - 1] != arr[mid]) return mid;
                    mid--;
                }
            }
        }
        return -1;
    }


    private static int getMostLeftIndexOptimize(int[] arr, int num) {

        int start = 0, end = arr.length - 1;
        while (start <= end){
            int mid = start + ((end - start) >> 1);
            if (arr[mid] >= num){
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        if (start < arr.length && arr[start] == num){
            return start;
        }
        return -1;
    }
}
