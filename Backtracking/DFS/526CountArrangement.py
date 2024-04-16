# 用i来track当前num应该被放在的index上, 
# base case: 只有length合适的时候才会+1, 也就是i == n+1的时候
# 每个recursive call都是在找下一个num应该放在的index, 所以index+=1
# 假设当前number是可以被采用的, 那么就要把他删掉, 以免重复使用

class Solution:
    def countArrangement(self, n: int) -> int:
        self.res = 0
        nums = [i for i in range(1, n+1)]

        def dfs(nums, i):
            if n+1 == i: 
                self.res += 1
                return

            for j, num in enumerate(nums):
                if (num % i == 0) or (i % num == 0):
                    dfs(nums[:j] + nums[j+1:], i+1)
        
        dfs(nums, 1)
        return self.res