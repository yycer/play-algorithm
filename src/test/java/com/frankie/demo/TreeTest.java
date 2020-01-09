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
        tu.addTreeNode(4);
        tu.addTreeNode(2);
        tu.addTreeNode(6);
        tu.addTreeNode(3);
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

    @Test
    public void preOrderTraversalUsingStackAndLoopTest(){
        TreeUtils tu = new TreeUtils();
        tu.addTreeNode(4);
        tu.addTreeNode(2);
        tu.addTreeNode(6);
        tu.addTreeNode(1);
        tu.addTreeNode(3);
        tu.addTreeNode(5);
        tu.addTreeNode(7);
        BinaryTreePrinter.printNode(tu.root);

        TreeUtils.preOrderTraversalUsingStackAndLoop(tu.root);
    }

    @Test
    public void preOrderTraversalUsingRecursionTest(){
        TreeUtils tu = new TreeUtils();
        tu.addTreeNode(4);
        tu.addTreeNode(2);
        tu.addTreeNode(6);
        tu.addTreeNode(1);
        tu.addTreeNode(3);
        tu.addTreeNode(5);
        tu.addTreeNode(7);
        BinaryTreePrinter.printNode(tu.root);

        System.out.println("PreOrder traversal using recursion: ");
        TreeUtils.preOrderTraversalUsingRecursion(tu.root);
    }

    @Test
    public void inOrderTraversalUsingStackAndLoopTest(){
        TreeUtils tu = new TreeUtils();
        tu.addTreeNode(4);
        tu.addTreeNode(2);
        tu.addTreeNode(6);
        tu.addTreeNode(1);
        tu.addTreeNode(3);
        tu.addTreeNode(5);
        tu.addTreeNode(7);
        BinaryTreePrinter.printNode(tu.root);

        System.out.println("InOrder traversal using stack and loop: ");
        TreeUtils.inOrderTraversalUsingStackAndLoop(tu.root);
    }

    @Test
    public void inOrderTraversalUsingRecursionTest(){
        TreeUtils tu = new TreeUtils();
        tu.addTreeNode(4);
        tu.addTreeNode(2);
        tu.addTreeNode(6);
        tu.addTreeNode(1);
        tu.addTreeNode(3);
        tu.addTreeNode(5);
        tu.addTreeNode(7);
        BinaryTreePrinter.printNode(tu.root);

        System.out.println("InOrder traversal using recursion: ");
        TreeUtils.inOrderTraversalUsingRecursion(tu.root);
    }
}
