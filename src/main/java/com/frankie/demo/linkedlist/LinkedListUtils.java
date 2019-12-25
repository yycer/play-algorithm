package com.frankie.demo.linkedlist;

/**
 * @author: Yao Frankie
 * @date: 2019/12/25 15:46
 */
public class LinkedListUtils {

    /**
     * 打印链表。
     */
    public static void printNode(Node node){
        StringBuilder sb = new StringBuilder();
        String result;
        while (node != null){
            sb.append(node.getVal());
            sb.append(" -> ");
            node = node.getNextNode();
        }
        String sbStr = new String(sb);
        int length = sbStr.length();
        if (length > 2){
            result = sbStr.substring(0, length - 4);
        } else {
            result = sbStr;
        }
        System.out.println(result);
    }

    /**
     * 判断链表中是否包含某个节点。
     */
    public static boolean isContainNode(Node node, String val){
        while (node != null){
            if (val.equals(node.getVal())){
                return true;
            }
            node = node.getNextNode();
        }
        return false;
    }

    /**
     * 删除链表节点，主要分为两种情况
     * 1. 首节点，preNode = null, return node.getNextNode();
     * 2. 其他节点(中间、尾节点)，preNode.setNextNode(curNode.getNextNode()); return node;
     */
    public static Node deleteNode(Node node, String val){
        Node curNode = node;
        Node preNode = null;

        // 定位当前节点与前一个节点。
        while (curNode != null){
            if (val.equals(curNode.getVal())) break;
            preNode = curNode;
            curNode = curNode.getNextNode();
        }

        // 特殊情况，删除节点为头结点。
        if (preNode == null){
            return curNode.getNextNode();
        }
        // 分为两种情况
        // 1. 删除的节点为中间节点。
        // 2. 删除的节点为尾节点。
        else {
            preNode.setNextNode(curNode.getNextNode());
            return node;
        }
    }

    public static Node addNode(Node node, String val){
        if (node == null){
            return new Node(val, null);
        }
        Node curNode = node;
        // 定位到最后一个节点。
        while (curNode.getNextNode() != null){
            curNode = curNode.getNextNode();
        }
        curNode.setNextNode(new Node(val, null));
        return node;
    }
}
