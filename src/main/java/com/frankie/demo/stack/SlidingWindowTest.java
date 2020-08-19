package com.frankie.demo.stack;

import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * @author: Yao Frankie
 * @date: 2020/8/19 07:48
 */
public class SlidingWindowTest {

    public static void main(String[] args) {
        p239();
    }

    private static void p239() {
        int[] nums = {1, 3, 1, 2, 0, 5};
        int k = 3;
        int[] ans = maxSlidingWindow(nums, k);
        System.out.println(Arrays.toString(ans));
    }

    private static int[] maxSlidingWindow(int[] nums, int k) {
        int len = nums.length;
        if (len < k) return new int[0];
        int[] ans = new int[len - k + 1];
        ArrayDeque<Integer> q = new ArrayDeque<>();

        // 1. 将前k个元素加入队列
        for (int i = 0; i < k; i++){
            while (!q.isEmpty() && q.peekLast() < nums[i]){
                q.poll();
            }
            q.offer(nums[i]);
        }

        // 2. 遍历j直至数组结束
        int l = 0, r = k;
        ans[l] = q.peek();
        if (nums[l] == q.peek()) q.poll();
        l++;
        System.out.println(Arrays.toString(ans));
        for (int j = k; j < len; j++){
            while (!q.isEmpty() && q.peekLast() < nums[j]){
                q.pollLast();
            }
            q.offer(nums[j]);
            ans[l] = q.peek();
            if (nums[l] == q.peek()) q.poll();
            l++;
        }
        return ans;
    }
}
