package com.frankie.demo.binarySearch;

import java.util.Arrays;

/**
 * @author: Yao Frankie
 * @date: 2020/5/11 10:48
 */
public class BinarySearchLeetcode {

    public static void main(String[] args) {
//        p69();
//        p744();
//        p540();
//        p278();
//        p153();
//        p34();
        p33();
    }

    /**
     * 33. Search in Rotated Sorted Array
     */
    private static void p33() {
        int[] nums = {5, 1, 3};
        int target = 3;
        searchNotWork(nums, target);
    }

    private static int searchNotWork(int[] nums, int target) {
        if (nums == null || nums.length == 0){
            return -1;
        }
        int ans = -1;
        if (nums[0] > target){
            ans = getIndexFromEnd(nums, target);
        } else {
            ans = getIndexFromStart(nums, target);
        }

        return ans;
    }

    private static int getIndexFromStart(int[] nums, int t) {
        int lo = 0, len = nums.length, hi = len - 1;
        while (lo <= hi){
            int mid = (lo + hi) >>> 1;
            if (nums[mid] == t){
                return mid;
            } else if (nums[mid] > t){
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return hi >= 0 && nums[hi] == t ? hi : -1;
    }

    private static int getIndexFromEnd(int[] nums, int t) {
        int lo = 0, len = nums.length, hi = len - 1;
        while (lo <= hi){
            int mid = (lo + hi) >>> 1;
            if (nums[mid] == t){
                return mid;
            } else if (nums[mid] > nums[hi]){
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return lo < len && nums[lo] == t ? lo : -1;
    }


    /**
     * Find First and Last Position of Element in Sorted Array
     */
    private static void p34() {
//        int[] nums1 = {2, 3, 4, 4, 5, 6, 7};
//        int target1 = 2;
//        int[] ret1 = searchRange(nums1, target1);
//        System.out.println(Arrays.toString(ret1));
        int[] nums2 = {1};
        int target2 = 0;
        int[] ret2 = searchRange(nums2, target2);
        System.out.println(Arrays.toString(ret2));
    }

    private static int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0){
            return new int[]{-1, -1};
        }
        int lo1 = 0, len = nums.length, hi1= len - 1;
        int lo2 = 0, hi2 = len - 1;

        // Left
        while (lo1 <= hi1){
            int mid = lo1 + ((hi1 - lo1) >> 1);
            if (nums[mid] < target){
                lo1 = mid + 1;
            } else {
                hi1 = mid - 1;
            }
        }
        if (lo1 >= len || nums[lo1] != target){
            lo1 = -1;
        }

        // Right
        while (lo2 <= hi2){
            int mid = lo2 + ((hi2 - lo2) >> 1);
            if (nums[mid] > target){
                hi2 = mid - 1;
            } else {
                lo2 = mid + 1;
            }
        }
        if (hi2 < 0 || hi2 >= len || nums[hi2] != target){
            hi2 = -1;
        }

        return new int[]{lo1, hi2};
    }

    /**
     * 153. Find Minimum in Rotated Sorted Array
     */
    private static void p153() {
        int[] nums1 = {5, 6, 7, 0, 1, 2, 3, 4};
        int[] nums2 = {3, 4, 5, 6, 7, 0, 1, 2};
        int[] nums3 = {1, 2, 3, 4, 0};
        int[] nums4 = {4, 5, 6, 7, 0, 1, 2};
        int ret1 = findMin(nums1);
        System.out.println(ret1);
        int ret2 = findMin(nums2);
        System.out.println(ret2);
        int ret3 = findMin(nums3);
        System.out.println(ret3);
        int ret4 = findMin(nums4);
        System.out.println(ret4);
    }

    private static int findMin(int[] nums) {

        int lo = 0, len = nums.length, hi = len - 1;

        while (lo < hi){
            int mid = lo + ((hi - lo) >> 1);
            if (nums[mid] > nums[hi]){
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return nums[lo];
    }

    /**
     * 278. First Bad Version
     */
    private static void p278() {
        int n = 10;
//        firstBadVersion(n);
    }

//    private static int firstBadVersion(int n) {
//        int lo = 1;
//
//        while (lo < n){
//            int mid = lo + ((n - lo) >> 1);
//            if (isBadVersion(mid)){
//                n = mid;
//            } else {
//                lo = mid + 1;
//            }
//        }
//        return lo;
//    }

    /**
     * 540. Single Element in a Sorted Array
     */
    private static void p540() {
        int[] nums1 = {1, 1, 2, 3, 3, 4, 4};
        int[] nums2 = {1, 1, 2, 2, 3, 4, 4};
        int[] nums3 = {1, 2, 2, 3, 3, 4, 4};
        int[] nums4 = {1, 1, 2, 2, 3, 3, 4};
//        int ret1 = singleNonDuplicate(nums);
        int ret1 = singleNonDuplicateHalfWay(nums1);
        System.out.println(ret1);
        int ret2 = singleNonDuplicateHalfWay(nums2);
        System.out.println(ret2);
        int ret3 = singleNonDuplicateHalfWay(nums3);
        System.out.println(ret3);
        int ret4 = singleNonDuplicateHalfWay(nums4);
        System.out.println(ret4);

    }

    private static int singleNonDuplicateHalfWay(int[] nums) {

        int lo = 0, len = nums.length, hi = len / 2;

        while (lo < hi){
            int mid = lo + ((hi - lo) >> 1);
            if (nums[2 * mid] == nums[2 * mid + 1]){
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return nums[2 * lo];
    }

    private static int singleNonDuplicate(int[] nums) {
        int ret = 0;
        for (int n: nums){
            ret ^= n;
        }
        return ret;
    }

    /**
     * 744. Find Smallest Letter Greater Than Target
     */
    private static void p744() {
        char[] letters = {'b', 'm', 'o', 'r', 'y'};
        char t1 = 'c';
        char t2 = 'p';
        char t3 = 'x';
        char t4 = 'z';
        char t5 = 'm';
        char c1 = nextGreatestLetter(letters, t1);
        System.out.println(c1);
        char c2 = nextGreatestLetter(letters, t2);
        System.out.println(c2);
        char c3 = nextGreatestLetter(letters, t3);
        System.out.println(c3);
        char c4 = nextGreatestLetter(letters, t4);
        System.out.println(c4);
        char c5 = nextGreatestLetter(letters, t5);
        System.out.println(c5);
    }

    private static char nextGreatestLetter(char[] letters, char target) {

        int start = 0, len = letters.length, end = len - 1;
        while (start <= end){
            int mid = start + ((end - start) >> 1);
            if (letters[mid] <= target){
                start = mid + 1;
            } else {
                end = mid - 1;
            }
            if (start == len){
                return letters[0];
            }
        }
        return letters[start];
    }

    /**
     * 69. Sqrt(x)
     */
    private static void p69() {
        int x = 2147395599;
        int ret = sqrt(x);
//        System.out.println(Integer.MAX_VALUE);
    }

    private static int sqrt(int x) {
        long start = 0, end = x;

        while (start <= end){
            long mid = start + ((end - start) >> 1);
            long midSquare = mid * mid;
            if (midSquare == x){
                return (int) mid;
            } else if (midSquare < x){
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return (int) end;
    }

}
