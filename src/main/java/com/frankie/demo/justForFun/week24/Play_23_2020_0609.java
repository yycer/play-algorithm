package com.frankie.demo.justForFun.week24;

/**
 * @author: Yao Frankie
 * @date: 2020/6/1 9:40
 */
public class Play_23_2020_0609 {

    public static void main(String[] args) {
        p413(); // 413. Arithmetic Slices
    }

    private static void p413() {
        int[] A = {1, 2, 3, 8, 9, 10};
        int a = numberOfArithmeticSlices(A);
        System.out.println(a);
    }

    private static int numberOfArithmeticSlices(int[] A) {
        int len  = A.length;
        int ans  = 0;
        int[] dp = new int[len];

        for (int i = 2; i < len; i++){
            if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]){
                dp[i] = dp[i - 1] + 1;
                ans += dp[i];
            }
            // Think about [1, 2, 3, 8, 9, 10]
            // else {
            //     dp[i] = dp[i - 1];
            // }
        }

        return ans;
    }
}