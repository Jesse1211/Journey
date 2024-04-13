# DFS, 每一层会删掉第一个element, 直到leaf之后一个数字, 然后回溯, 重新加入删掉的数字
# 在当前level时, 它包括了所有leaf的结果, 也就是所有可能的排列
# 所以再回到上一层时, 只需要加上当时删掉的数字即可

from typing import List


class Solution:
    def permute(self, nums: List[int]) -> List[List[int]]:
        if len(nums) == 0: return [[]]
        res = []

        for _ in range(len(nums)):
            cur = nums.pop(0)
            perms = self.permute(nums)

            for p in perms:
                p.append(cur)
            
            nums.append(cur)

            res.extend(perms)

        return res
