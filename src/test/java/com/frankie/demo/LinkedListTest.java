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
        Node n3 = new Node("3", null);
        Node n2 = new Node("1", n3);
        node    = new Node("2", n2);
    }

    @Test
    public void buildAndPrintNodes(){
        String result = LinkedListUtils.printNode(node);
        System.out.println(result);
    }

    @Test
    public void isContainNode(){
        boolean isContainNode = LinkedListUtils.isContainNode(node, "1");
        System.out.println("isContainNode: " + isContainNode);
    }
}
