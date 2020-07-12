package com.frankie.demo.linkedlist;

/**
 * @author: Yao Frankie
 * @date: 2020/7/11 17:08
 */
public class LC143 {

    public static void main(String[] args) {

        ListNode head = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        head.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;

        ListNode fast = head;
        ListNode slow = head;
        ListNode pre  = null;
        while (fast != null && fast.next != null){
            pre  = slow;
            fast = fast.next.next;
            slow = slow.next;
        }

        pre.next = null;
        fast = head;
        slow = reverse(slow);

        while (slow != null){
            ListNode fastNext = fast.next;
            fast.next = slow;
            fast = fastNext;
            ListNode slowNode = slow.next;
            slow.next = fastNext;
            slow = slowNode;
        }
        System.out.println(head);
    }

    private static ListNode reverse(ListNode head){
        ListNode ans = null;
        while (head != null){
            ListNode next = head.next;
            head.next = ans;
            ans = head;
            head = next;
        }
        return ans;
    }
}
