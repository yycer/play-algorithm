package com.frankie.demo.math;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @author: Yao Frankie
 * @date: 2020/5/15 10:21
 */


public class MathTest {

    public static class Bucket{
        Integer min;
        Integer max;
    }
    public static void main(String[] args) {
//        p204();
//        p7();
//        p279();
//        gcd();

//        p125();

//        p560();

        int a = 8;
        int ans = (int) Math.ceil((double) a / 6);
        System.out.println(ans);
    }

    private static void calBugs() {

        // 0 1 1 2 3 5 8
        // 0 1 2 3 4 5 6

    }

    private static void p560() {

        int[] nums = {1, 1, 1};
        int k = 2;
        int ans = 0;
        int len = nums.length;
        int[] preSum = new int[len + 1];
        // key is prefixSum, val is frequency.
        HashMap<Integer, Integer> map = new HashMap<>();
        // HashSet<Integer> set = new HashSet<>();

        // Build preSum.
        for (int i = 0; i < len; i++){
            preSum[i + 1] = preSum[i] + nums[i];
        }

        // sum of range[i, j] = preSum[j] - preSum[i - 1]
        // 0
        // 1
        // 2
        // 3
        for (int n: preSum){
            // 2
            // 1
            // 0
            // 1
            int target = k - n;
            if (map.containsKey(target)){
                // 1
                // 2
                ans += map.get(target);
            }
            map.put(n, map.getOrDefault(n, 0) + 1);

        }
        // nums   =    [1, 1, 1]
        // preSum = [0, 1, 2, 3]
        // map    = {0:1, 1:1, 2:1, 3:1}

        System.out.println(ans);

    }

    private static void sum4() {
        int[] nums = {1, 0, -1, 0, -2, 2};
        int target = 0;
        List<List<Integer>> ans = new LinkedList<>();
        int len = nums.length;

        for (int i = 0; i < len - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int first = nums[i];
            for (int j = i + 1; j < len - 2; j++) {
                if (j > 0 && nums[j] == nums[j - 1]) continue;
                int second = nums[j];
                int rest = target - first - second;
                int lo = j + 1;
                int hi = len - 1;
                while (lo < hi) {
                    int sum = nums[lo] + nums[hi];
                    if (sum == rest) {
                        ans.add(Arrays.asList(first, second, nums[lo], nums[hi]));
                        while (lo < hi && nums[lo] == nums[lo + 1]) lo++;
                        while (lo < hi && nums[hi] == nums[hi - 1]) hi++;
                        lo++;
                        hi--;
                    } else if (sum < rest) {
                        lo++;
                    } else {
                        hi--;
                    }
                }
            }
        }
        System.out.println(ans);
    }

    private static void find() {

        int[] nums = {5, 7, 7, 8, 8, 10};
        int target = 8;
        int left = getLeftIndex(nums, target);
        int right = getRightIndex(nums, target);

//        if (left == -1 && right == -1) return 0;
        int ans = right - left + 1;
        System.out.println(ans);
    }

