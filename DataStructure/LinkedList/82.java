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
 * highlight: 指到新的地方, 要注意把next的指向
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode newHead = new ListNode();
        ListNode dummyHead = newHead;

        while (head != null) {
            int cur = head.val;
            boolean isDuplicate = false;

            while (head.next != null && head.next.val == cur) {
                head = head.next;
                isDuplicate = true;
            }

            if (!isDuplicate) {
                dummyHead.next = head;
                dummyHead = dummyHead.next;
            }
            head = head.next;
            dummyHead.next = null;
        }

        return newHead.next;
    }
}