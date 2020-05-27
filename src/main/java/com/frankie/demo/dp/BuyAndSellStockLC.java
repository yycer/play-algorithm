package com.frankie.demo.dp;

/**
 * @author: Yao Frankie
 * @date: 2020/5/27 13:29
 */
public class BuyAndSellStockLC {

    // https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/discuss/108870/most-consistent-ways-of-dealing-with-the-series-of-stock-problems
    public static void main(String[] args) {
//        p121(); // Best Time to Buy and Sell Stock I
//        p122(); // Best Time to Buy and Sell Stock II
//        p309(); // Best Time to Buy and Sell Stock with Cooldown
//        p714(); // Best Time to Buy and Sell Stock with Transaction Fee
//        p123(); // Best Time to Buy and Sell Stock III
//        p188(); // Best Time to Buy and Sell Stock IV
    }

    private static void p188() {
        int k = 2;
        int[] prices = {3, 3, 5, 0, 0, 3, 1, 4};
        int ret1 = maxProfit_188(k, prices);
        System.out.println(ret1);
    }

    private static int maxProfit_188(int k, int[] prices) {

//        int len = prices.length;
//        int[][][] dp = new int[len][k + 1][2];
//        for (int i = 0; i < len; i++){
//            for (int j = k; j >= 1; j--){
//
//            }
//        }
        return -1;
    }

    /**
     * 123. Best Time to Buy and Sell Stock III
     */
    private static void p123() {
        int[] prices = {3, 3, 5, 0, 0, 3, 1, 4};
        int ret1 = maxProfit_123(prices);
        System.out.println(ret1);
    }

