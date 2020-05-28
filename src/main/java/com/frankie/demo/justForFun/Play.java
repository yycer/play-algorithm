package com.frankie.demo.justForFun;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author: Yao Frankie
 * @date: 2020/5/26 9:44
 */
public class Play {

    public static void main(String[] args) {
//        p509();
        p118();
    }

    /**
     * 118. Pascal's Triangle
     */
    private static void p118() {
        int numRows = 5;
//        List<List<Integer>> ans = generate(numRows);
        List<List<Integer>> ans = generateOptimize1(numRows);
        for (List<Integer> child: ans){
            System.out.println(child);
        }
    }

    private static List<List<Integer>> generate(int numRows) {

        List<List<Integer>> ans = new ArrayList<>();
        if (numRows == 0) return ans;
        if (numRows == 1){
            ans.add(Collections.singletonList(1));
            return ans;
        }
        if (numRows == 2){
            ans.add(Arrays.asList(1));
            ans.add(Arrays.asList(1, 1));
            return ans;
        }
        ans.add(Arrays.asList(1));
        ans.add(Arrays.asList(1, 1));
        for (int i = 3; i <= numRows; i++){
            ArrayList<Integer> tmpList = new ArrayList<>();
            tmpList.add(1);
            for (int j = 1; j < i - 1; j++){
                tmpList.add(ans.get(i - 2).get(j - 1) + ans.get(i - 2).get(j));
            }
            tmpList.add(1);
            ans.add(tmpList);
        }
        return ans;
    }

    private static List<List<Integer>> generateOptimize1(int numRows) {

        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < numRows; i++){
            ArrayList<Integer> tmp = new ArrayList<>(Arrays.asList(1));
            // Think about the second layer: [1, 1]
            for (int j = 1; j < i; j++){
                // a[i][j] = a[i-1][j-1] + a[i-1][j]
                tmp.add(ans.get(i - 1).get(j - 1) + ans.get(i - 1).get(j));
            }
            // Think about the first layer: [1]
            if (i > 0) tmp.add(1);
            ans.add(tmp);
        }
        return ans;
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
