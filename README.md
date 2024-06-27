# Algorithm

这个 repo 用来记录 leetcode & 学习笔记
时间就像海绵, 挤一挤走是有的,

id 刷题 === 早日上岸: why don't you start right now?

养成习惯 一天五道题 坚持 19 天先

- [x] Day1 4/12/2024
- [x] Day2 4/13/2024
- [x] Day3 4/15/2024
- [x] Day4 4/16/2024
- [x] Day5 4/17/2024
- [x] Day6 4/27/2024 复习 Sort & Backtracking
- [x] Day7 5/22/2024 复习 Sort & Backtracking
- [x] Day8 5/23/2024 复习 BFS & DFS + 学习 binary search
- [x] Day9 5/24/2024 学习 DP 经典部分
- [x] Day10 5/25/2024 学习 DP 更多部分
- [x] Day11 5/26/2024 学习 DP 真题
- [x] Day12 5/27/2024 学习 DP 真题
- [x] Day13 写了 snowflake 和 Awardco 的 OA
- [x] Day14 5/29/2024 binary search
- [x] Day15 6/12/2024 开始 blind 75
- [x] Day16 6/15/2024 complete Graph, Interval, LinkedList
- [x] Day17 6/16/2024 complete Matrix, String; todo: Tree, Heap
- [x] Day18 6/17/2024 complete Tree, Heap; 完成 blind 75
- [x] Day19 6/18/2024 开始第二 section: complete LinkedList
- [x] Day20 6/19/2024 开始第二 section: complete Queue & Stack & 系统学习 linkedList
- [x] Day21 6/20/2024 开始第二 section: complete Hash
- [x] Day22 6/21/2024 开始第二 section: complete Two pointer
- [x] Day23 6/24/2024 开始第二 section: complete Two pointer
- [x] Day24 6/25/2024 开始第二 section: complete Two pointer
- [x] Day25 6/26/2024 开始第二 section: complete Prefix sum, 学习Uber OA

# Java syntax

### Variable

#### String

- Convert
  - `String s = String.valueOf(integer)`
  - `String s = String.valueOf(char[] charList);`
  - `s.toCharArray()`
  - `Character.isLetterOrDigit(char)`
  - `Character.toLowerCase(char)`
- Get
  - `char ch = str.charAt(index)`
  - `s.length()`
  - `String str = new String(char[] chars)`
- Set
  - `StringBuilder sb = new StringBuilder(s);`
  - `sb.setCharAt(index, cur);`

#### Integer
- char to int
  - `Character.getNumericValue(s.charAt(i - 1));`
- string to int
  - `Integer.parseInt(s.substring(i - 2, i));`

### Structure

#### Array

- init
  - `int[] arr = new int[];`
- sort
  ```
  Arrays.sort(intervals, new Comparator<int[]>() {
  	public int compare(int[] a, int[] b) {
  		return a[1] - b[1];
  	}
  });
  ```
- other
  - `arr.length`
  - `Arrays.fill(arr, Ingeter.MIN_VALUE);`
  - `arr[0] = 1`

#### List

- init
  - `List<List<Integer>> res = new ArrayList<List<Integer>>();`
  - `List<Integer> res = new ArrayList<>();`
- other
  - `res.add(1);`
  - `res.remove(res.size() - 1);`
  - `res.get(i)`

#### Stack

- init
  - `Stack<Character> stack = new Stack<Character>();`
- other
  - `stack.pop()`
  - `stack.push()`
  - `stack.isEmpty()`

#### Map

##### HashMap

- init
  - `Map<Integer, Integer> map = new HashMap<>();`
- other
  - `map.put(var, map.getOrDefault(key, 0) + 1);`
  - `map.get(key)`
  - `map.containsKey(key)`
  - `map.values()`
  - `for (var ent:map.entrySet()) {ent.getKey()}`

#### Queue

##### General Queue

- init
  - `Queue<int[]> queue = new ArrayDeque<>();`
- other
  - `queue.size()`
  - `pq.add(new int[]{ent.getKey(), ent.getValue()});`
  - `pq.poll();`
  - `pq.isEmpty()`

##### PriorityQueue

- 用于特殊比较, 每装一个数字, 他都会特殊排序 => kth element 也就是 1st element
- 注意:
  - **Comparator Result > 0**: The comparison `b[1] - a[1]` results in a positive value when `b[1]` is greater than `a[1]`. This tells the priority queue that `b` should come before `a`, pushing larger values to the front.
  - **Comparator Result < 0**: Conversely, if `b[1]` is less than `a[1]`, the result is negative, positioning `a` before `b`, effectively demoting smaller values.
- init
  - `PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> b[1] - a[1])`

#### Set

- init
  - `Set<Integer> set = new HashSet();`
- other
  - `set.add`
  - `set.contains`
  - `set.remove`
