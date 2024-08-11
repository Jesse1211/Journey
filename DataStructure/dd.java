package DataStructure;

import java.util.*;

// Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.

// Implement the LRUCache class:

// LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
// int get(int key) Return the value of the key if the key exists, otherwise return -1.
// void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
// The functions get and put must each run in O(1) average time complexity.

// Example 1:

// Input
// ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
// [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
// Output
// [null, null, null, 1, null, -1, null, -1, 3, 4]

// Explanation
// LRUCache lRUCache = new LRUCache(2);
// lRUCache.put(1, 1); // cache is {1=1}
// lRUCache.put(2, 2); // cache is {1=1, 2=2}
// lRUCache.get(1);    // return 1
// lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
// lRUCache.get(2);    // returns -1 (not found)
// lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
// lRUCache.get(1);    // return -1 (not found)
// lRUCache.get(3);    // return 3
// lRUCache.get(4);    // return 4

class Solution {
    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1); // cache is {1=1}
        lRUCache.put(2, 2); // cache is {1=1, 2=2}
        lRUCache.get(1); // return 1
        lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
        lRUCache.get(2); // returns -1 (not found)
        lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
        lRUCache.get(1); // return -1 (not found)
        lRUCache.get(3); // return 3
        lRUCache.get(4); // return 4
    }
}

class Linkedlist {
    int val;
    Linkedlist next;
    Linkedlist prev;

    public Linkedlist() {

    }

    public Linkedlist(int val) {
        this.val = val;
    }

    public void next(Linkedlist Linkedlist) {
        this.next = Linkedlist;
    }

    public void prev(Linkedlist Linkedlist) {
        this.prev = Linkedlist;
    }
}

class LRUCache {
    Map<Integer, Integer> keyToVal; // key : value
    Map<Integer, Linkedlist> keyToNode; // key : node
    Linkedlist[] keys; // store the order of key
    Linkedlist head;
    Linkedlist tail;
    int size, capacity;

    public LRUCache(int capacity) {
        size = 0;
        this.capacity = capacity;
        keys = new Linkedlist[capacity];
        keyToVal = new HashMap<>();
        keyToNode = new HashMap<>();
        head = new Linkedlist(0);
        tail = new Linkedlist(0);

        head.prev = null; // head: older; tail: newest
        head.next = tail;
        tail.prev = head;
        tail.next = null;
    }

    public int get(int key) {
        if (!keyToNode.containsKey(key)) {
            return -1;
        }

        // update it
        Linkedlist cur = keyToNode.get(key);
        // change the order

        Linkedlist last = cur.prev;
        Linkedlist next = cur.next;
        last.next = next;
        next.prev = last;

        // add cur to the tail
        Linkedlist beforeTail = tail.prev;
        beforeTail.next = cur;
        cur.next = tail;
        tail.prev = cur;

        return keyToVal.get(key);
    }

    public void put(int key, int value) {
        if (keyToVal.containsKey(key)) {
            keyToVal.put(key, value);
            Linkedlist cur = keyToNode.get(key);

            // update valye
            cur.val = value;
            // change the order
            Linkedlist last = cur.prev;
            Linkedlist next = cur.next;
            last.next = next;
            next.prev = last;

            // add cur to the tail
            Linkedlist beforeTail = tail.prev;
            beforeTail.next = cur;
            cur.next = tail;
            tail.prev = cur;
            return;
        }

        if (size == capacity) {
            // find oldest
            Linkedlist oldest = head.next;

            // remove
            keyToVal.remove(oldest.val);
            keyToNode.remove(oldest.val);

            // remove from linkedlist
            Linkedlist secOldest = oldest.next;
            head.next = secOldest;
            secOldest.prev = head;

            // add the newest
            Linkedlist cur = new Linkedlist(value);
            keyToVal.put(key, value);
            keyToNode.put(key, cur);

            Linkedlist beforeTail = tail.prev;
            beforeTail.next = cur;
            cur.next = tail;
            tail.prev = cur;
            return;
        }

        keyToVal.put(key, value);
        Linkedlist cur = new Linkedlist(value);
        keyToNode.put(key, cur);

        // add cur to the tail
        Linkedlist beforeTail = tail.prev;
        beforeTail.next = cur;
        cur.next = tail;
        tail.prev = cur;
        return;
    }
}
