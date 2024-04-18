### Notes in Backtracking

Structure: BFS & DFS

#### DFS
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
            if n+1 == i: # Base Case
                self.res += 1
                return

            # Inductive step
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
    

#### BFS
- 经典格式 #102
    ``` 
    # 2. 用queue(BFS解法): 把root放到queue里面，然后while queue不为空，每次把queue里面的node都pop出来，然后把node的值append到level里面
    class Solution:
        def levelOrder(self, root: Optional[TreeNode]) -> List[List[int]]:
            if not root: return []
            res = []
            q = collections.deque([])
            q.append(root)
            while(q):
                level = []
                for i in range(len(q)):
                    cur = q.popleft()
                    level.append(cur.val)
                    if cur.left: q.append(cur.left)
                    if cur.right: q.append(cur.right)
                res.append(level)
            return res
    ```
    - 还可以用DFS的思路来解, 那就需要一个parameter Level来检测应该把node存在list的位置
    ```
    class Solution:
        def levelOrder(self, root: Optional[TreeNode]) -> List[List[int]]:
            
            res = []

            def bfs(cur, level):

                if not cur: return

                if level >= len(res):
                    res.append([cur.val])
                else:
                    res[level].append(cur.val)
                
                bfs(cur.left, level+1)
                bfs(cur.right, level+1)

            bfs(root, 0)
            return res
    ```