---
authors:
  - Jesse
title: Depth-First Search
status: published
source: CodeBlog
---

## Tree `O(n \* m)`

### Traverse by Orders

```python
dfs(root) {
    // preorder
    dfs(root.left)
    // inorder
    dfs(root.right)
    // postorder
}
```

```text
     1
   /   \
  2     3
 / \   / \
4   5 6   7
```

- Preorder traversal: root, left, right: `1245367`
- Inorder traversal: left root right: `4251637`
- Postorder traversal: left, right, root: `4526731`
- 大部分时候是 **void** inorder(), preorder(), postorder()

> **Question**
>
>
> 🌟🌟🌟🌟 Preorder
>
> 114 - 反着做 preorder
> 430 - 和 114 一样
> 437 - 快: Prefix & preofrder; 慢: 2 个嵌套 recursion;
>
> 🌟🌟 Inorder
>
> 285 - Tree 快慢指针
> 426 - Tree 转成 Sorted Doubly LinkedList
> 510 - 285 增强, 画图 & while loop 反推 (左中右 -> 右中左)
>
> 🌟🌟🌟🌟 Postorder
>
> 449 - Tree (De)serialization, Stack, dfs
>
> 🌟🌟🌟🌟 Mix
>
> 105 - serialize from preorder & inorder
> 106 - serialize from postorder & inorder
>

---

### 基于 Traverse Orders, 更实用的 useage

```python
dfs(res, root, value) {
    if (!root):
        return

    if (value = root.val):
        res.add(root)
        return

    for option : options:
        做选择
        dfs(res, newRoot, newValue)
        撤销选择
}
```

- 步骤

  1. base case: if root == null
  2. inductive case: if can make recursive call
  3. return

- Helper functions
  - depth(), isBST(), nodeCount(), dfs()
  - 讲究 order 时 (一般用 **PostOrder** 方式)
    - dfs() return TreeNode
    - 根据 returned left & right 判断

> **Question**
>
>
> ⭐️⭐️⭐️ Tree
>
> 333 - 经典 DFS, recursive main & recursive helper function
> 124 - ❤️ res 不一定是 leaf -> leaf path
> 236 - ❤️ 三选一 return left, right, root
> 250 - ❤️ helper return bool 判定 univalue subtree
> 450 - ❤️ delete BST
> 1650 - ❤️ helper return bool (另一个逻辑: set)
>

---

## 其他不同的格式 O(2^N) 每个元素用一次, 每个元素只有两种选择

### 逻辑相似 (e.g. Permutation - 找到所有可能性)

- helper function 必不可少
  - `dfs(所有选项, 当前 index, res, cur)`
- index 注意:
  - base case & recursive call 中的 index 判定: `Value | Index`
- 步骤

  1. base case: index 或者 cur 达到上限
  2. inductive:

  - recursive call with new index & cur
    - `cur.add() + dfs(...) + cur.remove()`
  - 如果是更改 input, 需要快慢指针 swap 来代替 cur 和 res
  - dfs()/main()中可以加 set 来防重
    - 取决于题型: 前后 add + remove, 或者设定 true / false
    - 取决于题型: set 保存 value 还是 index

- 防重

  ```java
  // 1. 如果Traverse strategy每次从index开始, 必须要sort
  Arrays.sort(num)

  // 2. 当前**traverse**中, 不能重复使用某元素
  if (visitedIndex.contains(i)) {
      continue;
  }

  // 3. 当前**level**中, 不能以相同数值做起点 (用nums[i] == nums[i-1]代替set)
  Set<Integer> visitedValue = new HashSet<>();
  for (i from x to y) {
    if (visitedValue.contains(nums[i])) {
        continue;
    }

    visitedValue.add(nums[i]);
  }
  ```

> **Question**
>
>
> ⭐️ 防重
> 47 - for loop 起点为 0
> 90 - for loop 起点为 index
> 526 - 用 boolean list 整体防重
>
> ⭐️⭐️ Matrix: BFS / DFS 都可以
>
> 79
> 419
> 547
> 694
>
> ⭐️⭐️⭐️ 灵活运用
>
> 17
> 51
> 93
> 131 - traverse 所有组合, 每个组成的 substring 检查是不是 palindrome
> 216
>

---
