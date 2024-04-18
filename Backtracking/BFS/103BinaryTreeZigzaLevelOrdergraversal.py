# Definition for a binary tree node.
import collections
from typing import List, Optional

# 经典BFS
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
class Solution:
    def zigzagLevelOrder(self, root: Optional[TreeNode]) -> List[List[int]]:
        if not root: return []

        res = []
        q = collections.deque([])
        q.append(root)
        direction = -1
        while(q):
            level = []
            for i in range(len(q)):
                cur = q.popleft()
                if cur:
                    level.append(cur.val)
                    q.append(cur.left)
                    q.append(cur.right)
            if len(level) != 0: 
                if direction == 1:
                    level.reverse()
                direction = -1 * direction
                res.append(level)
        return res

        