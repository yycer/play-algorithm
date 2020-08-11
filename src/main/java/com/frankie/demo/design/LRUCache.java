package com.frankie.demo.design;

import java.util.HashMap;

/**
 * @author: Yao Frankie
 * @date: 2020/8/3 19:10
 */
public class LRUCache {

    public class Node{
        public int key;
        public int val;
        public Node left;
        public Node right;

        public Node(int k, int v){
            this.key = k;
            this.val = v;
            this.left  = null;
            this.right = null;
        }
    }

    public LRUCache(int capacity) {
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.right = tail;
        tail.left  = head;
        this.capacity = capacity;
    }

    Node head, tail;
    HashMap<Integer, Node> map = new HashMap<>();
    int capacity;

    public void remove(Node p){
        p.left.right = p.right;
        p.right.left = p.left;
    }

    public void insert(Node p){
        p.right = head.right;
        head.right.left = p;
        p.left  = head;
        head.right = p;
    }


    public int get(int key) {
        // 若哈希表中不包含该key，直接返回-1
        if (!map.containsKey(key)){
            return -1;
        }
        Node p = map.get(key);
        remove(p);
        insert(p);
        return p.val;
    }

    public void put(int key, int value) {
        // 若哈希表中不包含该key，则插入哈希表与双链表中
        if (!map.containsKey(key)){
            // 判断容量
            if (capacity == map.size()){
                Node p = tail.left;
                remove(p);
                map.remove(p.key);
            }
            Node p = new Node(key, value);
            map.put(key, p);
            insert(p);
        } else {
            // 否则修改该节点值，并将其删除，再插到链表头部
            Node p = map.get(key);
            p.val = value;
            remove(p);
            insert(p);
        }
    }
}
