
/*
 * highlight: 全场最重要
 */

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
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode cur = head;
        while (cur != null) {
            // step1: save cur.next node & set cur.next as prev
            ListNode after = cur.next;
            cur.next = prev;
            // step2: update prev as cur & update cur to saved node
            prev = cur;
            cur = after;
        }
        return prev;
    }
}