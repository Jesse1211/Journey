package DataStructure.LinkedList;

import java.util.HashSet;
import java.util.Set;

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
    public ListNode detectCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        while (head != null) {
            if (set.contains(head)) {
                return head;
            }
            set.add(head);
            head = head.next;
        }
        return null;
    }
}

// class Solution {
//     public ListNode detectCycle(ListNode head) {
//         ListNode slow = head, fast = head;
//         while (fast != null && fast.next != null) {
//             slow = slow.next;
//             fast = fast.next.next;
//             if (slow == fast)
//                 break;
//         }

//         // handle if 1 head only or no head
//         if (fast == null || fast.next == null)
//             return null;
//         while (slow != head) {
//             slow = slow.next;
//             head = head.next;
//         }
//         return slow;
//     }
// }