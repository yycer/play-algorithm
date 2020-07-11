package com.frankie.demo.linkedlist;

/**
 * @author: Yao Frankie
 * @date: 2020/7/10 22:41
 */
public class ListNode {

    public int val;
    public ListNode next;

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
