
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
    public ListNode doubleIt(ListNode head) {
        ListNode newHead = reverse(head);

        // double every node & save carry for next
        ListNode cur = newHead;
        ListNode prev = null;

        int carry = 0;
        while (cur != null) {
            int val = cur.val * 2 + carry;
            carry = 0;
            if (val > 9) {
                carry++;
                val %= 10;
            }

            cur.val = val;
            prev = cur;
            cur = cur.next;
        }

        // check carry
        if (carry != 0) {
            prev.next = new ListNode(carry);
        }

        return reverse(newHead);
    }

    private ListNode reverse(ListNode head) {
        ListNode prev = null;

        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }

        return prev;
    }
}