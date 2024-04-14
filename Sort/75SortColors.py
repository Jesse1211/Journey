from typing import List

# 因为只有三个数, 所以可以用一个map来存储每个数的个数, 然后再按照顺序放回去
class Solution:
    def sortColors(self, nums: List[int]) -> None:
        """
        Do not return anything, modify nums in-place instead.
        """
        map = {}
        for i in nums:
            if i not in map:
                map[i] = 1
            else:
                map[i] += 1
        
        index = 0
        for i in range(3):
            if i in map:
                for j in range(map[i]):
                    nums[index] = i
                    index += 1