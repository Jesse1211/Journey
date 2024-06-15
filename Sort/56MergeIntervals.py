from typing import List


class Solution:
    def merge(self, intervals: List[List[int]]) -> List[List[int]]:
        res = []
        intervals.sort()  # make sure the intervals are sorted, so that we only check overlap of cur's last element and next
        cur = intervals[0]
        for i in range(1, len(intervals)):
            left = intervals[i][0]
            right = intervals[i][1]
            if left <= cur[1]:  # overlap
                if right > cur[1]:
                    cur[1] = right
            else:  # no overlap
                res.append(cur)
                cur = intervals[i]
        res.append(cur)
        return res
