package com.frankie.demo.math;

import java.util.Arrays;

/**
 * @author: Yao Frankie
 * @date: 2020/5/15 10:21
 */
public class MathTest {

    public static void main(String[] args) {
//        p204();
//        p7();
//        p279();
        gcd();
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
