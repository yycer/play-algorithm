package com.frankie.demo;

import com.frankie.demo.tree.BinaryTreePrinter;
import com.frankie.demo.tree.TreeUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author: Yao Frankie
 * @date: 2020/1/6 9:44
 */
@SpringBootTest
public class TreeTest {

    @Test
    public void addAndPrintTreeNodeTest(){
        TreeUtils tu = new TreeUtils();
        tu.addTreeNode(5);
        tu.addTreeNode(3);
        tu.addTreeNode(8);
        tu.addTreeNode(4);
        BinaryTreePrinter.printNode(tu.root);
    }

    @Test
    public void addSameTreeNodeTest(){
        TreeUtils tu = new TreeUtils();
        tu.addTreeNode(5);
        tu.addTreeNode(3);
        tu.addTreeNode(7);
        tu.addTreeNode(3);
        BinaryTreePrinter.printNode(tu.root);
    }

    @Test
    public void containTreeNodeTest(){
        TreeUtils tu = new TreeUtils();
        tu.addTreeNode(5);
        tu.addTreeNode(3);
        tu.addTreeNode(7);
        tu.addTreeNode(4);
        BinaryTreePrinter.printNode(tu.root);

        // true
        boolean contain4 = TreeUtils.containTreeNode(tu.root, 4);

        // false
        boolean contain2 = TreeUtils.containTreeNode(tu.root, 2);

    }
}
