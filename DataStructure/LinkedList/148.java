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
 * highlight: 注意中点的寻找，以及链表的断开
 */

class Solution {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = head;

        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        prev.next = null;

        ListNode left = sortList(head);
        ListNode right = sortList(slow);

        ListNode res = new ListNode();
        ListNode dummyHead = res;

        while (left != null && right != null) {
            if (left.val <= right.val) {
                dummyHead.next = left;
                left = left.next;
            } else {
                dummyHead.next = right;
                right = right.next;
            }
            dummyHead = dummyHead.next;
            dummyHead.next = null;
        }

        if (left != null) {
            dummyHead.next = left;
        } else if (right != null) {
            dummyHead.next = right;
        }
        return res.next;
    }
}