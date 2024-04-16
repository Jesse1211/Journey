from typing import List
# 防重: 提前sort好, 然后在dfs的时候, 如果当前的和上一个一样, 就skip
# 每次recursive call需要更新start, 以防止重复

class Solution:
    def combinationSum2(self, candidates: List[int], target: int) -> List[List[int]]:
        list = []
        res = []
        candidates.sort()
        def dfs(path, target, start):
            if target == 0:
                res.append(path)
                return
            elif target < 0:
                return
            
            for i in range(start, len(candidates)):
                if i > start and candidates[i] == candidates[i-1]:
                    continue
                dfs(path+[candidates[i]], target-candidates[i], i+1)
        
        dfs(list, target, 0)
        return res