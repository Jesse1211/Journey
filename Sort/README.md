### Notes in Sorting

Structure部分分为两种: LinkedList和List, 但是逻辑是大致相同的

#### 快慢指针
其中一个指针可以用来更改(Delete, Insert element)List
- Example:
    - List
        用慢指针(用index)来确定element应该被加到哪里
        ```
        class 27RemoveElements:
            def removeElement(self, nums: List[int], val: int) -> int:
                index = 0
                for i in range(len(nums)):
                    if nums[i] != val:
                        nums[index] = nums[i]
                        index += 1
                return index
        ```
    - LinedList
        - 用快指针来找到需要改动的node, 慢指针来确定改动之后新的快指针位置

            ```
            def 147InsertionSortList(self, head: Optional[ListNode]) -> Optional[ListNode]:
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
            ```
        - 用快慢指针来找到mid, 然后自定义sort逻辑
            ```
            def 148sortList(self, head: Optional[ListNode]) -> Optional[ListNode]:
                if not head or not head.next:
                    return head
                
                slow, fast = head, head.next

                while(fast and fast.next):
                    slow = slow.next
                    fast = fast.next.next
                
                mid = slow.next
                slow.next = None
                
                left = self.sortList(head)
                right = self.sortList(mid)

                dummy = ListNode(0)
                cur = dummy
                while(left and right):
                    if left.val < right.val:
                        cur.next = left
                        left = left.next
                    else:
                        cur.next = right
                        right = right.next
                    cur = cur.next
                
                cur.next = left or right


                return dummy.next
            ```
            


#### 自定义Sort
可以用任何sort, 只不过在比较element的时候call一下helper function
- Example
    ``` # 179LargestNumber
        def customSort(a, b):
                return str(a) + str(b) > str(b) + str(a)

            for i in range(len(nums)):
                for j in range(i, len(nums)):
                    if not customSort(nums[i], nums[j]):
                        nums[j], nums[i] = nums[i], nums[j]
    ```

#### 查找
很快的就是用heap, 因为minium heap会把最小的放在最前面, 那么就是说可以找到第k个最大/小值
- Example
    ```
    def 215KthLargest(self, nums: List[int], k: int) -> int:
            heap = nums[:k]
            heapq.heapify(heap)

            for i in range(k, len(nums)):
                if nums[i] > heap[0]:
                    heapq.heappop(heap)
                    heapq.heappush(heap, nums[i])
            
            return heap[0]
    ```