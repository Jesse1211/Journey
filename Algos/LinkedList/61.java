class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return head;
        }

        ListNode left = head;
        ListNode right = head;

        int len = 0;
        while (right != null) {
            right = right.next;
            len++;
        }

        k %= len;
        right = head;

        if (k == 0) {
            return head;
        }

        while (k-- > 0) {
            right = right.next;
        }

        while (right != null && right.next != null) {
            right = right.next;
            left = left.next;
        }

        ListNode next = left.next;
        left.next = null;
        right.next = head;
        return next;
    }
}