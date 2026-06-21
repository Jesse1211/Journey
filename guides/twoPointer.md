---
authors:
  - Jesse
title: Two Pointer
status: published
source: CodeBlog
---

## O(nlogn) OR O(nlogn)

### 两个 list, 分别各有一个指针

- Loop 一个 list, 每个 element 和另一个 list 的 2 pointer 合作
```java
int left = 0;
int right = 0;

while (left < n && right < m) {
  // 处理 right
  while (left < n && right < m && condition) {
    right++;
  }

  // 处理 left & right
  while (left < n && right < m && condition) {
    left++;
    right++;
  }
}
```

> **Question**
>
>
> - 408 基础
> - 524 基础
> - 826 ⭐️⭐️⭐️⭐️ sort后 two pointer (更偏向于preprocessing & greedy)
> - 986 ⭐️⭐️⭐️⭐️ find the over lap by comparing boundries
> - 1570 ⭐️⭐️ 运用合理的data structure
>

---

### 同向 - 快慢指针

**普通同向指针 (不需要储存ij之间的信息)**
- `[processed -> not needed -> unknown]`
- [0 -> i -> j -> n], (指针不一定要从 0 出发, 也能从 n-1 出发)
- processed: slow pointer
- not needed: fast pointer
- unknown: length of the array

```java
while () {
  if (...) {
    fast++; // append
  } else {
    ... // create a new window (不存在shirnk)
    slow++;
    fast++;
  }
}
```

> **Question**
>
>
> ⭐️⭐️ 基础同向指针: slow用于储存数据, fast用于阅读数据
>
> - 80: 排除超过x个重复的元素
> - 121: 买卖股票
> - 443: 更改 char[]
> - 457: circular & direction, 快指针跳两步(检查第一 & 第二步是否有效)
>


**Sliding Window (需要储存ij之间的信息, 额外存储窗口信息)**
- `[检测过 -> 当前窗口 -> unknown]`
- [0 -> i -> j -> n], (指针不一定要从 0 出发, 也能从 n-1 出发)

1. 扩大窗口
2. 缩小窗口
3. 更新答案

- DataStructure 保存 window 信息
  - (frequency / index), 通过判定 fast 对应的 element 来更改 left

```text
int left = 0, right = 0, 

// window 信息
window = 0; // 或者 int[] window = new int[n];

while (fast < n) {
  2. insert: 添加fast, 更新window信息
	2. validate: 检查窗口 & shirnk (删除slow & 更新slow)
	3. 更新fast
}
```

> **Question**
>
>
> ⭐️⭐️⭐️ Fixed 窗口
>
> - 438: 2 个 list 储存 & 比较 freq
> - 904: 固定k个篮子
> - 930: 有trick, **算出window内的所有情况 `res += right - left + 1;`**
> - 1052: 找到最大 unsatisfied count in length k, 转换成最长的 satisfied subarray
> - 1343: 固定k个数
> - 1423: 固定k个数的最小和, 读懂题就知道怎么写了
> - 2090: optional使用prefixSum
>
>
> ⭐️⭐️⭐️ Non-Fixed 窗口
>
> - 3: 无重复的最长substring
> - 76: ❤️ 2 个map储存 & 比较 freq, 使用unique代表数量 - window size
> - 424: list储存freq
> - 532: 找到所有情况
> - 713: 子数组乘积小于k
> - 1004: 最多flip k个0
> - 1658: 最长子数组和等于k
> - 1838: k个更新后, 最多相同数字
>
> 另类 sliding window (用到PQ, 也可数学计算)
>
> - 621
> - 2365
>
---

### 相夹 - 左右指针

```text
0 -> i -- j <- n
- i = left [0, i]: processed
- j = right [j, n]: processed
- n = length, [i, j]: unknown

while (left <= right) {
  1. 处理 left++
  2. 处理 right--
  3. (optional) 处理 left & right
}
```

> **Question**
>
>
> ⭐️ 基础
>
> - 42: 接雨水
> - 167: two sum II
> - 680: Palindrome II
>
> ⭐️⭐️⭐️⭐️ LR 双指针 + index 指针
>
> - 75: sort colors, swap, 排除法
> - 259: 3 sum II
> - 881: 这题很trick
> - 923: 高阶版 259
>

---

- 31: Permutation 找到规律暴力解