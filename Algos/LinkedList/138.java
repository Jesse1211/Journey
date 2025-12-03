import java.util.HashMap;
import java.util.Map;

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

class Solution {
    public Node copyRandomList(Node head) {
        Map<Node, Node> map = new HashMap<>();

        Node dummyHead = head;

        // 1. 存
        while (head != null) {
            Node newHead = new Node(head.val);
            map.put(head, newHead);
            head = head.next;
        }

        // 2. 链接
        head = dummyHead;
        while (head != null) {
            map.get(head).random = map.get(head.random);
            map.get(head).next = map.get(head.next);
            head = head.next;
        }

        return map.get(dummyHead);
    }
}