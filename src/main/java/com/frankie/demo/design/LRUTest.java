package com.frankie.demo.design;

/**
 * @author: Yao Frankie
 * @date: 2020/8/3 19:12
 */
public class LRUTest {

    public static void main(String[] args) {
        LRUCache lru = new LRUCache(1);
        lru.put(2, 1);
        int ans1 = lru.get(2);
        System.out.println(ans1);
        lru.put(3, 2);
        int ans2 = lru.get(2);
        System.out.println(ans2);
        int ans4 = lru.get(3);
        System.out.println(ans4);
    }
}
