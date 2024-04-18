# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
import collections
from typing import List, Optional

# 有两种解法, 哪种更好? 对于可以BFS的题, 我觉得都行hhhh
# 1. 用递归DFS解法(需要控制level): basecase就是当cur为空时return; inductive case就是append当前node的值到res[level]里面，然后递归左右子树，level+1
            # 在这里需要level是因为要把每一个level的node放到一个element里面，所以需要一个level来区分
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