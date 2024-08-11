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

// TODO: 这个很难想到，需要多做几遍
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = head;

        while (fast != null) {
            if (fast.next != null && fast.val == fast.next.val) {
                while (fast.next != null && fast.val == fast.next.val) {
                    fast = fast.next;
                }
                slow.next = fast.next;
            } else {
                slow.next = fast;
                slow = slow.next;
            }
            fast = fast.next;
        }

        return dummy.next;
    }
}