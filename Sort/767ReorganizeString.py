# 用map记录每个字符出现的次数，
# 然后按照出现次数排序
# 依次填充到res数组中
# 每次填充的位置是index - 先填充所有的偶数, 然后填充所有的奇数
# 在需要sort map的时候, 灵活运用lambda表达式

# 这道题很容易误解, 以为每一个element都可以作为list的开始, 其实不然, list应该最开始分布出现次数最多的element. Hence, 我们不需要BDFS
class Solution:
    def reorganizeString(self, s: str) -> str:
        map = {}

        for i in s:
            if i in map: map[i] += 1
            else: map[i] = 1

        sorted_chars = sorted(map.keys(), key= lambda x: map[x], reverse=True)

        if map[sorted_chars[0]] > (len(s) + 1) // 2: return ""

        res = [None] * len(s)
        index = 0
        for i in sorted_chars:
            for _ in range(map[i]):
                if index >= len(s): index = 1
                res[index] = i
                index += 2
        return ''.join(res)
