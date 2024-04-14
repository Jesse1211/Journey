import heapq
from typing import List

# 做一个只有k个元素的heap, 然后每次遍历到一个新的元素, 如果比heap里面最小的大, 就把最小的pop掉, 把新的放进去
# 因为heapq.heapify(heap)把它变成了minimum heap, 所以最小的在最前面
# 每次push / pop的时候, 会自动调整heap, 保证最小的在最前面
class Solution:
    def findKthLargest(self, nums: List[int], k: int) -> int:
        heap = nums[:k]
        heapq.heapify(heap)

        for i in range(k, len(nums)):
            if nums[i] > heap[0]:
                heapq.heappop(heap)
                heapq.heappush(heap, nums[i])
        
        return heap[0]

