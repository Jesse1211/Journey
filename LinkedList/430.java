
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
};

class Solution {
    public Node flatten(Node head) {
        Node cur = head;
        while (cur != null) {
            if (cur.child != null) {
                // 只把child这一层解决

                Node childNode = cur.child;

                while (childNode.next != null) {
                    childNode = childNode.next;
                }

                childNode.next = cur.next;
                if (cur.next != null) {
                    cur.next.prev = childNode;
                }

                cur.next = cur.child;
                cur.child.prev = cur;
                cur.child = null;
            } else {
                cur = cur.next;
            }
        }

        return head;
    }
}