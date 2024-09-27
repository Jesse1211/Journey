
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
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode oddHead = new ListNode();
        ListNode odd = oddHead;
        ListNode evenHead = new ListNode();
        ListNode even = evenHead;

        int index = 1;
        while (head != null) {
            if (index % 2 == 0) {
                odd.next = head;
                odd = odd.next;
                head = head.next;
                odd.next = null;
            } else {
                even.next = head;
                even = even.next;
                head = head.next;
                even.next = null;
            }

            index += 1;
        }
        even.next = oddHead.next;
        return evenHead.next;
    }
}