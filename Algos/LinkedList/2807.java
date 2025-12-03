
/*
 * highlight: findGCD比较难
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
    public ListNode insertGreatestCommonDivisors(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode slow = head;
        ListNode fast = head.next;

        while (fast != null) {
            int gcd = findGCD(slow.val, fast.val);
            ListNode cur = new ListNode(gcd);
            cur.next = slow.next;
            slow.next = cur;
            slow = fast;
            fast = fast.next;
        }
        return head;
    }

    private int findGCD(int a, int b) {
        // Base case: if b becomes 0, the GCD is a
        if (b == 0) {
            return a;
        }
        // Recursive case: call the function again with the second number and the
        // remainder
        return findGCD(b, a % b);
    }
}