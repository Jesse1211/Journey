
class Node {
    public int val;
    public Node next;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _next) {
        val = _val;
        next = _next;
    }
};

class Solution {
    public Node insert(Node head, int insertVal) {
        if (head == null) {
            head = new Node(insertVal);
            head.next = head;
            return head;
        }

        Node curr = head;

        while (curr.next != head) {
            // 1. insertVal在中间
            if (curr.val <= insertVal && curr.next.val >= insertVal)
                break;

            // 2. insertVal是新的最小值或最大值
            // [3,4,5] 10 或者 [3,4,5] 1
            // 此时需要找到升序数组的断点
            // prev.val > curr.val 表示当前位置是最大值和最小值之间, 重要节点!!
            if (curr.val > curr.next.val && (insertVal >= curr.val || insertVal <= curr.next.val))
                break;

            curr = curr.next;
        }

        curr.next = new Node(insertVal, curr.next);
        return head;
    }
}