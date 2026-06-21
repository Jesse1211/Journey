---
authors:
  - Jesse
title: Breath-First Search
status: published
source: CodeBlog
---

## Shortcodes O(n \* m)

```python
while (q is not empty) {
	traverse q {
		var cur = q.poll();

		traverse cur.children {
			if cur.child is valid {
				if (cur.child is valid) {
					do something;
					add cur.child to q;
				}
			}
		}
	}
}
```

### Tree: 以 root 为中心

- 横向: 把 tree 转换成 Array 的两种表达式:
  1.  以 root 为中心 root = i, left = i - 1, right = i + 1.
  2.  以 root 为起点, 右下角 leaf 为终点. root = i, left = i _ 2, right = i _ 2 + 1.
      - 从左往右看每一个 level
- 纵向: 通过 Queue / Map 监控每个 node 的 index, 加上当前 Level 便可找出具体 node
  1. Root.index = 1
  2. Root.left.index = Root.index - 1; Root.right.index = Root.index + 1;

> **Question**
>
>
> **- [x] 题号 - 推荐 ❤️ 难度 ⭐️ (基础框架 + ...)**
>
> ⭐️ 基础 BFS
>
> - 107 - boolean ifReverse
> - 199 - 特殊处理每层最后一个 node
> - 958 - 如何判定结束?
>
> ⭐️⭐️ 需要额外 Data structure 处理信息 (HashSet, HashMap)
>
> - 261 - ❤️ Queue 配合 Set
> - 863 - ❤️ Map 实现 child to parent path
>
> ⭐️⭐️ 加 column 角度
>
> - 314 - ❤️ ⭐️⭐️⭐️ Map 应用于 column 信息, 额外的 Queue 保存每个 Node 的 Index
> - 662 - ❤️ ⭐️⭐️⭐️⭐️ 314 增强版. 注意 Index (x \* 2 + 1), min & max 计算方式 (min 等于每个 level 的第一个 node.index)
> - 987 - ⭐️⭐️⭐️ 314 增强版, 相同 index & level 重新 sort
>
> ⭐️⭐️⭐️ Tree Serialization - 判断 serialize 前后的 format
>
> - 297 - ❤️
> - 449
>

---

### Matrix, Graph, 无规则 List: 指定点为起点

- 难点 - 用 Map 或者 list 来重新梳理一下信息
- 防重
  - `int[] minCost = new int[n];`: 根据 cost 或其他数据避免不需要的 access
  - `Set<> visited = new HashSet();`: 无脑防止第二次 access
  - `Map<> map = new HashMap;` 可以在处理数据的同时做到防重
  - Change value when traversing, change back after

#### From 1 spot

- 从一个点出发, 最终 traverse 整个图

> **Question**
>
>
> ⭐️ 基础
>
> - 339 ❤️ BFS, value \* level
> - 364 ❤️ 高阶 339
>
> ⭐️⭐️ 运用 **Set** 防重, 通过题目抓住每层是如何衍生 child
>
> - 127 - 双向 BFS, 每个‘child’要检查是否合理
> - 279 - 求 Levels, 那么每层数据是什么? 搭配 HashSet 使用效率更高(可以用 DP)
> - 301 - ❤️
> - 322 - 类似 279
> - 365 - ❤️ 通过题目抓住每层是如何衍生 child
>
> ⭐️⭐️⭐️ 运用 **Map** 防重和查找 child
>
> - 133 - ❤️ Map 旧&新的 node, undirected graph 需要双向连接
> - 690 - 根据 map 从 root 开始 traverse
> - 787 - ❤️ 类似 690
> - 815 - ❤️ 类似 690
>
> ⭐️⭐️ Matrix
>
> - 200 - 普通 BFS
> - 490 - 2Matrix 搭配 visited, 计算 levels
> - 1091 - 双向 BFS
>

#### From multiple stops

- 从很轻易判定状态的开始 (edge case)
- **Topological**: 通过`int[] degrees`判断是不是 leaf, 作为起点

> **Question**
>
>
> ⭐️⭐️ Matrix - 找到起点, BFS 处理 & 更改内容
>
> - 130 - bfs on sides (上下左右)
> - 317 - ⭐️⭐️⭐️⭐️⭐️ ❤️❤️ 每次 BFS 都会更改内容, 所以更改的内容是 variable
> - 417 ❤️ 两次 bfs (使用 visited), 合并两个 visited
> - 1162 - 经典
> - 934 ❤️ 连续两次 BFS, 第一次从 island 出发, 第二次从 water 出发
>
> ⭐️⭐️ Topological Sort - 总结 `int[][] degrees`, degrees = 1 为起点, 每次 iterate degree - 1 (iterate 时可能需要 map)
>
> - 210 入门
> - 310 类似 210
> - 329 ❤️ 类似 water flood, 但是通过 degrees 找到起点
>

---

### Recursion 不常用

```java
bfs(TreeNode root, int index, List<List<Integer>>res)
{
	if (root == null) {
		return ;
	}
	if (res.size() == index) { // 需要开一个新的level
		List<Integer> cur = new ArrayList<>();
		cur.add(root.val);
		res.add(cur);
	} else {
		res.get(index).add(root.val);
	}

	bfs(root.left, index+1, res);
	bfs(root.right, index+1, res);
}
```
