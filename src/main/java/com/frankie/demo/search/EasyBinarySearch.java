package com.frankie.demo.search;

/**
 * @author: Yao Frankie
 * @date: 2020/5/8 11:22
 */
public class EasyBinarySearch {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        for (int i: arr){
            boolean ret = containNumUsingBS(arr, i);
            System.out.println("arr contains " + i + ": " + ret);
        }
    }

    private static boolean containNumUsingBS(int[] arr, int num) {
        int start = 0, end = arr.length - 1;
        int count = 1;

        while (start <= end){
            int mid = start + ((end - start) >> 1);
            if (arr[mid] == num){
                System.out.println(count);
                return true;
            } else if (arr[mid] > num){
                end = mid - 1;
                count++;
            } else {
                start = mid + 1;
                count++;
            }
        }
        System.out.println("count = " + count);
        return false;
    }
}
