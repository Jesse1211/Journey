from typing import List

# 经典DFS
# 这里需要传Start是为了防止重复. 如果从第一个出发, 它将找到所有和第一个有关的情况, 那么从第二个出发的时候就不能涵盖第一个了

class Solution:
    def combinationSum(self, candidates: List[int], target: int) -> List[List[int]]:
        list = []
        res = []
        def dfs(path, target, start):
            if target == 0:
                res.append(path)
                return
            elif target < 0:
                return
            
            for i in range(start, len(candidates)):
                dfs(path+[candidates[i]], target-candidates[i], i)
        
        dfs(list, target, 0)
        return res