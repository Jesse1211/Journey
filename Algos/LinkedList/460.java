import java.util.HashMap;
import java.util.Map;

class Node {
    Node prev;
    Node next;
    int key;
    int val;
    int freq;

    Node(int key, int val) {
        this.key = key;
        this.val = val;
        this.freq = 1;
    }
}

class DoubleLinkedList {
    Node head;
    Node tail;
    int size;

    DoubleLinkedList() {
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    void add(Node cur) {
        Node next = head.next;
        head.next = cur;
        cur.prev = head;
        cur.next = next;
        next.prev = cur;
        size++;
    }

    void remove(Node cur) {
        cur.prev.next = cur.next;
        cur.next.prev = cur.prev;
        size--;
    }
}

class LFUCache {
    int capacity;
    Map<Integer, Node> keyMap;
    Map<Integer, DoubleLinkedList> freqMap;
    int minFreq;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        keyMap = new HashMap<>();
        freqMap = new HashMap<>();
        minFreq = 0;
    }

    public int get(int key) {
        if (!keyMap.containsKey(key)) {
            return -1;
        }

        Node cur = keyMap.get(key);
        updateFreq(cur);
        return cur.val;
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }

        if (keyMap.containsKey(key)) {
            Node cur = keyMap.get(key);
            cur.val = value;
            updateFreq(cur);
        } else {
            if (keyMap.size() >= capacity) {
                DoubleLinkedList leastFreqList = freqMap.get(minFreq);
                keyMap.remove(leastFreqList.tail.prev.key);
                leastFreqList.remove(leastFreqList.tail.prev);
            }

            this.minFreq = 1;
            Node cur = new Node(key, value);
            keyMap.put(key, cur);
            freqMap.computeIfAbsent(minFreq, k -> new DoubleLinkedList()).add(cur);

        }
    }

    private void updateFreq(Node cur) {
        DoubleLinkedList list = freqMap.get(cur.freq);
        list.remove(cur);

        if (cur.freq == minFreq && list.size == 0) {
            minFreq++;
        }

        cur.freq++;
        freqMap.computeIfAbsent(cur.freq, k -> new DoubleLinkedList()).add(cur);
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
