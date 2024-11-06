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
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null) {
            return head;
        }

        ListNode dummyHead = new ListNode();
        dummyHead.next = head;
        ListNode prev = dummyHead;

        // 1. prev: 找到翻转起始点的上一个
        for (int i = 1; i < left; i++) {
            prev = prev.next;
        }

        // 2. 开始 cur和cur之后的反转, 但是prev不变
        ListNode cur = prev.next;
        for (int i = 0; i < right - left; i++) {
            ListNode next = cur.next;
            cur.next = next.next;
            next.next = prev.next;
            prev.next = next;
        }

        return dummyHead.next;
    }
}