
class Node {
    public int val;
    public Node next;
    public Node random;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _next, Node _random) {
        val = _val;
        next = _next;
        random = _random;
    }
};

class Solution {
    public Node copyRandomList(Node head) {
        Node iter = head;
        Node next = null;

        // 让iter.next 指向copy
        // copy.next 指向下一个node
        while (iter != null) {
            next = iter.next;

            Node copy = new Node(iter.val);
            iter.next = copy;
            copy.next = next;

            iter = next;
        }

        iter = head;
        while (iter != null) {
            if (iter.random != null) {
                // iter.next 就是copy
                // iter.random.next 才是copy的node
                iter.next.random = iter.random.next;
            }
            iter = iter.next.next;
        }

        iter = head;
        Node dummy = new Node(0);
        Node copy = dummy;
        Node copyIter = dummy;

        while (iter != null) {
            next = iter.next.next;

            copy = iter.next;
            copyIter.next = copy;
            copyIter = copy;

            iter.next = next;

            iter = next;
        }

        return dummy.next;
    }
}