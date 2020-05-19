package com.frankie.demo.tree;

/**
 * @author: Yao Frankie
 * @date: 2020/5/19 13:30
 */
public class ListNode {

    public int val;
    public ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
