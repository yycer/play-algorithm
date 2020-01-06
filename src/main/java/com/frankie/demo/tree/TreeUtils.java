package com.frankie.demo.tree;

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
        } else if (val > curNode.getVal()){
            curNode.setRightNode(addTreeNodeRecursively(curNode.getRightNode(), val));
        } else {
            return curNode;
        }
        return curNode;
    }
}
