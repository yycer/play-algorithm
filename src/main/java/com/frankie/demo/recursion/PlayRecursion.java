package com.frankie.demo.recursion;

/**
 * @author: Yao Frankie
 * @date: 2020/5/22 15:27
 */
public class PlayRecursion {

    public static void main(String[] args) {
        calAge();
    }

    /**
     * Calculate my age.
     */
    private static void calAge() {
        int ret1 = doCalAge(2020);
        System.out.println(ret1);
    }

    private static int doCalAge(int year) {
        if (year == 1996){
            return 0;
        }
        return doCalAge(year - 1) + 1;
    }
}
