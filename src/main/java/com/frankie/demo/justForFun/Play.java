package com.frankie.demo.justForFun;

/**
 * @author: Yao Frankie
 * @date: 2020/5/26 9:44
 */
public class Play {

    public static void main(String[] args) {
        p509();
    }

    private static void p509() {
        int N = 4;
        int ret1 = fib(N);
        System.out.println(ret1);
    }

    // 0, 1, 1, 2, 3
    // f(3) = f(1) + f(2) = 1 + 1 = 2
    // f(2) = f(0) + f(1) = 0 + 1 = 1
    public static int fib(int N) {
        if (N <= 0) return 0;
        int a = 0, b = 1;
        while (--N > 0){
            int t = b;
            b = a + b;
            a = t;
        }
        return b;
    }


}
