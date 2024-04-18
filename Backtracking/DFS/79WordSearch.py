from typing import List

# 经典DFS, 只不过在查看children结果的时候, 要用OR, 只要有一条路径是True, 就返回True
class Solution:
    def exist(self, board: List[List[str]], word: str) -> bool:
        dirs = [[-1, 0], [1, 0], [0, 1], [0, -1]]
        visited = set()
        def dfs(row, col, index):
            if index == len(word): return True
            
            if 0 <= row < len(board) and 0 <= col < len(board[0]):
                if board[row][col] == word[index] and ((row, col) not in visited):
                    visited.add((row, col))
                    res = False
                    for [i, j] in dirs:
                        res = res or dfs(row+i, col+j, index + 1)
                    visited.remove((row, col))
                    return res
            else: return False
        
        for i in range(len(board)):
            for j in range(len(board[i])):
                if board[i][j] == word[0]:
                    if bfs(i, j, 0):
                        return True
        return False

