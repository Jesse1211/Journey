---
authors:
  - Jesse
title: Binary Search
status: published
source: CodeBlog
---

34
272
300
658
1235
1300
2422

- 写出&总结所有情况 test case, 对症下药
- 因为 x / 2 永远被 floor, 所以永远都要**从右向左**找
  - 永远找**偏大**数字: `left = mid + 1`必不可少
- edge case:
  - edge case 取决于整个 list 的顺序规律
  - 如果 if 有多个 condition, 尽量使用 `&`

## O(log n)

### Inclusive `while (left <= right)`

- **直接判定** mid 是最终结果
  - `if (...) {return mid;}`
- 必有: `left = mid + 1; right = mid - 1;`

```java
// init value = res最大 & 最小
int left = 0;
int right = n;

while (left <= right) {
	int mid = left + (right - left) / 2;
	if (...) {
		return mid;
	} else if (...) {
		left = mid + 1;
	} else {
		right = mid - 1;
	}
}

return left;
```

> **Question**
>
>
> ⭐️ Matrix - mid 取自于总 int 数量
> 74
>
> ⭐️⭐️⭐️⭐️ 其他
>
> 81 - 所有 test case 中分组判定
> 162 - 入门
> 1539
> 275 - 不确定
>

---

### Exclusive `while (left < right)`

- **不可直接判定**：mid 是最终结果
- 必有:
  - `left = mid + 1; right = mid;`

> **Question**
>
>
> ⭐️ 入门
> 69
> 528
>
> ⭐️⭐️⭐️ 自定义判断 - `if (helper()) {...}`
>
> 410
> 475
> 875
> 1011
> 1802
>
>
> ⭐️⭐️⭐️ 情况偏多
>
> 154 - ❤️ 高阶 153, 和 81 逻辑相似, 总结所有 test case 后写代码
>

### MID = ?

mid 有两种计算方式, 以及如何搭配更新 left & right

#### 偏左 - `int mid = left + (right - left) / 2;`

- **必有`left = mid + 1`**
- `right = mid` 或者 `right = mid - 1` 搭配使用。
- 因为这里的 mid “偏左”，如果你在判断条件满足后让 right = mid，那本次迭代就会把区间 [left, mid] 继续当作候选区间，不会丢失正确解。
- 这样写可以确保每次循环都能将搜索区间减少至少 1。

#### 偏右 - `int mid = left + (right - left) / 2 + 1;`

// 或者更常见的写法: mid = left + (right - left + 1) / 2;

- **不必有`left = mid + 1`**
- `left = mid` 或者 `left = mid + 1` 搭配使用。
- 因为这里的 mid “偏右”，如果判断条件满足后让 left = mid，就不会出现死循环；否则如果用“偏左”的 mid 再写 left = mid，可能导致 left 不变，进而死循环。
- 同理，这样可以保证区间同样向右侧或左侧收缩至少 1。
