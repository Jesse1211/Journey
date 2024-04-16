# Same to 46 Permutation, but with duplicates in the list 所以需要检查有没有重复
from typing import List


class Solution:
    def permuteUnique(self, nums: List[int]) -> List[List[int]]:
        if len(nums) == 0: return [[]]
        res = []
        for _ in range(len(nums)):
            cur = nums.pop(0)
            perms = self.permuteUnique(nums)

            for i in perms:
                i.append(cur)
                if i not in res:
                    res.append(i)
            nums.append(cur)
        return res