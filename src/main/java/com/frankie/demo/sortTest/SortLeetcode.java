package com.frankie.demo.sortTest;

import com.frankie.demo.utils.Utils;

import java.util.*;

/**
 * @author: Yao Frankie
 * @date: 2020/5/14 9:31
 */
public class SortLeetcode {

    public static void main(String[] args) {
//        p215();
//        p347();
        p75();
    }

    /**
     * 75. Sort Colors
     */
    private static void p75() {
        int[] nums = {2, 0, 1, 1, 0, 2};
        sortColorsUsingBubble(nums);
        System.out.println(Arrays.toString(nums));

        sortColorsUsingSelection(nums);
        System.out.println(Arrays.toString(nums));

        sortColorUsingInsertion(nums);
        System.out.println(Arrays.toString(nums));

        sortColorUsingMerge(nums);
        System.out.println(Arrays.toString(nums));

        sortColorAmazing(nums);
        System.out.println(Arrays.toString(nums));
    }

    private static void sortColorAmazing(int[] nums) {
        int lo = 0, hi = nums.length - 1;
        int cur = 0;
        while (cur <= hi){
            if (nums[cur] == 0){
                Utils.swap(nums, cur++, lo++);
            } else if (nums[cur] == 2) {
                Utils.swap(nums, cur, hi--);
            } else {
                cur++;
            }
        }
    }


    private static void sortColorUsingMerge(int[] nums) {
        seperate(nums, 0, nums.length - 1);
    }

    private static void seperate(int[] nums, int lo, int hi) {
        if (lo >= hi) return;
        int mid = lo + ((hi - lo) >> 1);
        seperate(nums, lo, mid);
        seperate(nums, mid + 1, hi);
        doMerge(nums, lo, mid + 1, hi);
    }

    private static void doMerge(int[] nums, int lo, int mid, int hi) {
        int[] left  = new int[mid - lo];
        int[] right = new int[hi - mid + 1];

        for (int i = lo; i < mid; i++){
            left[i - lo] = nums[i];
        }

        for (int j = mid; j <= hi; j++){
            right[j - mid] = nums[j];
        }

        int i = 0, j = 0, k = lo;
        while (i < left.length && j < right.length){
            if (left[i] < right[j]){
                nums[k++] = left[i++];
            } else {
                nums[k++] = right[j++];
            }
        }

        while (i < left.length){
            nums[k++] = left[i++];
        }

        while (j < right.length){
            nums[k++] = right[j++];
        }
    }

    private static void sortColorUsingInsertion(int[] nums) {
        int len = nums.length;
        for (int i = 1; i < len; i++){
            int j = i;
            while (j > 0){
                if (nums[j] < nums[j - 1]){
                    Utils.swap(nums, j, j - 1);
                }
                j--;
            }
        }
    }

    /**
     * 每轮选择一个最大的元素，将其移至最右侧。
     * 选择排序较于冒泡排序的优势在于：减少交换次数。
     * @param nums
     */
    private static void sortColorsUsingSelection(int[] nums) {
        int len = nums.length;
        for (int i = len - 1; i > 0; i--){
            int j = 1, maxIndex = 0;
            while (j <= i){
                if (nums[j] > nums[maxIndex]){
                    maxIndex = j;
                }
                j++;
            }
            if (i != maxIndex){
                Utils.swap(nums, i, maxIndex);
            }
        }
    }

    private static void sortColorsUsingBubble(int[] nums) {
        for (int i = nums.length - 1; i > 0; i--){
            int j = 1;
            while (j <= i){
                if (nums[j] < nums[j - 1]){
                    Utils.swap(nums, j, j - 1);
                }
                j++;
            }
        }
    }

    /**
     * 347. Top K Frequent Elements
     */
    private static void p347() {
        int[] nums1 = {5, 5, 5, 3, 1, 1};
        int k1 = 2;
        int[] ret1 = topKFrequent(nums1, k1);
        System.out.println(Arrays.toString(ret1));
//        int[] nums2 = {1, 2};
//        int k2 = 2;
//        int[] ret2 = topKFrequent(nums2, k2);
//        System.out.println(Arrays.toString(ret2));
        int[] nums3 = {5, 5, 5, 5, 2, 2, 2, 4, 4, 1};
        int k3 = 2;
        int[] ret3 = topKFrequentOptimize(nums3, k3);
        System.out.println(Arrays.toString(ret3));

        int[] nums4 = {5, 5, 5, 5, 2, 2, 2, 4, 4, 4, 1};
        int k4 = 2;
        int[] ret4 = topKFrequentUsingBucket(nums4, k4);
        System.out.println(Arrays.toString(ret4));

//        int[] nums5 = {1};
//        int k5 = 1;
//        int[] ret5 = topKFrequentUsingBucket(nums5, k5);
//        System.out.println(Arrays.toString(ret5));
    }

