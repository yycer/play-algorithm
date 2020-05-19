package com.frankie.demo.tree;

import com.frankie.demo.utils.TreeNode;

import java.util.ArrayDeque;

/**
 * @author: Yao Frankie
 * @date: 2020/5/18 16:47
 */
public class BSTLeetcode {

    public static void main(String[] args) {
//        p230();
//        p538();
        p235();
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
