package com.frankie.demo.tree;

import lombok.Getter;
import lombok.Setter;

/**
 * @author: Yao Frankie
 * @date: 2020/1/6 9:26
 */
@Setter
@Getter
public class TreeNode {
    public int val;
    private TreeNode leftNode;
    private TreeNode rightNode;
    private TreeNode parentNode;

    public TreeNode(int val){
        this.val  = val;
        leftNode  = null;
        rightNode = null;
    }
}
