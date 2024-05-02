# Note: There exist duplicate elements in the input list
class Solution:
    def permuteUnique(self, nums: List[int]) -> List[List[int]]:
        
        def backtracking(nums, path, used, res):
            if len(path) == len(nums):
                res.append(path[:])
                return 
            for i in range(len(nums)):
                if used[i]:
                    continue
                # Prune
                if i > 0 and nums[i] == nums[i-1] and used[i-1] == False:
                    continue
                path.append(nums[i])
                used[i] = True
                backtracking(nums, path, used, res)
                path.pop()
                used[i] = False
        
        # For backtracking problem, if there exist duplicate elements in the input list,
        # We will sort the input list first, which will make it much easier to prune
        nums.sort()
        res = []
        backtracking(nums, [], [False] * len(nums), res)
        return res