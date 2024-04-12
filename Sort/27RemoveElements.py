# 快门指针, 两个指针, 一个指针遍历, 一个指针指向下一个非val元素应该放置的位置
class Solution:
    def removeElement(self, nums: List[int], val: int) -> int:
        index = 0
        for i in range(len(nums)):
            if nums[i] != val:
                nums[index] = nums[i]
                index += 1
        return index

 