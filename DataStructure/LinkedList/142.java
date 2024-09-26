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
 * highlight: 每发生一次cycle, head和slow的距离越来越小
 * 可以用set, 但不是考点
 * https://www.cnblogs.com/hzpeng/p/15228914.html
 */

class Solution {
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast)
                break;
        }

        // handle if 1 head only or no head
        if (fast == null || fast.next == null)
            return null;
        while (slow != head) {
            slow = slow.next;
            head = head.next;
        }
        return slow;
    }
}