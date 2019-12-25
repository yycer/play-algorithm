package com.frankie.demo.linkedlist;

/**
 * @author: Yao Frankie
 * @date: 2019/12/25 15:46
 */
public class LinkedListUtils {

    /**
     * 打印链表。
     */
    public static String printNode(Node node){
        StringBuilder sb = new StringBuilder();
        while (node != null){
            sb.append(node.getVal());
            sb.append(" -> ");
            node = node.getNextNode();
        }
        String result = new String(sb);
        int length = result.length();
        if (length > 2){
            return result.substring(0, length - 4);
        } else {
            return result;
        }
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
}
