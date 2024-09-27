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

/*
 * 两个指针, two pointers
 */

class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode left = head;
        ListNode right = head;

        // set the window
        for (int i = 0; i < n; i++) {
            right = right.next;
        }

        // when remove the head -> n = length
        if (right == null) {
            return head.next;
        }

        // shift to right most
        while (right != null && right.next != null) {
            left = left.next;
            right = right.next;
        }

        // drop the element
        left.next = left.next.next;
        return head;

    }
}