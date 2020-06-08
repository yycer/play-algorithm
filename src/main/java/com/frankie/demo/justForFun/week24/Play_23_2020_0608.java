package com.frankie.demo.justForFun.week24;

/**
 * @author: Yao Frankie
 * @date: 2020/6/1 9:40
 */
public class Play_23_2020_0608 {

    public static void main(String[] args) {
//        p121(); // 121. Best Time to Buy and Sell Stock
//        p122(); // 122. Best Time to Buy and Sell Stock II
//        p123(); // 123. Best Time to Buy and Sell Stock III
    }

    private static void p123() {
//        int[] prices = {3, 3, 5, 0, 0, 3, 1, 4};
        int[] prices = {1, 2, 3, 4, 5};
        int a = maxProfit_123(prices);
        System.out.println(a);
    }

    private static int maxProfit_123(int[] prices) {

        int b1 = Integer.MIN_VALUE, s1 = 0;
        int b2 = Integer.MIN_VALUE, s2 = 0;

        for (int i = 0; i < prices.length; i++){
            int cur = prices[i];
            b1 = Math.max(b1, -cur);
            s1 = Math.max(s1, b1 + cur);
            b2 = Math.max(b2, s1 - cur);
            s2 = Math.max(s2, b2 + cur);
        }
        return s2;
    }

    private static int maxProfit_123_not_work(int[] nums) {

        int first = 0, second = 0;
        int len = nums.length;

        for (int i = 1; i < len; i++){
            int diff = Math.max(0, nums[i] - nums[i - 1]);
            if (diff >= first){
                first   = diff;
                second = first;
            } else {
                second = Math.max(second, diff);
            }
        }
        return first + second;
    }

    private static void p122() {
        int[] prices = {7, 1, 5, 3, 6, 4};
        int a = maxProfit_122(prices);
        System.out.println(a);
    }

    private static int maxProfit_122(int[] prices) {
        int len = prices.length;
        int ans = 0;

        for (int i = 1; i < len; i++){
            ans += Math.max(0, prices[i] - prices[i - 1]);
        }
        return ans;
    }

    private static int maxProfit_122_not_work(int[] prices) {

        int len = prices.length;
        int[] hold   = new int[len];
        int[] unhold = new int[len];
        hold[0] = -prices[0];

        for (int i = 1; i < len; i++){
            int cur = prices[i];
            hold[i] = Math.max(-cur, hold[i - 1]);

            // 卖了就赚钱
            if (cur + hold[i - 1] > 0){
                unhold[i] = unhold[i - 1] + cur + hold[i - 1];
                hold[i]   = Integer.MIN_VALUE;
            } else {
                unhold[i] = unhold[i - 1];
            }
        }

        return unhold[len - 1];
    }

    private static void p121() {
        int[] prices = {7, 1, 5, 3, 6, 4};
//        int a = maxProfit_121(prices);
        int a = maxProfit_121_optimize(prices);
        System.out.println(a);
    }

    private static int maxProfit_121(int[] prices) {
        int max = 0;
        int len = prices.length;
        for (int i = 0; i < len; i++){
            for (int j = i + 1; j < len; j++){
                max = Math.max(max, prices[j] - prices[i]);
            }
        }
        return max;
    }

    private static int maxProfit_121_optimize(int[] prices) {
        int len = prices.length;
        if (len == 0) return 0;
        int preMin = prices[0];
        int maxPro = 0;

        for (int i = 1; i < len; i++){
            maxPro = Math.max(maxPro, prices[i] - preMin);
            preMin = Math.min(preMin, prices[i]);
        }

        return maxPro;
    }
}