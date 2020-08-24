package com.frankie.demo.heap;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * @author: Yao Frankie
 * @date: 2020/8/22 10:52
 */
public class HeapTest {

    public static void main(String[] args) {
        maxHeapTest();
    }

    private static void maxHeapTest() {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        maxHeap.offer(2);
        maxHeap.offer(1);
        maxHeap.offer(3);
        if (maxHeap.size() > 3) maxHeap.poll();
        maxHeap.offer(4);
        if (maxHeap.size() > 3) maxHeap.poll();
        System.out.println(maxHeap);
        while (maxHeap.size() > 0) {
            System.out.println(maxHeap.poll());
        }
    }
}