    /**
     * https://leetcode.com/problems/top-k-frequent-elements/discuss/81602/Java-O(n)-Solution-Bucket-Sort
     */
    private static int[] topKFrequentUsingBucket(int[] nums, int k) {

        // Step1: Build frequency map.
        HashMap<Integer, Integer> map = new HashMap<>(64);
        for (int n: nums){
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        // Step2: Build bucket array.
        List<Integer>[] bucketArr = new List[nums.length + 1];
        for (Map.Entry<Integer, Integer> entry: map.entrySet()){
            int frequency = entry.getValue();
            if (bucketArr[frequency] == null){
                bucketArr[frequency] = new ArrayList<>();
            }
            bucketArr[frequency].add(entry.getKey());
        }

        // Step3: Build result list.
        ArrayList<Integer> retList = new ArrayList<>();
        for (int i = bucketArr.length - 1; i >= 0 && retList.size() < k; i--){
            if (bucketArr[i] != null){
                retList.addAll(bucketArr[i]);
            }
        }

        return retList.subList(0, k).stream().mapToInt(x -> x).toArray();
    }

    /**
     * https://leetcode.com/problems/top-k-frequent-elements/discuss/81733/*Java*-straightforward-O(N-%2B-(N-k)lg-k)-solution
     */
    private static int[] topKFrequentOptimize(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>(64);
        ArrayList<Integer> retList    = new ArrayList<>();

        // Step1: Build frequency map.
        for (int n: nums){
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        // Step2: Build priority queue.
        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(
                Comparator.comparingInt(Map.Entry::getValue));
        for (Map.Entry<Integer, Integer> entry: map.entrySet()){
            pq.offer(entry);
            if (pq.size() > k){
                pq.poll();
            }
        }

        // Step3: Build result list.
        while (!pq.isEmpty()){
            retList.add(pq.poll().getKey());
        }

        return retList.stream().mapToInt(x -> x).toArray();
    }

    private static int[] topKFrequent(int[] nums, int k) {

        // Step1: Build frequency map.
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int n: nums){
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        // Step2: Build max heap.
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int value: map.values()){
            pq.add(value);
            if (pq.size() > k){
                pq.poll();
            }
        }

        // Step3: Build result list.
        ArrayList<Integer> retList = new ArrayList<>();
        for (int n: pq){
            for (Map.Entry<Integer, Integer> entry: map.entrySet()){
                if (n == entry.getValue()){
                    retList.add(entry.getKey());
                }
            }
        }

        return retList.stream().distinct().mapToInt(x -> x).toArray();
    }

    /**
     * 215. Kth Largest Element in an Array
     */
    private static void p215() {
//        int[] nums = {5, 2, 1, 8, 3};
//        int[] nums = {5, 2, 1, 8, 3, 5, 7};
        int[] nums = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        int k = 4;
//        int ret1 = findKthLargestUsingSort(nums, k);
//        System.out.println(ret1);
        int ret2 = findKthLargestUsingPQ(nums, k);
//        System.out.println(ret2);
        int ret3 = findKthLargestUsingQuickSort(nums, k);
        System.out.println(ret3);
    }

    private static int findKthLargestUsingQuickSort(int[] nums, int k) {
        int len = nums.length;
        int lo = 0, hi = len - 1, index = len - k;
        while (lo < hi){
            int pivotIndex = getPivotIndex(nums, lo, hi);
            if (pivotIndex < index)      lo = pivotIndex + 1;
            else if (pivotIndex > index) hi = pivotIndex - 1;
            else return nums[pivotIndex];
        }
        return nums[lo];
    }

    private static int getPivotIndex(int[] nums, int lo, int hi) {
        int pivot = nums[lo];
        // Be careful start = lo.
        int start = lo, end = hi;

        while (start != end){
            while (start < end && nums[end] > pivot){
                end--;
            }
            while (start < end && nums[start] <= pivot){
                start++;
            }
            if (start < end){
                Utils.swap(nums, start, end);
            }
        }
        nums[lo]    = nums[start];
        nums[start] = pivot;
        return start;
    }

    private static int findKthLargestUsingPQ(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int n: nums){
            pq.offer(n);
            if (pq.size() > k){
                pq.poll();
            }
        }
        return pq.peek();
    }

    private static int findKthLargestUsingSort(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }
}
