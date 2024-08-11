# # Definition for singly-linked list.
# from typing import Optional


# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next

# # QUESTION: 为啥这个不行
# # class Solution:
# #     def sortList(self, head: Optional[ListNode]) -> Optional[ListNode]:
# #         dummy = head
# #         while(dummy):
# #             print(1)
# #             dummy = dummy.next
# #             self.helper(head)
        
# #         return head
    
# #     def helper(self, cur):
# #         if cur and cur.next:
# #             if cur.val > cur.next.val:
# #                 temp = cur.val
# #                 cur.val = cur.next.val
# #                 cur.next.val = temp
# #                 return self.helper(cur.next)
# #         return cur

# '''
#     快慢指针来找到中点，然后分治,  assume每一个list都是sorted的, 这样可以merge
# '''    

# class Solution:
#     def sortList(self, head: Optional[ListNode]) -> Optional[ListNode]:
#             if not head or not head.next:
#                 return head
            
#             slow, fast = head, head.next

#             while(fast and fast.next):
#                 slow = slow.next
#                 fast = fast.next.next
            
#             mid = slow.next
#             slow.next = None
            
#             left = self.sortList(head)
#             right = self.sortList(mid)

#             dummy = ListNode(0)
#             cur = dummy
#             while(left and right):
#                 if left.val < right.val:
#                     cur.next = left
#                     left = left.next
#                 else:
#                     cur.next = right
#                     right = right.next
#                 cur = cur.next
            
#             cur.next = left or right


#             return dummy.next
            
        