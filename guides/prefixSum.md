---
authors:
  - Jesse
title: Prefix Sum
status: published
source: CodeBlog
---

通过预先累积和存储之前的数值，使得之后使用极大地降低区间查询的时间复杂度

### Used Data Structures

- `{value: count / index}`

#### Array / List

```java
// a - b: increase
// b - a: decrease
Collections.sort(new ArrayList<>(), (a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);

Arrays.sort(new int[], (a, b) -> a - b);
```

```java
int[] sum = new int[n]; // inclusive count

n = right - left + 1; // 如果n太大, 需使用List<int[]> sort by index
int[] sum = new int[n + 1]; // exclusive count, 比如路灯问题.

// 额外解决index 0
map.put(0, -1);
sums[0] = ...

// loop for the rests from 1
for (int i = 1; i < n; i++) {
    sums[i] = sums[i-1] + nums[i];
}
```

### Sum 和 find 分开 - 找不相连的 Subarray

1. 整个 Array 中搜索某个特定 index
   - 两个方向算 Sum, 然后找关联.
2. 找当前 index 之前的关联
   - 一个方向算 Sum

```java
int[] count = new int[n];

for (int[] light : lights) {
    int left = ...;
    int right = ...;
    count[left]++;
    count[right]--;
}

int cur = 0;
int res = 0;

for (int c : count) {
    cur += c;
    res = Math.max(res, cur);
}

return res;
```

> **Note**
>
>
> ⭐️ 基础
>
> 238
> 724 - 2 list
> 1094 - map
> 2021 - ❤️ sort array list
> 2237 - list
>
> ⭐️⭐️ Groups - res = 最多 count 的 prefix
>
> 2406
>
> ⭐️⭐️⭐️ Triplets
>
> 2874 - ❤️ 2 次 prefix sum 用于找 min index 和 max index
> 2909 - 和 2874 一样
>

### Sum 和 find 同步 - 找相连的 Subarray

```JAVA
int sum = 0;
HashMap<Integer, Integer> map = new HashMap();
map.put(0, -1);

for (int num : nums) {
    sum += num;
    mapKey = sum...;

    // 检查subarray是否存在
    if (map.containsKey(mapKey)) {
        // res...
        // ...
    }

    // 是否更新map
    if (!map.containsKey(sum)) {
        // map.put...
    }
}
```

> **Question**
>
>
> ⭐️ 入门 - map, res = Math.max(...)
>
> 523
> 1109 - list
>
> ⭐️⭐️⭐️ subarray = k
>
> 325
> 560 - map
> 974 - map
>
> ⭐️⭐️⭐️ ❤️ sum 和 value 关系不直接
>
> 525 - 特定某个 value 对 sum 是加/减
> 1590 - 通过 sum 求出 map.key
>
> ⭐️⭐️⭐️⭐️ 益智 but 常考
>
> 670
