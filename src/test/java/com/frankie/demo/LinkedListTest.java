package com.frankie.demo;

import com.frankie.demo.linkedlist.LinkedListUtils;
import com.frankie.demo.linkedlist.Node;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author: Yao Frankie
 * @date: 2019/12/25 15:55
 */
@SpringBootTest
public class LinkedListTest {
    public static Node node;

    static {
        // 1 -> 3 -> 5 -> 2 -> 7
        Node n1 = new Node("7", null);
        Node n2 = new Node("2", n1);
        Node n3 = new Node("5", n2);
        Node n4 = new Node("3", n3);
        node    = new Node("1", n4);
    }

    @Test
    public void buildAndPrintNodes(){
        LinkedListUtils.printNode(node);
    }

    @Test
    public void isContainNode(){
        boolean isContainNode = LinkedListUtils.isContainNode(node, "1");
        System.out.println("isContainNode: " + isContainNode);
    }

    @Test
    public void deleteNode(){

        System.out.println(">>>> 初始Node >>>>");
//        >>>> 初始Node >>>>
//        1 -> 3 -> 5 -> 2 -> 7
        LinkedListUtils.printNode(node);

        System.out.println(">>>> 删除首节点 >>>>");
//        >>>> 删除首节点 >>>>
//        3 -> 5 -> 2 -> 7
        Node processedNode1 = LinkedListUtils.deleteNode(LinkedListTest.node, "1");
        LinkedListUtils.printNode(processedNode1);

        System.out.println(">>>> 删除中间节点 >>>>");
//        >>>> 删除中间节点 >>>>
//        1 -> 3 -> 2 -> 7
        Node processedNode2 = LinkedListUtils.deleteNode(LinkedListTest.node, "5");
        LinkedListUtils.printNode(processedNode2);

        System.out.println(">>>> 删除尾节点 >>>>");
//        >>>> 删除尾节点 >>>>
//        1 -> 3 -> 5 -> 2
        Node processedNode3 = LinkedListUtils.deleteNode(LinkedListTest.node, "7");
        LinkedListUtils.printNode(processedNode3);

        System.out.println(">>>> 边界条件: 删除不存在节点 >>>>");
//        >>>> 删除不存在节点 >>>>
//        1 -> 3 -> 5 -> 2 -> 7
        Node processedNode4 = LinkedListUtils.deleteNode(LinkedListTest.node, "4");
        LinkedListUtils.printNode(processedNode4);

    }

    @Test
    public void addValTest(){
        LinkedListUtils.printNode(node);
        Node node = LinkedListUtils.addNode(LinkedListTest.node, "10");
        LinkedListUtils.printNode(node);
    }

    @Test
    public void deleteNodeUsingO1Test(){
        System.out.println(">>>> 初始Node >>>>");
//        >>>> 初始Node >>>>
//        1 -> 3 -> 5 -> 2 -> 7
        LinkedListUtils.printNode(node);

        System.out.println(">>>> 删除不存在节点 >>>>");
//        >>>> 删除不存在节点 >>>>
//        1 -> 3 -> 5 -> 2 -> 7
        Node processedNode1 = LinkedListUtils.deleteNodeUsingO1(LinkedListTest.node, "4");
        LinkedListUtils.printNode(processedNode1);

        System.out.println(">>>> 删除首节点 >>>>");
//        >>>> 删除首节点 >>>>
//        3 -> 5 -> 2 -> 7
        Node processedNode2 = LinkedListUtils.deleteNodeUsingO1(LinkedListTest.node, "1");
        LinkedListUtils.printNode(processedNode2);

        System.out.println(">>>> 删除尾节点 >>>>");
//        >>>> 删除尾节点 >>>>
//        1 -> 3 -> 5 -> 2
        Node processedNode3 = LinkedListUtils.deleteNodeUsingO1(LinkedListTest.node, "7");
        LinkedListUtils.printNode(processedNode3);
//
        System.out.println(">>>> 删除中间节点 >>>>");
//        >>>> 删除中间节点 >>>>
//        1 -> 3 -> 2 -> 7
        Node processedNode4 = LinkedListUtils.deleteNodeUsingO1(LinkedListTest.node, "5");
        LinkedListUtils.printNode(processedNode4);
    }

    @Test
    public void deleteNodeInSortedLinkedListTest(){
        Node node1 = new Node("2", null);
        Node node2 = new Node("1", node1);
        Node node = new Node("1", node2);
//        Node node4 = new Node("2", node3);
//        Node node  = new Node("1", node4);

        /**
         * 1. 常规:  1 -> 2 -> 2 -> 3
         * 2. 更多:  1 -> 2 -> 2 -> 2 -> 3
         * 3. 首部:  1 -> 1 -> 2
         * 4. 尾部:  1 -> 2 -> 2
         * 5. 多样： 1 -> 2 -> 2 -> 3 -> 3 -> 4
         * 6. 无重:  1 -> 2 -> 3
         * 7. 单个:  1
         * 8. 仅重： 1 -> 1
         */
        System.out.println(">>>> 正常情况 >>>>");
        LinkedListUtils.printNode(node);
        Node result = LinkedListUtils.deleteDuplicateNode(node);
        System.out.println(">>>> 删除重复节点 >>>>");
        LinkedListUtils.printNode(result);
    }

}
