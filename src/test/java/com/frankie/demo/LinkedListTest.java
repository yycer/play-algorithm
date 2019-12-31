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

        // 1. 常规:  1 -> 2 -> 2 -> 3
        LinkedListUtils llu1 = new LinkedListUtils("1");
        llu1.addNodeForward("2");
        llu1.addNodeForward("2");
        llu1.addNodeForward("3");

        System.out.println(">>>> 正常情况 >>>>");
        LinkedListUtils.printNode(llu1.head);
        Node result1 = LinkedListUtils.deleteDuplicateNode(llu1.head);
        System.out.println(">>>> 删除重复节点 >>>>");
        LinkedListUtils.printNode(result1);

//        2. 更多:  1 -> 2 -> 2 -> 2 -> 3
        LinkedListUtils llu2 = new LinkedListUtils("1");
        llu2.addNodeForward("2");
        llu2.addNodeForward("2");
        llu2.addNodeForward("2");
        llu2.addNodeForward("3");

        System.out.println(">>>> 正常情况 >>>>");
        LinkedListUtils.printNode(llu2.head);
        Node result2 = LinkedListUtils.deleteDuplicateNode(llu2.head);
        System.out.println(">>>> 删除重复节点 >>>>");
        LinkedListUtils.printNode(result2);

//        3. 首部:  1 -> 1 -> 2
        LinkedListUtils llu3 = new LinkedListUtils("1");
        llu3.addNodeForward("1");
        llu3.addNodeForward("2");

        System.out.println(">>>> 正常情况 >>>>");
        LinkedListUtils.printNode(llu3.head);
        Node result3 = LinkedListUtils.deleteDuplicateNode(llu3.head);
        System.out.println(">>>> 删除重复节点 >>>>");
        LinkedListUtils.printNode(result3);

//      4. 尾部:  1 -> 2 -> 2
        LinkedListUtils llu4 = new LinkedListUtils("1");
        llu4.addNodeForward("2");
        llu4.addNodeForward("2");

        System.out.println(">>>> 正常情况 >>>>");
        LinkedListUtils.printNode(llu4.head);
        Node result4 = LinkedListUtils.deleteDuplicateNode(llu4.head);
        System.out.println(">>>> 删除重复节点 >>>>");
        LinkedListUtils.printNode(result4);

//      5. 多样： 1 -> 2 -> 2 -> 3 -> 3 -> 4
        LinkedListUtils llu5 = new LinkedListUtils("1");
        llu5.addNodeForward("2");
        llu5.addNodeForward("2");
        llu5.addNodeForward("3");
        llu5.addNodeForward("3");
        llu5.addNodeForward("4");

        System.out.println(">>>> 正常情况 >>>>");
        LinkedListUtils.printNode(llu5.head);
        Node result5 = LinkedListUtils.deleteDuplicateNode(llu5.head);
        System.out.println(">>>> 删除重复节点 >>>>");
        LinkedListUtils.printNode(result5);

//        6. 无重:  1 -> 2 -> 3
        LinkedListUtils llu6 = new LinkedListUtils("1");
        llu6.addNodeForward("2");
        llu6.addNodeForward("3");

        System.out.println(">>>> 正常情况 >>>>");
        LinkedListUtils.printNode(llu6.head);
        Node result6 = LinkedListUtils.deleteDuplicateNode(llu6.head);
        System.out.println(">>>> 删除重复节点 >>>>");
        LinkedListUtils.printNode(result6);

//        7. 单个:  1
        LinkedListUtils llu7 = new LinkedListUtils("1");
        System.out.println(">>>> 正常情况 >>>>");
        LinkedListUtils.printNode(llu7.head);
        Node result7 = LinkedListUtils.deleteDuplicateNode(llu7.head);
        System.out.println(">>>> 删除重复节点 >>>>");
        LinkedListUtils.printNode(result7);

//        8. 仅重： 1 -> 1
        LinkedListUtils llu8 = new LinkedListUtils("1");
        llu8.addNodeForward("1");

        System.out.println(">>>> 正常情况 >>>>");
        LinkedListUtils.printNode(llu8.head);
        Node result8 = LinkedListUtils.deleteDuplicateNode(llu8.head);
        System.out.println(">>>> 删除重复节点 >>>>");
        LinkedListUtils.printNode(result8);
    }

    @Test
    public void addNodeForwardTest(){
        LinkedListUtils llu = new LinkedListUtils("1");
        llu.addNodeForward("2");
        llu.addNodeForward("2");
        llu.addNodeForward("3");

//        1 -> 2 -> 2 -> 3
        LinkedListUtils.printNode(llu.head);
    }
}