- 其他 equivalent usage
  - `boolean[][] visited = new boolean[m][n];`
  - `HashMap<Integer,Boolean> visited = new HashMap<>();`
    - `visited.put(i,false);`

# Backtrack

- Preorder
  - 根节点、左子树、右子树
  - 第一个数字总是树的根结点的值
- Inorder
  - 左子树、根节点、右子树
  - 根结点的值在序列的中间，左子树的结点的值位于根结点的值的左边，而右子树的结点的值位于根结点的值的右边

# BFS

## Recursion

```
class Solution {

public List<List<Integer>> levelOrder(TreeNode root) {
	List<List<Integer>> res = new ArrayList<>();
	bfs(root, 0, res);
	return res;
}

public static void bfs(TreeNode root, int index, List<List<Integer>>res)
{
	if (root == null) {
		return ;
	}
	if (res.size() == index) { // 需要开一个新的level
		List<Integer> cur = new ArrayList<>();
		cur.add(root.val);
		res.add(cur);
	}
	else {
		res.get(index).add(root.val);
	}
	bfs(root.left, index+1, res);
	bfs(root.right, index+1, res);
	}
}
```

## Queue

```
class Solution {
public List<List<Integer>> levelOrder(TreeNode root) {
	Queue<TreeNode> q = new LinkedList<>();
	List<List<Integer>> res = new ArrayList<List<Integer>>();
	if (root == null) {
		return res;
	}
	q.add(root);
	while(!q.isEmpty()){
		int level = q.size();
		List<Integer> subLevels = new ArrayList<>();
		for (int i = 0; i < level; i++) {
			var cur = q.poll();
			subLevels.add(cur.val);
			if (cur.left != null) {
				q.add(cur.left);
			}
			if (cur.right != null) {
				q.add(cur.right);
			}
		}
		res.add(subLevels);
	}
		return res;
	}
}
```

# DFS

DFS (for loop 里面加了之后, recursive call 之后别忘了删掉):

## case w/o SET:

```
public List<List<Integer>> permute(int[] nums) {
	List<List<Integer>> res = new ArrayList<List<Integer>>();
	dfs(nums, new ArrayList<>(), res);
	return res;
}

private void dfs(int[] nums, List<Integer> curList, List<List<Integer>> res) {
	if (curList.size() == nums.length) {
		res.add(new ArrayList<>(curList));
		return;
	}

	for (int i = 0; i < nums.length; i++) {
		curList.add(nums[i]);
		dfs(nums, curList, res);
		curList.remove(curList.size() - 1);
	}
}
```

## case w/ SET:

```
public void dfs(HashSet<String> set, boolean[] visited, String cur, String tiles) {
	set.add(cur);
	for (int i = 0; i < tiles.length(); i++) {
		if (visited[i]) { continue; }
		visited[i] = true;
		dfs(set, visited, cur + tiles.charAt(i), tiles);
		visited[i] = false;
	}
}
```

# Binary Search

拿到左右两边, evaluate & 更新 中间值

## 重点:

`while (left < right)` OR `while (left <= right)`, 取决于如何更新 `left` 和 `right`, 以及如何定义搜索的结束条件。两者的选择影响了循环何时结束，以及如何确定最终结果

### `while (left <= right)` (精确匹配并可能返回索引)

- 当 `left` 和 `right` 重合: `left == right` . 仍然会进行一次检查。这种情况适用于需要确保在区间内部查找且可能直接返回匹配的索引的场景。
- **更新方法**：通常在这种情况下，如果中间元素不是目标值，你会将 `left` 更新为 `mid + 1`，将 `right` 更新为 `mid - 1`。
- **适用场景**：当你需要查找的元素确实存在于数组中，并且你需要直接返回找到的元素的索引时。

```
public int firstBadVersion(int n) {
	int low = 1;
	int high = n;
	while(low<=high){
		int mid = low + (high-low)/2;
		if(isBadVersion(mid)){
			high = mid - 1;
		}
		else {
			low = mid + 1;
		}
	}
	return low;
}
```

### `while (left < right)` (在区间中查找边界或条件)

- 即将重合时结束，即不包括 `left == right`
- 这种方法常用于需要在区间中查找但不需要返回确切索引的情况，例如寻找上界或下界。
- **更新方法**：在这种情况下，通常会根据具体的查找目标调整 `left` 或 `right` 的更新方式，以防止无限循环。比如，`left` 可能会更新为 `mid` 或 `mid + 1`，而 `right` 则可能更新为 `mid` 或 `mid - 1`。
- **适用场景**：适用于需要找到满足某些条件的边界值，如寻找最左侧或最右侧的满足条件的元素。

```
	public int peakIndexInMountainArray(int[] arr) {
		int left = 0;
		int right = arr.length - 1;
			while (left < right) {
				int mid = left + (right - left) / 2;
				if (arr[mid] < arr[mid+1]) {
					left = mid + 1;
				}
				else {
					right = mid;
				}
			}
		return right;
	}
```

