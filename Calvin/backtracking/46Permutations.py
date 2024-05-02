# Note: This problem assume there are no duplicates in the input list
class Solution:
    def permute(self, nums: List[int]) -> List[List[int]]:

        def backtracking(path, used, res):
            if len(path) == len(nums):
                res.append(path[:])
            
            for i, letter in enumerate(nums):
                if used[i]:
                    continue
                # Make a choice
                path.append(letter)
                used[i] = True
                backtracking(path, used, res)
                # Undo the choice
                path.pop()
                used[i] = False
        
        res = []
        backtracking([], [False] * len(nums), res)
        return res
