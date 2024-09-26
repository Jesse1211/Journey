package DataStructure.LinkedList;

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
 * highlight: 和82一样, 不能忘了把指针next置为null
 */

class Solution {
    public ListNode partition(ListNode head, int x) {
        ListNode left = new ListNode();
        ListNode dummyLeft = left;

        ListNode right = new ListNode();
        ListNode dummyRight = right;
        while (head != null) {
            if (head.val < x) {
                dummyLeft.next = head;
                head = head.next;
                dummyLeft = dummyLeft.next;
                dummyLeft.next = null;
            } else {
                dummyRight.next = head;
                head = head.next;
                dummyRight = dummyRight.next;
                dummyRight.next = null;
            }
        }
        dummyLeft.next = right.next;
        return left.next;
    }
}