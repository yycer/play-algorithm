package com.frankie.demo.justForFun.week24;

/**
 * @author: Yao Frankie
 * @date: 2020/6/1 9:40
 */
public class Play_23_2020_0608 {

    public static void main(String[] args) {
        p121(); // 121. Best Time to Buy and Sell Stock
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