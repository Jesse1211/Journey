
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
    public void reorderList(ListNode head) {
        if (head == null || head.next == null)
            return;

        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        ListNode prev = null, next = null;
        while (slow != null) {
            next = slow.next;
            slow.next = prev;
            prev = slow;
            slow = next;
        }

        ListNode firstHalf = head;
        ListNode secondHalf = prev;
        while (secondHalf.next != null) {
            next = firstHalf.next;
            prev = secondHalf.next;

            firstHalf.next = secondHalf;
            secondHalf.next = next;

            firstHalf = next;
            secondHalf = prev;
        }

    }
}