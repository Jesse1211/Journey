
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
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode left = head;
        ListNode right = head.next;
        ListNode next = right.next;

        // inductive: 第一次运行时, 不需要考虑谁的next指向了left
        right.next = left;
        left.next = swapPairs(next);

        return right;
    }
}