package com.frankie.demo.stack;

import java.util.ArrayDeque;

/**
 * @author: Yao Frankie
 * @date: 2020/8/1 16:44
 */
public class StackSort {

    private static ArrayDeque<Integer> s1 = new ArrayDeque<>();
    private static ArrayDeque<Integer> s2 = new ArrayDeque<>();

    public static void main(String[] args) {

        init();
        while (!s1.isEmpty()){
            int t = s1.pop();
            while (!s2.isEmpty() && s2.peek() > t){
                s1.push(s2.pop());
            }
            s2.push(t);
        }
        print();
    }

    private static void print() {
        while (!s2.isEmpty()){
            System.out.println(s2.pop());
        }
    }

    private static void init() {
        s1.push(3);
        s1.push(10);
        s1.push( 8);

        s2.push(1);
        s2.push(4);
        s2.push(5);
        s2.push(8);
        s2.push(12);
    }
}