    private static int maxProfit_123(int[] prices) {
        // dp[i][2][0] = max(dp[i-1][2][0], dp[i-1][1][1] + prices[i]);
        // dp[i][2][1] = max(dp[i-1][2][1], dp[i-1][1][0] - prices[i]);
        // dp[i][1][0] = max(dp[i-1][1][0], dp[i-1][0][1] + prices[i]);
        // dp[i][1][1] = max(dp[i-1][1][1], -prices[i]);

        if (prices == null || prices.length == 0) return 0;
        int len = prices.length;
        int dp_i_2_0 = 0, dp_i_2_1 = Integer.MIN_VALUE;
        int dp_i_1_0 = 0, dp_i_1_1 = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++){
            dp_i_2_0 = Math.max(dp_i_2_0, dp_i_2_1 + prices[i]);
            dp_i_2_1 = Math.max(dp_i_2_1, dp_i_1_0 - prices[i]);
            dp_i_1_0 = Math.max(dp_i_1_0, dp_i_1_1 + prices[i]);
            dp_i_1_1 = Math.max(dp_i_1_1, -prices[i]);
        }
        return dp_i_2_0;
    }

    /**
     * 714. Best Time to Buy and Sell Stock with Transaction Fee
     */
    private static void p714() {
        int[] prices = {1, 3, 2, 8, 4, 9};
        int fee = 2;
        int ret1 = maxProfit_714(prices, fee);
        System.out.println(ret1);
    }

    private static int maxProfit_714(int[] prices, int fee) {
        if (prices == null || prices.length == 0) return 0;
        int len = prices.length;
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++){
            int tmp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, tmp - prices[i] - fee);
        }
        return dp_i_0;
    }

    /**
     * 309. Best Time to Buy and Sell Stock with Cooldown
     */
    private static void p309() {
        int[] prices = {1, 2, 3, 0, 2};
        int ret1 = maxProfit_309(prices);
        System.out.println(ret1);
    }

    private static int maxProfit_309(int[] prices) {

        if (prices == null || prices.length == 0) return 0;
        int len = prices.length;
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
        int dp_pre_0 = 0; // represents dp[i-2][0]
        for (int i = 0; i < len; i++){
            int tmp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1   + prices[i]);
            dp_i_1 = Math.max(dp_i_1, dp_pre_0 - prices[i]);
            dp_pre_0 = tmp;
        }
        return dp_i_0;
    }

    /**
     * 122. Best Time to Buy and Sell Stock II
     */
    private static void p122() {
        int[] prices = {7, 1, 5, 3, 6, 4};
        maxProfit_122(prices);
    }

    private static int maxProfit_122(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int len = prices.length;
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++){
            int tmp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, tmp - prices[i]);
        }
        return dp_i_0;
    }

    /**
     * Best Time to Buy and Sell Stock 1
     *
     */
    private static void p121() {
        int[] prices = {4, 6, 3, 7};
        int ret1 = maxProfit(prices);
        System.out.println(ret1);
    }

    /**
     * https://labuladong.gitbook.io/algo/dong-tai-gui-hua-xi-lie/tuan-mie-gu-piao-wen-ti
     * ----------------------------------------------------------------------------
     * dp[i][k][0/1]: 在第i天，是否持有股票的情况下，最多允许k次操作可以获取最大的利润。
     * eg: dp[3][2][0]：现在是第3天，我现在手头未持有股票，最多允许2次操作可以获得最大的利润。
     * eg: dp[8][3][1]：现在是第8天，我现在手头已持有股票，最多允许3次操作可以获得最大的利润。
     * Target = dp[N-1][K][0]
     * ----------------------------------------------------------------------------
     * 选择：买入[Buy]、卖出[Sell]、无操作[Rest]
     * 状态：天数、允许交易的最大次数、当前的持有状态。
     * ----------------------------------------------------------------------------
     * dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
     *               max(    rest     ,            sell          )
     * 今天我没有持有股票的原因：
     * 1. 我昨天就没有买股票。
     * 2. 我昨天买了股票，但是我今天卖了。
     * ----------------------------------------------------------------------------
     * dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k][0] - prices[i])
     *               max(    rest     ,            buy           )
     * 今天我持有股票的原因：
     * 1. 我昨天就买股票。
     * 2. 我昨天未持有股票，但是我今天买了。
     * ----------------------------------------------------------------------------
     * Base case
     * 1. dp[-1][k][0] = 0， 证券交易所还没开门。
     * 2. dp[-1][k][1] = -1，不可能，证券交易所还没开门，你怎么买的股票。
     * 3. dp[i][0][0]  = 0， 如果不允许你交易，当天利润肯定是0。
     * 4. dp[i][0][1] = -1， 不允许交易的情况下，是不可能持有股票的。
     * ----------------------------------------------------------------------------
     */
    private static int maxProfit(int[] prices) {
        int len = prices.length;
        int[][] dp = new int[len][2];

        for (int i = 0; i < len; i++){
            // 第一天
            if (i == 0){
                // 不买
                dp[i][0] = 0;
                // dp[0][1] = Math.max(dp[-1][1], dp[-1][0] - prices[i]);
                //          = Math.max(-infinity, 0 - prices[i]);
                //          = -prices[i];
                // 入手
                dp[i][1] = -prices[i];
                continue;
            }
            // 如果今天未持有股票，说明要么之前就没买，或者之前买了，今天又卖了。
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            // 如果今天已持有股票，说明要么之前就买了，或者之前没买，但是今天买了。
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
        }
        return dp[len - 1][0];
    }

    /**
     * prices:[3  2  6  5  0  3]
     * index : 0  1  2  3  4  5
     * preMin: 3  2  2  2  0  0
     * maxPro:-X -1  4  3 -2  3
     *
     * Exception: [7, 6, 4, 3, 1]
     *
     */
    private static int maxProfit1_1(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int len    = prices.length;
        int preMin = prices[0];
        int maxPro = Integer.MIN_VALUE;

        for (int i = 1; i < len; i++){
            int cur = prices[i];
            maxPro = Math.max(maxPro, cur - preMin);
            if (cur < preMin){
                preMin = cur;
            }
        }
        return Math.max(maxPro, 0);
    }

}
