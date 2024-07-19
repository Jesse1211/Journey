package TwoPointer;

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
        if (head == null || k == 0) {
            return head;
        }

        ListNode dummy = new ListNode();
        dummy = head;
        int len = 0;

        while (dummy != null) {
            dummy = dummy.next;
            len++;
        }

        int newK = k % len;

        if (newK == 0) {
            return head;
        }

        dummy = head;
        ListNode oldHead = head;

        for (int i = 0; i < newK; i++) {
            dummy = dummy.next;
        }

        while (dummy != null && dummy.next != null) {
            head = head.next;
            dummy = dummy.next;
        }

        ListNode newHead = head.next;
        head.next = null;
        dummy.next = oldHead;

        return newHead;

    }
}