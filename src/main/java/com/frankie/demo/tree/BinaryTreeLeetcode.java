package com.frankie.demo.tree;

import com.frankie.demo.utils.TreeNode;

import java.util.LinkedList;
import java.util.List;

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
//        p111();
//        p222();
//        p113();
    }

    private static List<List<Integer>> ans113 = new LinkedList<>();

    private static void p113() {
        TreeNode root = buildRootFor113();
        int sum = 22;
        pathSum(root, sum);
    }

    private static TreeNode buildRootFor113() {
        TreeNode root = new TreeNode(5);
        TreeNode n4 = new TreeNode(4);
        TreeNode n8 = new TreeNode(8);
        TreeNode n11 = new TreeNode(11);
        TreeNode n13 = new TreeNode(13);
        TreeNode n4_2 = new TreeNode(4_2);
        TreeNode n7 = new TreeNode(7);
        TreeNode n2 = new TreeNode(2);
        TreeNode n5 = new TreeNode(5);
        TreeNode n1 = new TreeNode(1);
        root.left = n4;
        root.right = n8;
        n4.left = n11;
        n11.left = n7;
        n11.right = n2;
        n8.left = n13;
        n8.right = n4_2;
        n4_2.left = n5;
        n4_2.right = n1;
        return root;
    }

    private static List<List<Integer>> pathSum(TreeNode root, int sum) {
        dfs(root, sum, new LinkedList<>());
        return ans113;
    }

    private static void dfs(TreeNode root, int sum, LinkedList<Integer> child) {
        if (root == null) return;
        System.out.println(root.val);
        sum -= root.val;
        child.add(root.val);
        if (root.left == null && root.right == null && sum == 0){
            ans113.add(new LinkedList<>(child));
            return;
        }
        dfs(root.left, sum, child);
        dfs(root.right, sum, child);
        child.removeLast();
        sum += root.val;
    }

    // 222. Count Complete Tree Nodes
    private static void p222() {
        TreeNode cur = buildCur();
        int ans = countNodes(cur);
        System.out.println(ans);
    }

    private static TreeNode buildCur() {
        TreeNode n1 = new TreeNode(8);
        TreeNode n2 = new TreeNode(3);
        TreeNode n3 = new TreeNode(6);
        TreeNode n4 = new TreeNode(1);
        TreeNode n5 = new TreeNode(7);
        TreeNode n6 = new TreeNode(3);
        TreeNode n7 = new TreeNode(6);
        TreeNode n8 = new TreeNode(2);
        TreeNode n9 = new TreeNode(3);
        TreeNode n10 = new TreeNode(8);
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n3.left = n6;
        n3.right = n7;
        n4.left = n8;
        n4.right = n9;
        n5.left = n10;
        return n1;
    }

    private static int countNodes(TreeNode root) {
        if (root == null) return 0;
        int h = 0;
        TreeNode node = root;
        while (node.left != null) {
            h++;
            node = node.left;
        }

        int upper = (1 << h) - 1;
        int start = 0, end = upper;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (hasNode(root, mid, h)) {
                start = mid;
            }
            else {
                end = mid;
            }
        }
        if (hasNode(root, end, h)) {
            return upper + end + 1;
        }
        return upper + start + 1;

    }

    public static boolean hasNode(TreeNode root, int idx, int h) {
        int start = 0;
        int end = (1 << h) - 1;
        TreeNode node = root;
        for (int i = 0; i < h; ++i) {
            int mid = start + (end - start) / 2;
            if (mid >= idx) {
                node  = node.left;
                end = mid;
            }
            else {
                node = node.right;
                start = mid;
            }
        }
        return node != null;
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
