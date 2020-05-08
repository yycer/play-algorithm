package com.frankie.demo.search;

/**
 * @author: Yao Frankie
 * @date: 2020/5/8 13:16
 */
public class ContainRepeatBS {

    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 3, 4, 5};
        int[] arr2 = {1, 3, 4, 5};
        int[] arr3 = {2, 2, 2};
//        int ret = getIndexUsingBS(arr, 2);
//        int ret = getIndexUsingBS(arr2, 2);
        int ret = getIndexUsingBS(arr3, 2);
        System.out.println(ret);
    }

    private static int getIndexUsingBS(int[] arr, int num) {

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
}
