class Solution:
    def solveNQueens(self, n: int) -> List[List[str]]:
        def backtracking(n, board, row):
            if row == n:
                res.append(["".join(theRow) for theRow in board])
                return 
            for col in range(n):
                if not isValid(n, board, row, col):
                    continue
                # Make choice
                board[row][col] = "Q"
                backtracking(n, board,  row+1)
                # Undo the choice
                board[row][col] = "."
                
        
        def isValid(n, board, row, col):
            # Same col
            for i in range(row):
                if board[i][col] == "Q":
                    return False
            # right diagonal
            tmp_row, tmp_col = row - 1, col + 1
            while tmp_row >= 0 and tmp_col < n:
                if board[tmp_row][tmp_col] == "Q":
                    return False
                tmp_row -= 1
                tmp_col += 1
            # left diagonal
            tmp_row, tmp_col = row - 1, col - 1
            while tmp_row >= 0 and tmp_col >= 0:
                if board[tmp_row][tmp_col] == "Q":
                    return False
                tmp_row -= 1
                tmp_col -= 1
            return True
         
        board = [["."] * n for _ in range(n)]
        res = []
        backtracking(n, board, 0)
        return res
            