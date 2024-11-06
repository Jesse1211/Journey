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
    public ListNode partition(ListNode head, int x) {
        ListNode left = new ListNode();
        ListNode dummyHeadLeft = left;
        ListNode right = new ListNode();
        ListNode dummyHeadRight = right;

        while (head != null) {
            if (head.val < x) {
                left.next = head;
                head = head.next;
                left = left.next;
                left.next = null;
            } else {
                right.next = head;
                head = head.next;
                right = right.next;
                right.next = null;
            }
        }

        left.next = dummyHeadRight.next;
        return dummyHeadLeft.next;
    }
}