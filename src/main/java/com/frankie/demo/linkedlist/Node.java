package com.frankie.demo.linkedlist;

import lombok.Getter;
import lombok.Setter;

/**
 * @author: Yao Frankie
 * @date: 2019/12/25 15:49
 */
@Setter
@Getter
public class Node {

    private String val;
    private Node   nextNode;

    public Node(String val, Node nextNode){
        this.val      = val;
        this.nextNode = nextNode;
    }
    public Node(){

    }
}
