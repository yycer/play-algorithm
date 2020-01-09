package com.frankie.demo.tree;

import java.util.Stack;

/**
 * @author: Yao Frankie
 * @date: 2019/12/25 15:46
 */
public class TreeUtils {

    public TreeNode root;

    public void addTreeNode(int val){
        root = addTreeNodeRecursively(root, val);
    }

    private TreeNode addTreeNodeRecursively(TreeNode curNode, int val) {
        if (curNode == null){
            return new TreeNode(val);
        }
        if (val < curNode.getVal()){
            curNode.setLeftNode(addTreeNodeRecursively(curNode.getLeftNode(), val));
        }
        else if (val > curNode.getVal()){
            curNode.setRightNode(addTreeNodeRecursively(curNode.getRightNode(), val));
        }
        // Binary search tree has no nodes with same value.
        else {
            return curNode;
        }
        return curNode;
    }

    /**
     * Determine the tree contains a tree node.
     */
    public static boolean containTreeNode(TreeNode root, int val){
        if (val < 0 || root == null) return false;
        if (val == root.getVal())    return true;

        return val < root.getVal() ?
               containTreeNode(root.getLeftNode(), val) :
               containTreeNode(root.getRightNode(), val);
    }

    /**
     * Pre-order traversal using stack and while loop.
     */
    public static void preOrderTraversalUsingStackAndLoop(TreeNode node){
        if (node == null) return;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(node);
        System.out.println("PreOrder traversal using stack and loop: ");

        while (!stack.isEmpty()){

            TreeNode curNode = stack.pop();
            System.out.print(curNode.getVal() + " ");
            if (curNode.getRightNode() != null){
                stack.push(curNode.getRightNode());
            }
            if (curNode.getLeftNode() != null){
                stack.push(curNode.getLeftNode());
            }
        }
    }

    /**
     * Pre-order traversal using recursion.
     */
    public static void preOrderTraversalUsingRecursion(TreeNode node){
        if (node == null) return;

        System.out.print(node.getVal() + " ");
        preOrderTraversalUsingRecursion(node.getLeftNode());
        preOrderTraversalUsingRecursion(node.getRightNode());
    }

    /**
     * In-order traversal using stack and loop.
     */
    public static void inOrderTraversalUsingStackAndLoop(TreeNode node){
        if (node == null) return;
        TreeNode curNode = node;
        Stack<TreeNode> stack = new Stack<>();

        while (curNode != null || !stack.isEmpty()){
            // Step1: Add curNode and left nodes into stack.
            while (curNode != null){
                stack.push(curNode);
                curNode = curNode.getLeftNode();
            }

            // Step2: Pop and print node.
            TreeNode poppedNode = stack.pop();
            System.out.print(poppedNode.getVal() + " ");

            // Step3: Navigate to the right node.
            curNode = poppedNode.getRightNode();
        }
    }

    public static void inOrderTraversalUsingRecursion(TreeNode node){
        if (node == null) return;

        inOrderTraversalUsingRecursion(node.getLeftNode());
        System.out.print(node.getVal() + " ");
        inOrderTraversalUsingRecursion(node.getRightNode());
    }
}