# LinkedList

## 注意:

- 环:
  - 题目有环: 快慢指针
  - 被写出环: 利用递归性 - 观察 base case
- 边界:
  - 移除头节点
  - 题目要 return 的不是头节点
- 前后序: inOrder & postOrder
  - 如果是前序遍历，那么你可以想象前面的链表都处理好了，怎么处理的不用管。相应地如果是后序遍历，那么你可以想象后面的链表都处理好了，怎么处理的不用管

## 指针的修改

### 反转

- 拿到 head & tail, 最后 return tail
- 保留 next 的信息, 更新 cur 的 next 作为 pre, (prepare for next iteration: 更新 pre 为 cur, 更新 cur 为 next)

```
	# 翻转一个子链表，并返回新的头与尾
	def reverse(self, head: ListNode, tail: ListNode):
		cur = head
		pre = None
		while cur != tail:
			# 留下联系方式
			next = cur.next
			# 修改指针
			cur.next = pre
			# 继续往下走
			pre = cur
			cur = next
		# 反转后的新的头尾节点返回出去
		return tail, head
	def dfs(head, pre):
		if not head: return pre
		# 留下联系方式
		next = head.next
		# 主逻辑（改变指针）在进入后面节点的前面（前面的已经处理好, 不会有环）
		head.next = pre
		dfs(next, head)
	dfs(head, None)
```

### 链表的拼接

- 穿针引线 - 先穿 (修改指针) 再排(排列组合让代码没有 bug) 后判空(被改变 next 指针的部分 != null )

# Two pointers

## 同向

0 $\overrightarrow{\rm processed}$ i $\overrightarrow{\rm \text{not needed}}$ j $\overrightarrow{\rm unknown}$ n

```
while (j < array.length) {
	if array[j] is needed: array[i++] = array[j]
	j++
}
```

## 反向

0 $\overrightarrow{\rm processed}$ i $\overline{\rm \text{unknown}}$ j $\overleftarrow{\rm processed}$ n

```
while (i <= j) {
	if (...) {
		i++;
		j--;
	} else ...
}
```

# DP

## 类型

1. 计数 - 方式 / 方法 数量 来满足要求
2. 最大 / 小 值 - 最大 / 长 数字
   - coin change
3. 存在性 - true / false

## 四步

1. 确定状态 - 每一个 step 和之前的关系
   - Property P: 最后一步来构成最优策略
     - $P_{n-1}$ + 最后一步 = $P_n$
   - inductive step
     - $P_{n-1}$ 是怎么求出来的
2. 转移方程 - 把状态转换成 dp list
   - $dp(x) = dp(x -1) + dp(x - 2)$
3. 初始条件和边界情况 - 细节
   - init 正无穷来找最小值 .etc.
   - 初始条件(base case): dp(x) = 0 .etc.
     - Property 中无法算出的数值
4. 计算顺序
   - button up / top down
   - 顺序取决于 dependency 的方向

## Cases

### 找最大 - 这个是最后要 return input 里面的东西, 而不是“关系”

- 用两个 variable 来 track 每轮 loop 的状态

```
class Solution {
	public int maxSubArray(int[] nums) {
		int cur = nums[0];
		int res = cur;

		for (int i = 1; i < nums.length; i++) {
		if (nums[i] > nums[i] + cur) {
			cur = nums[i];
		} else {
			cur += nums[i];
		}
			res = Math.max(cur, res);
		}
		return res;
	}
}
```

### 找关系

- 做一个新的 list 来存住每一个 element 的状态

```
class Solution {
public int coinChange(int[] coins, int amount) {
	int[] res = new int[amount + 1];
	Arrays.fill(res, Integer.MAX_VALUE);
	res[0] = 0;
	for (int i = 1; i <= amount; i++) {
		for (int j = 0; j < coins.length; j++) {
			if (i - coins[j] >= 0 && res[i - coins[j]] != Integer.MAX_VALUE)
			{
				res[i] = Math.min(res[i], 1 + res[i - coins[j]]);
			}
		}
	}
	return res[amount] != Integer.MAX_VALUE ? res[amount] : -1;
}
}
```

- 两个 entity 之间的关系 - 做一个 2d matrix 来看关系图, bottom up, 最后 dp[0][0]

```
public int longestCommonSubsequence(String text1, String text2) {
	int m = text1.length();
	int n = text2.length();
	int[][] dp = new int[m + 1][n + 1];
	for (int i = m - 1; i >= 0; i--) {
		for (int j = n - 1; j >= 0; j--) {
			char char1 = text1.charAt(i);
			char char2 = text2.charAt(j);
			if (char1 == char2) {
				dp[i][j] = 1 + dp[i+1][j+1];
			}
			else {
				dp[i][j] = Math.max(dp[i+1][j], dp[i][j+1]);
			}
		}
	}
	return dp[0][0];
}
```