    private static int getLeftIndex(int[] nums, int target) {

        int lo = 0;
        int hi = nums.length - 1;

        while (lo <= hi){
            int mid = (lo + hi) >>> 1;
            int cur = nums[mid];
            if (cur >= target){
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return nums[lo] == target ? lo : -1;
    }

    private static int getRightIndex(int[] nums, int target) {

        int lo = 0;
        int hi = nums.length - 1;

        while (lo <= hi){
            int mid = (lo + hi) >>> 1;
            int cur = nums[mid];
            if (cur <= target){
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return nums[hi] == target ? hi : -1;
    }

    private static void p125() {
        boolean ans = isPalindrome("race a car");
        System.out.println(ans);
    }

    public static boolean isPalindrome(String s) {

        s = s.toLowerCase();
        int len = s.length();
        if (len == 0) return true;

        int lo = 0;
        int hi = len - 1;

        while (lo < hi){
            if (s.charAt(lo) < '0' || s.charAt(lo) > '9' || s.charAt(lo) < 'a' || s.charAt(lo) > 'z'){
                lo++;
                continue;
            }

            if (s.charAt(hi) < '0' || s.charAt(hi) > '9' || s.charAt(hi) < 'a' || s.charAt(hi) > 'z'){
                hi--;
                continue;
            }

            if (s.charAt(lo) != s.charAt(hi)){
                return false;
            }
            lo++;
            hi--;
        }

        return true;
    }

    // Greatest Common Divisor.
    private static void gcd() {
        int a = 20;
        int b = 15;
        int ans1 = gcdV1(a, b);
        int ans2 = gcdV2(a, b);
        int ans3 = gcdV3(a, b);
        int ans4 = gcdV4(a, b);

//        System.out.println(ans1);
//        System.out.println(ans2);
//        System.out.println(ans3);
        System.out.println(ans4);
    }

    /**
     * 暴力法
     */
    private static int gcdV1(int a, int b) {

        int big   = Math.max(a, b);
        int small = Math.min(a, b);

        for (int i = small / 2; i > 1; i--){
            if (big % i == 0 && small % i == 0){
                return i;
            }
        }
        return 1;
    }

    /**
     * 辗转相除法
     */
    private static int gcdV2(int a, int b) {

        int big   = Math.max(a, b);
        int small = Math.min(a, b);

        if (big % small == 0){
            return small;
        }

        return gcdV2(big % small, small);
    }

    /**
     * 更相减损法
     */
    private static int gcdV3(int a, int b) {
        if (a == b) return a;

        int big   = Math.max(a, b);
        int small = Math.min(a, b);

        return gcdV3(big - small, small);
    }

    /**
     * 更相减损法 & 移位
     */
    private static int gcdV4(int a, int b) {

        if (a == b) return a;

        if ((a & 1) == 0 && (b & 1) == 0){
            return gcdV4(a >>> 1, b >>> 1) << 1;
        } else if ((a & 1) == 0 && (b & 1) == 1){
            return gcdV4(a >>> 1, b);
        } else if ((a & 1) == 1 && (b & 1) == 0){
            return gcdV4(a, b >>> 1);
        } else {
            int big   = Math.max(a, b);
            int small = Math.min(a, b);
            return gcdV4(big - small, small);
        }
    }


    private static void gcdBruteForce() {
    }

    /**
     * 279. Perfect Squares
     */
    private static void p279() {
//        int n = 140;
//        int ret1 = numSquares(n);
//        System.out.println(ret1);
//        int n2 = 140;
//        int ret2 = numSquaresNotWork(n2);
//        System.out.println(ret2);
        int n3 = 12;
        int ret3 = numSquaresUsingDP(n3);
        System.out.println(ret3);
    }

    /**
     * https://leetcode.com/problems/perfect-squares/discuss/71605/Java-DP-Solution-with-explanation
     */
    private static int numSquaresUsingDP(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);

        for (int i = 1; i <= n; i++){
            int sqrt = (int) Math.sqrt(i);

            if (sqrt * sqrt == i){
                dp[i] = 1;
                continue;
            }

            for (int j = 1; j <= sqrt; j++){
                // Amazing
                int diff = i - j * j;
                dp[i] = Math.min(dp[i], dp[diff] + 1);
            }
        }
        return dp[n];
    }

    /**
     * 12
     * My answer: 9 + 1 + 1 + 1
     * Right    : 4 + 4 + 4
     */
    private static int numSquaresNotWork(int n) {
        int count = 0;
        while (n > 0){
            int sqrt = (int) Math.sqrt(n);
            n -= sqrt * sqrt;
            count++;
        }
        return count;
    }

    /**
     * 7. Reverse Integer
     */
    private static void p7() {
        int x1 = 123;
        int ret1 = ReverseAmazing(x1);
        System.out.println(ret1);
        int x2 = -123;
        int ret2 = ReverseAmazing(x2);
        System.out.println(ret2);
        int x3 = 120;
        int ret3 = ReverseAmazing(x3);
        System.out.println(ret3);
        int x4 = 901000;
        int ret4 = ReverseAmazing(x4);
        System.out.println(ret4);
        int x5 = 1534236469;
        int ret5 = ReverseAmazing(x5);
        System.out.println(ret5);
    }


    private static int Reverse(int x) {
        if (x == 0) return 0;
        int cur = Math.abs(x);
        while (cur % 10 == 0){
            cur /= 10;
        }
        long ret = 0;
        while (cur > 0){
            ret = ret * 10 + cur % 10;
            if (ret > Integer.MAX_VALUE){
                return 0;
            }
            cur /= 10;
        }
        return x > 0 ? (int) ret : (int) -ret;
    }

    private static int ReverseOptimize(int x) {
        long ret = 0;
        while (x != 0){
            ret = ret * 10 + x % 10;
            x /= 10;
            if (ret > Integer.MAX_VALUE || ret < Integer.MIN_VALUE){
                return 0;
            }
        }
        return (int) ret;
    }

    private static int ReverseAmazing(int x) {
        int pre = 0, ret = 0;
        while (x != 0){
            ret = ret * 10 + x % 10;
            if ((ret - x % 10) / 10 != pre){
                return 0;
            }
            x /= 10;
            pre = ret;
        }
        return ret;
    }

    /**
     * 204. Count Primes
     */
    private static void p204() {
        int n = 47000;
//        int ret1 = countPrimesTimeExceeded(n);
//        System.out.println(ret1);

        int ret2 = countPrimesAmazing(n);
        System.out.println(ret2);
    }

    private static int countPrimesAmazing(int n) {
        int[] notPrimes = new int[n];
        int count = 0;
        for (int i = 2; i < n; i++){
            if (notPrimes[i] == 0){
                count++;
            }
            for (int j = 2; i * j < n; j++){
                notPrimes[i * j] = 1;
            }
        }
        return count;
    }


    private static int countPrimesTimeExceeded(int n) {
        int count = 0;
        for(int i = 2; i < n; i++){
            if (isPrime(i)){
                count++;
            }
        }
        return count;
    }

    private static boolean isPrime(int n) {
        if (n <= 1){
            return false;
        }
        int threshold = (int) Math.sqrt(n);
        while (threshold > 1){
            if (n % threshold == 0){
                return false;
            }
            threshold--;
        }
        return true;
    }
}
