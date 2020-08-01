package com.frankie.demo.graph;

import java.util.Arrays;

/**
 * @author: Yao Frankie
 * @date: 2020/8/1 16:58
 */
public class CourseSchedule {

    private static int    N = 10010;
    private static int[]  e = new int[N >>> 1];
    private static int[] ne = new int[N >>> 1];
    private static int[]  h = new int[N];
    private static int  idx = 0;
    private static int[]  d = new int[N];
    private static int[]  q = new int[N];

    public static void main(String[] args) {
        int n = 2;
        int[][] pre = {{0, 1}, {1, 0}};
        boolean ans = canFinish(n, pre);
        System.out.println(ans);
    }

    private static void add(int a, int b){
        e[idx] = b; ne[idx] = h[a]; h[a] = idx++;
    }

    private static boolean topSort(int n){

        // 初始化链表头尾指针
        int hh = 0, tt = -1;

        // 1. 将入度为0的结点添加到队列中
        for (int i = 0; i < n; i++){
            if (d[i] == 0) q[++tt] = i;
        }

        // 2. 依次遍历队头结点，并将有向边终点结点的入队-1，
        // 如果该结点的入度为0，则添加到队列中
        while (hh <= tt){
            int head = q[hh++];
            for (int i = h[head]; i != -1; i = ne[i]){
                int j = e[i];
                if (--d[j] == 0) q[++tt] = j;
            }
        }

        // 3. 判断是否是否所有结点均入队
        return tt == n - 1;
    }

    public static boolean canFinish(int numCourses, int[][] prerequisites) {

        // 1. 初始化h
        Arrays.fill(h, -1);

        // 2. 构建图，并调整入度数组
        for (int[] p: prerequisites){
            add(p[0], p[1]);
            d[p[1]]++;
        }

        // 3. 构建拓扑序列
        if (topSort(numCourses)) return true;
        else return false;
    }
}
