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
}
