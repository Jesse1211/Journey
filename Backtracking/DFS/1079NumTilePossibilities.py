# 因为题目限制, 只有26种字符, 如果没有限制的话那就要用map
# 因为每一个字符都可以作为root, 那么我们要用DFS
# 在recursive call的时候, res += res + 1 是因为他的parent有相同数量的, 并且还要更多一层的可能性

class Solution:
    def numTilePossibilities(self, tiles: str) -> int:

        freq = [0] * 26
        for tile in tiles: freq[ord(tile)-ord('A')] += 1

        def dfs(freq):
            res = 0
            for i in range(len(freq)):
                if freq[i] == 0: continue
                freq[i] -= 1
                res += dfs(freq) + 1
                freq[i] += 1
            return res

        return dfs(freq)