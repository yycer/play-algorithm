package com.frankie.demo.linkedlist;

import java.util.Stack;

/**
 * @author: Yao Frankie
 * @date: 2019/12/25 15:46
 */
public class LinkedListUtils {

    public Node head;

    public LinkedListUtils(String val){
        this.head = new Node(val, null);
    }

    // region Basic

    /**
     * Add node forward.
     */
    public void addNodeForward(String val){
        Node curNode = head;
        if (curNode == null){
            head = new Node(val, null);
        } else {
            // Navigate to the last node.
            while (curNode.getNextNode() != null){
                curNode = curNode.getNextNode();
            }
            curNode.setNextNode(new Node(val, null));
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
     * Count nodes in linked list.
     * eg. 1 -> 2 -> 3 return 3.
     */
    public static int countNodes(Node node){
        Node curNode = node;
        int count = 0;
        while (curNode != null){
            count++;
            curNode = curNode.getNextNode();
        }
        return count;
    }

    /**
     * Build a simple circle linked list.
     */
    public static Node buildCircleLinkedList(Node node, String connectedVal){

        // curNode is used to find the connected and last node.
        Node curNode       = node;
        Node connectedNode = null;

        if (curNode == null){
            return null;
        }

        // Step1: Navigate to to be linked node and the last node.
        while (curNode.getNextNode() != null){
            if (connectedVal.equals(curNode.getVal())){
                connectedNode = curNode;
            }
            curNode = curNode.getNextNode();
        }

        // Step2: Link to be linked node to the last node.
        curNode.setNextNode(connectedNode);
        return node;
    }

    /**
     * Print nodes reversely.
     */
    public static void printNodeReversely(Node node){
        Stack<String> stack = new Stack<>();
        Node        curNode = node;

        // Push val into stack.
        while (curNode != null){
            stack.push(curNode.getVal());
            curNode = curNode.getNextNode();
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()){
            sb.append(stack.pop());
            sb.append(" -> ");
        }
        String result = new String(sb);
        if (result.length() > 4){
            System.out.println("Revered linked list is " + result.substring(0, result.length() - 4));
        }
    }

    /**
     * Print the last k node using minus(count - k).
     * eg. 1 -> 2 -> 3 -> 4 -> 5, if k = 2, print 4.
     */
    public static String printLastKNodeUsingMinus(Node node, int k){
        Node curNode = node;
        // Step1: Count the nodes.
        int count = countNodes(curNode);

        // Step2: Calculate steps you should go.
        if (k < 0 || k > count){
            return null;
        }
        int step = count - k;

        // Step3: Navigate to the last k node.
        while (step > 0){
            curNode = curNode.getNextNode();
            step--;
        }
        return curNode.getVal();
    }

    /**
     * Print the last k node using fast and slow node.
     */
    public static String printLastKNodeUsingFastMode(Node node, int k){

        if (node == null || k <= 0 || k > countNodes(node)){
            return null;
        }

        Node fastNode = node;
        Node slowNode = node;

        // Step1: Fast node move k steps firstly.
        while (k > 0){
            fastNode = fastNode.getNextNode();
            k--;
        }

        // Step2: Slow node stop until fast node goes to the end.
        while (fastNode != null){
            fastNode = fastNode.getNextNode();
            slowNode = slowNode.getNextNode();
        }
        return slowNode.getVal();
    }

    // endregion

    // region Delete node

    /**
     * 删除链表节点，主要分为两种情况
     * 1. 首节点，preNode = null, return node.getNextNode();
     * 2. 其他节点(中间、尾节点)，preNode.setNextNode(curNode.getNextNode()); return node;
     * * 边界情况：若链表中不存在待删除节点。
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

        // 边界情况1：链表中不存在待删除节点。
        if (curNode == null){
            return node;
        }
        // 边界情况2：待删除节点为头结点。
        else if (preNode == null){
            return curNode.getNextNode();
        }
        // 分为两种情况
        // 1. 待删除的节点为中间节点。
        // 2. 待删除的节点为尾节点。
        else {
            preNode.setNextNode(curNode.getNextNode());
            return node;
        }
    }

    /**
     * 在O(1)时间复杂度下删除节点。
     * 边界条件：不存在待删除节点、待删除节点为首、尾节点、中间节点。
     */
    public static Node deleteNodeUsingO1(Node node, String val){
        Node curNode = node;

        // Step1: 定位待删除节点。
        while (curNode != null){
            if (val.equals(curNode.getVal())) break;
            curNode = curNode.getNextNode();
        }

        // Step2: 若链表中无待删除节点。
        if (curNode == null){
            return node;
        }

        // Step3: 若待删除节点为首节点。
        else if (curNode == node){
            return node.getNextNode();
        }

        // Step4: 若待删除节点为尾节点。
        else if (curNode.getNextNode() == null){
            // 定位到前一个节点。
            Node preNode = node;
            while (preNode.getNextNode() != curNode){
                preNode = preNode.getNextNode();
            }
            preNode.setNextNode(null);
        }

        // Step5: 若待删除节点为中间节点。
        else {
            curNode.setVal(curNode.getNextNode().getVal());
            curNode.setNextNode(curNode.getNextNode().getNextNode());
        }
        return node;
    }

    /**
     * 删除有序链表中的重复节点。
     */
    public static Node deleteDuplicateNode(Node node){
        Node curNode = node;
        Node preNode = null;

        // curNode != null prevents 1 -> 2 -> 2
        while (curNode != null && curNode.getNextNode() != null){
            // If current node's value is same to the next node's value.
            if (curNode.getVal().equals(curNode.getNextNode().getVal())){

                int    dupCount = 0;
                String dupValue = curNode.getVal();
                Node   nextNode = curNode.getNextNode();
                // nextNode != null prevents 1 -> 2 -> 2
                while (nextNode != null && dupValue.equals(nextNode.getVal())){
                    // 若首节点存在重复，
                    if (preNode == null){
                        node     = nextNode.getNextNode();
                        nextNode = nextNode.getNextNode();
                        dupCount++;
                        continue;
                    } else {
                        preNode.setNextNode(nextNode.getNextNode());
                        nextNode = nextNode.getNextNode();
                        dupCount++;
                    }
                }
                // Adjust the position of the current node.
                while (dupCount >= 0){
                    curNode = curNode.getNextNode();
                    dupCount--;
                }
            }
            // If it is different, set the previous node and next node.
            else {
                preNode = curNode;
                curNode = curNode.getNextNode();
            }
        }
        return node;
    }

    /**
     * 删除链表中间节点。
     */
    public static Node deleteMiddleNode(Node node){

        // Step1: Navigate to the to be deleted node using fast and slow mode.
        Node fastNode = node;
        Node slowNode = node;

        while (fastNode != null && fastNode.getNextNode() != null){
            fastNode = fastNode.getNextNode().getNextNode();
            slowNode = slowNode.getNextNode();
        }

        // Step2: Delete node using O(1).
        slowNode.setVal(slowNode.getNextNode().getVal());
        slowNode.setNextNode(slowNode.getNextNode().getNextNode());

        return node;
    }

    // endregion

}
