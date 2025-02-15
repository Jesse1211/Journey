
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
        int count = 0;
        ListNode end = head;

        while (end != null && count != k) {
            end = end.next;
            count++;
        }

        if (count == k) { // able to reverse one segment
            end = reverseKGroup(end, k); // recursive to next segment

            // swap current segment & connect head to END
            ListNode next = null;
            for (int i = 0; i < k; i++) {
                next = head.next;
                head.next = end;
                end = head;
                head = next;
            }

            head = end;
        }

        return head;
    }
}