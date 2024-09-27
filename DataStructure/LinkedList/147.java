// 需要快慢指针
// 需要比较快和慢的值
// 然后把快指针插入到已经sort好的排序中
// 然后用慢指针来更新快指针(快 = 慢.next)

/*
 * highlight: 全场最重要+1
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
    public ListNode insertionSortList(ListNode head) {
        ListNode dummy = new ListNode();
        dummy.next = head;

        ListNode slow = head;
        ListNode fast = head.next;

        while (fast != null) {
            if (fast.val >= slow.val) {
                slow = slow.next;
                fast = fast.next;
            } else {
                ListNode prev = dummy;
                while (prev.next.val <= fast.val) {
                    prev = prev.next;
                }
                // remove fast
                slow.next = fast.next;
                // insert fast
                ListNode temp = prev.next;
                prev.next = fast;
                fast.next = temp;
                fast = slow.next;
            }
        }
        return dummy.next;
    }

}