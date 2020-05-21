package com.frankie.demo.tree;

import com.frankie.demo.utils.TreeNode;

import java.awt.*;

/**
 * @author: Yao Frankie
 * @date: 2020/5/20 13:08
 */
public class BinaryTreeLeetcode {

    private static TreeNode root;

    static {
        root = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
//        TreeNode n7 = new TreeNode(7);

        root.left  = n2;
        root.right = n3;
        n2.left    = n4;
        n2.right   = n5;
        n5.left    = n6;
//        n5.right   = n7;
    }



    public static void main(String[] args) {
//        p110();
//        p104();
//        p543();
        p111();
    }

    /**
     * 111. Minimum Depth of Binary Tree
     */
    private static void p111() {
        TreeNode root = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);

        root.left  = n2;
        root.right = n3;
        n2.left    = n4;
        n2.right   = n5;
        int ret1 = minDepth(root);
        System.out.println(ret1);
    }

    private static int minDepth(TreeNode root) {
        if (root == null) return 0;

        int left  = minDepth(root.left);
        int right = minDepth(root.right);
        return Math.min(left, right) + 1;
    }

    /**
     * 104. Maximum Depth of Binary Tree
     */
    private static void p104() {

        int ret1 = maxDepth(root);
        System.out.println(ret1);
    }

    private static int maxDepth(TreeNode root) {
        if (root == null) return 0;
        int left  = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left, right) + 1;
    }

    /**
     * 543. Diameter of Binary Tree
     */
    private static void p543() {


        int ret1 = diameterOfBinaryTree(root);
        System.out.println(ret1);
    }

    public static int diameterOfBinaryTree(TreeNode root) {
        return getMaxDepth(root);
    }

    private static int getMaxDepth(TreeNode root){
        if (root == null) return 0;

        int left  = getMaxDepth(root.left);
        int right = getMaxDepth(root.right);

        return Math.max(left, right) + 1;
    }

    /**
     * 110. Balanced Binary Tree
     */
    private static void p110() {
        TreeNode root = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        TreeNode n7 = new TreeNode(7);

        root.left  = n2;
        root.right = n3;
        n2.left    = n4;
        n2.right   = n5;
        n5.left    = n6;
        n5.right   = n7;
//        depthTest(root, 1);
        isBalanced(root);
    }

    private static boolean ans = true;

    private static boolean isBalanced(TreeNode root) {
        countDepth(root);
        return ans;
    }

    private static int maxDepth = Integer.MIN_VALUE;

    private static int countDepth(TreeNode root){
        if (root == null) return 0;

        int left  = countDepth(root.left);
        int right = countDepth(root.right);

        if (Math.abs(left - right) > 1){
            ans = false;
        }

        return Math.max(left, right) + 1;
    }

    private static void depthTest(TreeNode root, int depth) {
        if (root == null){
            return;
        }

        depthTest(root.left,  depth + 1);
        depthTest(root.right, depth + 1);
        System.out.println("Current root: " + root.val + ", depth is " + depth);
    }
}
