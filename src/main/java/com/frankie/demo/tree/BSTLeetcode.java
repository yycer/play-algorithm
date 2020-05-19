package com.frankie.demo.tree;

import com.frankie.demo.utils.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: Yao Frankie
 * @date: 2020/5/18 16:47
 */
public class BSTLeetcode {

    public static void main(String[] args) {
//        p230();
//        p538();
//        p235();
//        p109();
//        p653();
        p501();
    }

    /**
     * 501. Find Mode in Binary Search Tree
     */
    private static void p501() {
        TreeNode n5  = new TreeNode(5);
        TreeNode n31 = new TreeNode(31);
        TreeNode n32 = new TreeNode(32);
        TreeNode n33 = new TreeNode(33);
        TreeNode n1  = new TreeNode(1);
        TreeNode n4  = new TreeNode(4);
        TreeNode n81 = new TreeNode(81);
        TreeNode n82 = new TreeNode(82);
        TreeNode n83 = new TreeNode(83);
        TreeNode n6  = new TreeNode(6);
        TreeNode n12 = new TreeNode(12);

        n5.left   = n31;
        n5.right  = n81;
        n31.left  = n32;
        n31.right = n33;
        n32.left  = n1;
        n33.right = n4;
        n81.left  = n6;
        n81.right = n82;
        n82.left  = n83;
        n82.right = n12;

        int[] ret = findMode(n5);
        System.out.println(Arrays.toString(ret));
    }

    private static int max = 1;
    private static int cur = 1;
    private static  List<Integer> freqNums = new ArrayList<>();
    private static TreeNode preNode = null;

    private static int[] findMode(TreeNode root) {

        getFreqNums(root);

        int size = freqNums.size();
        int[] retArr = new int[size];
        for (int i = 0; i < size; i++){
            retArr[i] = freqNums.get(i);
        }
        return retArr;
    }

    private static void getFreqNums(TreeNode root) {
        if (root == null) return;

        getFreqNums(root.left);

        if (preNode != null){
            if (root.val == preNode.val){
                cur++;
            } else {
                cur = 1;
            }
        }

        if (cur == max){
            freqNums.add(root.val);
        } else if (cur > max){
            max = cur;
            freqNums.clear();
            freqNums.add(root.val);
        }

        preNode = root;
        getFreqNums(root.right);
    }

    private static void p653() {

    }

    /**
     * 109. Convert Sorted List to Binary Search Tree
     */
    private static void p109() {

        ListNode head = new ListNode(-10);
        ListNode n3 = new ListNode(-3);
        ListNode n0 = new ListNode(0);
        ListNode n5 = new ListNode(5);
        ListNode n9 = new ListNode(9);

        head.next = n3;
        n3.next = n0;
        n0.next = n5;
        n5.next = n9;

        sortedListToBST(head);
    }

    private static TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return new TreeNode(head.val);
        ListNode preMidNode = getPreMid(head);
        ListNode midNode = preMidNode.next;
        preMidNode.next = null;
        TreeNode curNode = new TreeNode(midNode.val);
        curNode.left  = sortedListToBST(head);
        curNode.right = sortedListToBST(midNode.next);
        return curNode;
    }

    private static ListNode getPreMid(ListNode head) {
        ListNode fastNode   = head;
        ListNode slowNode   = head;
        ListNode preMidNode = head;

        while (fastNode != null && fastNode.next != null){
            preMidNode = slowNode;
            fastNode   = fastNode.next.next;
            slowNode   = slowNode.next;
        }

        return preMidNode;
    }

    /**
     * 235. Lowest Common Ancestor of a Binary Search Tree
     */
    private static void p235() {
        TreeNode n0 = new TreeNode(0);
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        TreeNode n7 = new TreeNode(7);
        TreeNode n8 = new TreeNode(8);
        TreeNode n9 = new TreeNode(9);

        n4.left  = n3;
        n4.right = n5;
        n2.left  = n0;
        n2.right = n4;
        n8.left  = n7;
        n8.right = n9;
        n6.left  = n2;
        n6.right = n8;
        lowestCommonAncestor(n6, n2, n4);
    }

    private static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root.val > p.val && root.val > q.val){
            return lowestCommonAncestor(root.left, p, q);
        }
        if (root.val < p.val && root.val < q.val){
            return lowestCommonAncestor(root.right, p, q);
        }
        return root;
    }

    /**
     * 538. Convert BST to Greater Tree
     */
    private static void p538() {

        TreeNode node6 = new TreeNode(6);
        TreeNode node3 = new TreeNode(3);
        TreeNode node9 = new TreeNode(9);
        TreeNode node1 = new TreeNode(1);
        TreeNode node5 = new TreeNode(5);
        TreeNode node7 = new TreeNode(7);
        TreeNode node12 = new TreeNode(12);

        node3.left  = node1;
        node3.right = node5;
        node9.left  = node7;
        node9.right = node12;
        node6.left  = node3;
        node6.right = node9;
        convertBST(node6);
    }

    private static TreeNode convertBST(TreeNode root) {
        if (root == null) return null;
        TreeNode curNode = root;
        int sum = 0;
        ArrayDeque<TreeNode> ad = new ArrayDeque<>();
        while (curNode != null || !ad.isEmpty()){
            while (curNode != null){
                ad.push(curNode);
                curNode = curNode.right;
            }
            TreeNode poppedNode = ad.pop();
            sum += poppedNode.val;
            poppedNode.val = sum;
            curNode = poppedNode.left;
        }
        return root;
    }

    /**
     * 230. Kth Smallest Element in a BST
     */
    private static void p230() {

        TreeNode node2 = new TreeNode(2);
        TreeNode node1 = new TreeNode(1);
        TreeNode node4 = new TreeNode(4);
        TreeNode node3 = new TreeNode(3);

        node1.right = node2;
        node3.left  = node1;
        node3.right = node4;

        int k = 3;
        int ret1 = kthSmallest(node3, k);
        System.out.println(ret1);
    }

    private static int kthSmallest(TreeNode root, int k) {
        if (root == null) return 0;
        int count = 0;

        ArrayDeque<TreeNode> ad = new ArrayDeque<>();
        while (root != null || !ad.isEmpty()){
            while (root != null){
                ad.push(root);
                root = root.left;
            }
            TreeNode poppedNode = ad.pop();
            count++;
            if (count == k){
                return poppedNode.val;
            }
            root = poppedNode.right;
        }
        return -1;
    }
}
