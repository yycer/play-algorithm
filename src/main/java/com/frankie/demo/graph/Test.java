package com.frankie.demo.graph;

import java.util.Arrays;

/**
 * @author: Yao Frankie
 * @date: 2020/7/31 21:26
 */
public class Test {

    private static int N = 100;
    private static int[] h  = new int[N];
    private static int[] e  = new int[N >>> 1];
    private static int[] ne = new int[N >>> 1];
    private static int  idx = 0;

    public static void main(String[] args) {

        /**
         * 1: -> 3 -> 4 -> 6 -> -1
         * 2: -> 6 -> 5 -> 4 -> -1
         * 3: -> 6 -> 5 -> -1
         * 4: -> 6 -> -1
         * 5: -> 1 -> -1
         * 6: -> -1
         */
        init();
        buildGraph();
        boolean a1 = connect(3, 6);
        boolean a2 = connect(6, 5);
        boolean a3 = connect(5, 4);
        boolean a4 = connect(1, 3);
        boolean a5 = connect(1, 5);
        boolean a6 = connect(1, 6);
        System.out.println(a1);
        System.out.println(a2);
        System.out.println(a3);
        System.out.println(a4);
        System.out.println(a5);
        System.out.println(a6);
    }

    private static void init() {
        Arrays.fill(h, -1);
    }

    private static boolean connect(int a, int b) {
        for (int i = h[a]; i != -1; i = ne[i]){
            if (e[i] == b) return true;
        }
        return false;
    }

    private static void buildGraph() {
        add(1, 3);
        add(1, 4);
        add(1, 6);
        add(2, 6);
        add(2, 5);
        add(2, 4);
        add(3, 6);
        add(3, 5);
        add(4, 6);
        add(5, 1);
    }

    private static void add(int a, int b) {
        e[idx] = b; ne[idx] = h[a]; h[a] = idx++;
    }
}
