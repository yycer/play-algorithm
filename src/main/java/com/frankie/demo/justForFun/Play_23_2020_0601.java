package com.frankie.demo.justForFun;

import java.util.Arrays;

/**
 * @author: Yao Frankie
 * @date: 2020/6/1 9:40
 */
public class Play_23_2020_0601 {

    public static void main(String[] args) {
        p413(); // 413. Arithmetic Slices
    }

    private static void p413() {
        int[] A = {1, 2, 3, 4};
//        int ret1 = numberOfArithmeticSlices(A);
        int ret1 = numberOfArithmeticSlicesOptimize(A);
        System.out.println(ret1);
    }

    private static int numberOfArithmeticSlicesOptimize(int[] A) {
        int ans  = 0;
        int len  = A.length;
        int[] dp = new int[len];
        for (int i = 2; i < len; i++){
            if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]){
                dp[i] = dp[i - 1] + 1;
                ans += dp[i];
            }
        }
        return ans;

    }

    private static int numberOfArithmeticSlices(int[] A) {

        int len  = A.length;
        // dp[i]: Arithmetic slices ending with [i]th element.
        int[] dp = new int[len];
        for (int i = 2; i < len; i++){
            if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]){
                dp[i] = dp[i - 1] + 1;
            }
        }
        return Arrays.stream(dp).sum();
    }
}
