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

class Solution {
    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }

        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode newHead = null, newNode = null;
        while (slow != null) {
            newNode = slow;
            slow = slow.next;
            newNode.next = newHead;
            newHead = newNode;
        }

        while (newHead != null) {
            if (newHead.val != head.val) {
                return false;
            }
            newHead = newHead.next;
            head = head.next;
        }
        return true;
    }
}