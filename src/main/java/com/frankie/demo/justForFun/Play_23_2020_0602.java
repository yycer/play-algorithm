package com.frankie.demo.justForFun;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author: Yao Frankie
 * @date: 2020/6/1 9:40
 */
public class Play_23_2020_0602 {

    public static void main(String[] args) {
//        p474(); // 474. Ones and Zeroes todo: waiting
//        p322(); // 322. Coin Change
//        p518(); // 518. Coin Change 2
//        p139(); // 139. Word Break
//        p77();  // 77. Combinations
//        p39();  // 39. Combination Sum
//        p40();  // 40. Combination Sum II
//        p216(); // 216. Combination Sum III
        p377(); // 377. Combination Sum IV
    }

    /**
     * https://leetcode.com/problems/combination-sum-iv/discuss/111860/Coin-change-AND-this-problem
     */
    private static void p377() {
        int[] nums = {1, 2, 3};
        int target = 4;
        int ret1 = combinationSum4(nums, target);
        System.out.println(ret1);
    }

    private static int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;

        for (int a = 1; a <= target; a++){
            for (int n: nums){
                if (a - n >= 0){
                    dp[a] += dp[a - n];
                }
            }
        }
        return dp[target];
    }

    private static void p216() {
        int k = 3;
        int n = 7;
        List<List<Integer>> ans = combinationSum3(k, n);
        for (List<Integer> c: ans){
            System.out.println(c);
        }
    }

    /**
     * https://leetcode.com/problems/combination-sum-iii/discuss/60614/Simple-and-clean-Java-code-backtracking.
     */
    private static List<List<Integer>> combinationSum3(int k, int n) {

        List<List<Integer>> ans = new LinkedList<>();
        combinationSum3(ans, new LinkedList<>(), k, 1, n);
        return ans;
    }

    private static void combinationSum3(List<List<Integer>> ans, LinkedList<Integer> tmp, int k, int start, int remain) {
        if (tmp.size() > k){
            return;
        }
        if (tmp.size() == k && remain == 0){
            ans.add(new LinkedList<>(tmp));
            return;
        }
        for (int i = start; i <= 9; i++){
            tmp.add(i);
            combinationSum3(ans, tmp, k, i + 1, remain - i);
            tmp.removeLast();
        }
    }

    private static void p40() {
        int[] candidates = {10, 1, 2, 7, 6, 1, 5};
        int target = 8;
        List<List<Integer>> ans = combinationSum2(candidates, target);
        for (List<Integer> c : ans) {
            System.out.println(c);
        }
    }

    private static List<List<Integer>> combinationSum2(int[] candidates, int target) {

        Arrays.sort(candidates);
        List<List<Integer>> ans = new LinkedList<>();
        combinationSum2(candidates, ans, new LinkedList<>(), target, 0);
        return ans;
    }

    private static void combinationSum2(int[] candidates, List<List<Integer>> ans, LinkedList<Integer> tmp, int remain, int start) {
        if (remain < 0){
            return;
        } else if (remain == 0){
            ans.add(new LinkedList<>(tmp));
        } else {
            for (int i = start; i < candidates.length; i++){
                int cur = candidates[i];
                // Skip duplicates.
                if (i > start && cur == candidates[i - 1]){
                    continue;
                }
                tmp.add(cur);
                combinationSum2(candidates, ans, tmp, remain - cur, i + 1);
                tmp.removeLast();
            }
        }
    }

    private static void p39() {
        int[] candidates = {2, 3, 6, 7};
        int target = 7;
        combinationSum(candidates, target);
    }

    private static List<List<Integer>> combinationSum(int[] candidates, int target) {

        Arrays.sort(candidates);
        List<List<Integer>> ans = new LinkedList<>();
        combinationSum(candidates, ans, new LinkedList<>(), target, 0);
        return ans;
    }

    private static void combinationSum(int[] candidates, List<List<Integer>> ans, LinkedList<Integer> tmp, int remain, int start) {
        if (remain < 0){
            return;
        } else if (remain == 0){
            ans.add(new LinkedList<>(tmp));
        } else {
            for (int i = start; i < candidates.length; i++){
                tmp.add(candidates[i]);
                combinationSum(candidates, ans, tmp, remain - candidates[i], i);
                tmp.removeLast();
            }
        }
    }

    private static void p77() {
        int n = 4, k = 2;
        List<List<Integer>> ret1 = combine_77(n, k);
        System.out.println(ret1.size());
    }

    /**
     * 如何处理k个元素?
     */
    private static List<List<Integer>> combine_77(int n, int k) {
        List<List<Integer>> ans = new LinkedList<>();
        combine_77(ans, new ArrayList<>(), 1, n, k);
        return ans;
    }

    private static void combine_77(List<List<Integer>> ans, ArrayList<Integer> tmpList, int lo, int hi, int k) {
        if (k == 0){
            ans.add(new LinkedList<>(tmpList));
            return;
        }

        for (int i = lo; i <= hi; i++){
            tmpList.add(i);
            combine_77(ans, tmpList, i + 1, hi, k - 1);
            tmpList.remove(tmpList.size() - 1);
        }
    }

    private static void p139() {
        String s = "leetcode";
        List<String> wordDict = Arrays.asList("leet", "code");
        boolean ret1 = wordBreak(s, wordDict);
        System.out.println(ret1);
    }

    private static boolean wordBreak(String s, List<String> wordDict) {

        int len = s.length();
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;

        for (int i = 1; i <= len; i++){
            for (int j = 0; j < i; j++){
                if (dp[j] && wordDict.contains(s.substring(j, i))){
                    dp[i] = true;
                    break; // break nested loop.
                }
            }
        }
        return dp[len];
    }

    private static void p518() {
        int[] coins = {1, 2, 5};
        int amount = 5;
        int ret1 = change(amount, coins);
        System.out.println(ret1);
    }

    private static int change(int amount, int[] coins) {
        int len = coins.length;
        int[][] dp = new int[len + 1][amount + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= len; i++){
            dp[i][0] = 1;
            for (int a = 1; a <= amount; a++){
                int rest = a - coins[i - 1];
                if (rest >= 0){
                    dp[i][a] = dp[i - 1][a] + dp[i][rest];
                } else {
                    dp[i][a] = dp[i - 1][a];
                }
            }
        }
        return dp[len][amount];
    }

    private static void p322() {
//        int[] coins = {1, 2, 5};
//        int amount = 6;
//        int[] coins = {2};
//        int amount = 3;
        int[] coins = {2, 5, 10, 1};
        int amount = 27;
        int ret1 = coinChange(coins, amount);
        System.out.println(ret1);
    }

    /**
     * https://leetcode.com/problems/coin-change/discuss/77360/C%2B%2B-O(n*amount)-time-O(amount)-space-DP-solution
     */
    private static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        // If use Integer.MAX_VALUE, it will cause an overflow.
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int c: coins){
            for (int a = c; a <= amount; a++){
                dp[a] = Math.min(dp[a], dp[a - c] + 1);
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    /**
     * 使用二维数组，边界条件极其复杂！
     * coins  = [2,5,10,1]
     * amount = 27
     * Expected = 4
     */
    private static int coinChangeNotWork(int[] coins, int amount) {

        int len = coins.length;
        int[][] dp = new int[len + 1][amount + 1];
        Arrays.fill(dp[0], Integer.MAX_VALUE);

        for (int i = 1; i <= len; i++){
            for (int a = 1; a <= amount; a++){
                // If has some rest.
                if (a - coins[i - 1] >= 0){
                    // Think about: coins = [2], amount = 3.
                    if (dp[i][a - coins[i - 1]] == -1){
                        dp[i][a] = -1;
                        continue;
                    }
                    dp[i][a] = Math.min(dp[i - 1][a], dp[i][a - coins[i - 1]] + 1);
                } else {
                    // Think about: coins = [2], amount = 3.
                    if (dp[i - 1][a] == Integer.MAX_VALUE){
                        dp[i][a] = -1;
                        continue;
                    }
                    dp[i][a] = dp[i - 1][a];
                }
            }
        }

        return dp[len][amount];
    }

    private static void p474() {

    }
}
