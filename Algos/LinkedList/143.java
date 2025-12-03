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
 * highlight: linkedlist reverse
 */
class Solution {
    public void reorderList(ListNode head) {

        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        ListNode firstHead = head;
        ListNode secondHead = reverse(slow);

        while (secondHead.next != null) {
            ListNode firstHeadNext = firstHead.next;
            ListNode secondHeadNext = secondHead.next;

            firstHead.next = secondHead;
            secondHead.next = firstHeadNext;

            firstHead = firstHeadNext;
            secondHead = secondHeadNext;
        }
        return;
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