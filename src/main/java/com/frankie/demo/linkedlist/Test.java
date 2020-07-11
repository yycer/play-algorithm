package com.frankie.demo.linkedlist;

/**
 * @author: Yao Frankie
 * @date: 2020/7/10 22:41
 */
public class Test {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);

        head = head.next;
        while (head != null){
            System.out.println(head.val);
            head = head.next;
        }
    }
}
