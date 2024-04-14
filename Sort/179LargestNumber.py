from typing import List

# 可以用任何sort, 只不过比较的时候要自定义
class Solution:
    def largestNumber(self, nums: List[int]) -> str:
        def customSort(a, b):
            return str(a) + str(b) > str(b) + str(a)

        for i in range(len(nums)):
            for j in range(i, len(nums)):
                if not customSort(nums[i], nums[j]):
                    nums[j], nums[i] = nums[i], nums[j]
        
        if nums[0] == 0:
            return "0"
        return str("".join(map(str, nums)))