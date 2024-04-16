from typing import List

# 和767题一样
class Solution:
    def rearrangeBarcodes(self, barcodes: List[int]) -> List[int]:
        freqMap = {}
        for i in barcodes:
            if i in freqMap: freqMap[i] += 1
            else: freqMap[i] = 1
        
        sortedMap = sorted(freqMap.keys(), key = lambda x: freqMap[x], reverse=True)

        res = [None] * len(barcodes)
        index = 0
        for i in sortedMap:
            for j in range(freqMap[i]):
                if index >= len(barcodes):
                    index = 1
                res[index] = i
                index += 2
        return res