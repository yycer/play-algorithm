package com.frankie.demo.linkedlist;

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

        while (curNode.getNextNode() != null){
            // 如果当前结点与下一个节点相等。
            if (curNode.getVal().equals(curNode.getNextNode().getVal())){
                String dupValue = curNode.getVal();
                Node   nextNode = curNode.getNextNode();
                // 考虑多个相等节点的情况。
                while (dupValue.equals(nextNode.getVal())){
                    // 若首节点存在重复，
                    if (preNode == null){
                        node = nextNode.getNextNode();
                    } else {
                        preNode.setNextNode(nextNode.getNextNode());
                    }
                    curNode = curNode.getNextNode();
                    nextNode = nextNode.getNextNode();
                }
            }
            // 若不相等，设置preNode，并遍历至下一个节点。
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
        return null;
    }

    // endregion

}
