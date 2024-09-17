package DataStructure.Design;

import java.util.*;

/*
 * highlight: 希望我可以从失败中汲取教训，不要再犯同样的错误
 */

class Node {
    int val;
    int key;
    Node prev;
    Node next;

    public Node() {
    }

    public Node(int key, int val) {
        this.key = key;
        this.val = val;
    }
}

class LRUCache {
    int capacity;
    int used;
    Map<Integer, Node> map;
    Node head;
    Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.used = 0;
        this.head = new Node();
        this.tail = new Node();
        this.head.next = tail;
        this.tail.prev = head;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            updateLinkedListOrder(map.get(key));
            return map.get(key).val;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.val = value;
            updateLinkedListOrder(node);
        } else {
            if (used == capacity) {
                Node remove = head.next;
                map.remove(remove.key);
                removeNode(head.next);
                used--;
            }

            Node cur = new Node(key, value);
            map.put(key, cur);

            appendToTail(cur);
            used++;
        }
    }

    /**
     * add to the tail
     */
    private void updateLinkedListOrder(Node node) {
        removeNode(node);
        appendToTail(node);
    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void appendToTail(Node node) {
        Node latest = tail.prev;
        latest.next = node;
        node.prev = latest;
        node.next = tail;
        tail.prev = node;
    }
}
/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */