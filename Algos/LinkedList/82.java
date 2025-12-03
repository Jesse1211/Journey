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
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummyHead = new ListNode();
        dummyHead.next = head;

        ListNode left = dummyHead;
        ListNode right = head;

        while (right != null) {
            if (right.next != null && right.val == right.next.val) {
                while (right.next != null && right.val == right.next.val) {
                    right = right.next;
                }
                left.next = right.next;
            } else {
                left.next = right;
                left = left.next;
            }
            right = right.next;
        }

        return dummyHead.next;
    }
}