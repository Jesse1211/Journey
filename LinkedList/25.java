
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
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return null;
        }

        // find current segment
        ListNode dummyHead = head;
        for (int i = 0; i < k - 1; i++) {
            if (head == null || head.next == null) {
                return dummyHead;
            }
            head = head.next;
        }

        // process next segment
        ListNode next = reverseKGroup(head.next, k);
        head.next = null;

        // reverse current segment
        ListNode newHead = reverse(dummyHead);
        dummyHead = newHead;
        while (newHead != null && newHead.next != null) {
            newHead = newHead.next;
        }

        // connect current & next segment
        newHead.next = next;
        return dummyHead;
    }

    private ListNode reverse(ListNode head) {
        ListNode prev = null;

        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }

        return prev;
    }
}