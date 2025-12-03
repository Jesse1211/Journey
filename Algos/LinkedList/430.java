
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
};
/*
 * // Definition for a Node.
 * class Node {
 * public int val;
 * public Node prev;
 * public Node next;
 * public Node child;
 * };
 */

// Assume left = child, right = next;
// root, left, right -> Preorder traversal

class Solution {
    // left = child, right = next;
    Node prev;

    public Node flatten(Node head) {
        dfs(head);
        return head;
    }

    public void dfs(Node head) {
        if (head == null) {
            return;
        }

        dfs(head.next);
        dfs(head.child);

        if (prev != null) { // not the last node
            prev.prev = head;
            head.next = prev;
        }

        prev = head;
        head.child = null;
        head = head.prev;
    }
}

class Solution2 {
    public Node flatten(Node head) {

        Node cur = head;

        while (cur != null) {
            Node left = cur.child;
            Node right = cur.next;

            if (left != null) {
                Node leftMost = left;
                while (leftMost.next != null) {
                    leftMost = leftMost.next;
                }

                leftMost.next = right;
                if (right != null) {
                    right.prev = leftMost;
                }

                cur.next = left;
                left.prev = cur;
                cur.child = null;
                cur = cur.next;
            } else {
                cur.next = right;
                cur = right;
            }
        }

        return head;
    }
}