### Notes in Backtracking

Structure: BFS & DFS

#### BFS
涵盖了一个for loop和recursion
- forloop是为了每一个每一个点都有机会做相对的root
    - 我们可以在forloop里面再涵盖一个forloop这样就可以观察它的所有children
- recursion是为了traverse当前root的所有children
- Example
    - 经典
    ```
    def 526CountArrangement(self, n: int) -> int:
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
    ```

    - 因为需要检查children的质量, 所以有多一个forloop
    ```
    def 46permute(self, nums: List[int]) -> List[List[int]]:
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
    ```
    