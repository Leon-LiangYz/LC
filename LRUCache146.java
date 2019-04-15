package DesignRelated;

/*
Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

Follow up:
Could you do both operations in O(1) time complexity?

Example:

LRUCache cache = new LRUCache( 2 /* capacity */

//import org.junit.Test;

import java.util.HashMap;

//        cache.put(1, 1);
//        cache.put(2, 2);
//        cache.get(1);       // returns 1
//        cache.put(3, 3);    // evicts key 2
//        cache.get(2);       // returns -1 (not found)
//        cache.put(4, 4);    // evicts key 1
//        cache.get(1);       // returns -1 (not found)
//        cache.get(3);       // returns 3
//        cache.get(4);       // returns 4
//
//*/

class Node {
    int key;
    int value;
    Node next;
    Node pre;
    public Node (int key, int value) {
        this.key = key;
        this.value = value;
        pre = null;
        next = null;
    }
}
public class LRUCache146 {


    private HashMap<Integer, Node> map;
    public int capacity;
    private Node head;
    private Node tail;

    public LRUCache146 (int capacity) {
        map = new HashMap<>();
        this.capacity = capacity;
        head = null;
        tail = null;
    }

    public int get (int key) {
        Node node = map.get(key);
        if (node == null) return -1;
        if (node != tail) {
            if (node == head) {
                head = head.next;
            } else {
                node.pre.next = node.next;
                node.next.pre = node.pre;
            }
        }
        tail.next = node;
        node.pre = tail;
        tail = node;
        node.next = null;

        return node.value;
    }

    public void put (int key, int value) {
        Node node = map.get(key);
        if (node != null) {
            node.value = value;
            if (node != tail) {
                if (node == head) {
                    head = head.next;
                } else {
                    node.pre.next = node.next;
                    node.next.pre = node.pre;
                }
            }
            tail.next = node;
            node.pre = tail;
            tail = node;
            node.next = null;

        } else {
            Node newNode = new Node(key, value);
            if (capacity == 0) {
                Node temp = head;
                head = head.next;
                map.remove(temp);
                capacity++;
            }
            if (head == null && tail == null) {
                head = newNode;
            } else {
                tail.next = newNode;
                newNode.pre = tail;
                newNode.next = null;
            }
            tail = newNode;
            map.put(key, newNode);
            capacity--;
        }
    }

//    @Test
//    public void test1 () {
//        LRUCache146 lc146 = new LRUCache146(3);
//        System.out.println(lc146.get(3));
//        lc146.put(1,99);
//        lc146.put(2,7);
//        lc146.put(1,99);
//        lc146.put(1,13);
//        System.out.println(lc146.get(2));
//    }
    public static void main (String[] args) {
        LRUCache146 lc146 = new LRUCache146(3);
        System.out.println(lc146.get(3));
        lc146.put(1,99);
        lc146.put(2,7);
        lc146.put(1,99);
        lc146.put(1,13);
        System.out.println(lc146.get(2));
    }



}
