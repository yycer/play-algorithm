package com.frankie.demo.linkedlist;

/**
 * @author: Yao Frankie
 * @date: 2020/7/11 15:45
 */
public class LC82 {

    public static void main(String[] args) {

        ListNode head = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(3);
        ListNode l5 = new ListNode(4);
        ListNode l6 = new ListNode(4);
        ListNode l7 = new ListNode(5);
        head.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        l5.next = l6;
        l6.next = l7;

        ListNode dummy = new ListNode(Integer.MAX_VALUE);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = head;

        while (cur != null && cur.next != null){
            if (cur.val != pre.val && cur.val != cur.next.val){
                cur = cur.next;
                pre = pre.next;
            } else {
                int preVal = cur.val;
                cur = cur.next;
                while (cur != null && cur.next != null){
                    if (cur.val == preVal || cur.val == cur.next.val){
                        preVal = cur.val;
                        cur = cur.next;
                    } else {
                        pre.next = cur;
                        break;
                    }
                }
                if (cur.val != preVal && cur.next == null){
                    pre.next = cur;
                }
            }
        }
        System.out.println(dummy);
    }
}
