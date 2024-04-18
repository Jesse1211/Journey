# 需要快慢指针
# 需要比较快和慢的值 
# 然后把快指针插入到已经sort好的排序中
# 然后用慢指针来更新快指针(快 = 慢.next)

from typing import Optional


class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next
class Solution:
    def insertionSortList(self, head: Optional[ListNode]) -> Optional[ListNode]:
        if not head or not head.next: return head

        dummyHead = ListNode(-5000)
        dummyHead.next = head
        fast = head.next
        slow = head

        while(fast):
            if fast.val >= slow.val:
                slow = slow.next
            else:
                # start form head to fine position for insertion
                prev = dummyHead
                while(prev.next.val <= fast.val):
                    prev = prev.next
                # find the position
                slow.next = fast.next # delete fast node
                temp = prev.next
                prev.next = fast      # insert fast node
                fast.next = temp

            fast = slow.next
        return dummyHead.next