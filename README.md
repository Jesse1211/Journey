# Algorithm

这个repo用来记录leetcode & 学习笔记
时间就像海绵, 挤一挤走是有的, 

id 刷题 === 早日上岸: why don't you start right now?

养成习惯 一天五道题 坚持19天先
- [x] Day1 4/12/2024
- [x] Day2 4/13/2024
- [x] Day3 4/15/2024
- [x] Day4 4/16/2024
- [x] Day5 4/17/2024
- [x] Day6 4/27/2024 复习Sort & Backtracking
- [x] Day7 5/22/2024 复习Sort & Backtracking
- [x] Day8 5/23/2024 复习BFS & DFS + 学习binary search
- [x] Day9 5/24/2024 学习DP经典部分
- [x] Day10 5/25/2024 学习DP更多部分

# Sort
1. 快慢指针
2. kth largest: 
	- 用HashMap找frequency
		- `Map<Integer, Integer> map = new HashMap<>();`
		- `map.put(barcode, map.getOrDefault(barcode, 0) + 1);`
	- 万能使用PriorityQueue
		- `PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> a - b);`
	        - 注意: 
		        - **Comparator Result > 0**: The comparison `b[1] - a[1]` results in a positive value when `b[1]` is greater than `a[1]`. This tells the priority queue that `b` should come before `a`, pushing larger values to the front.
		        - **Comparator Result < 0**: Conversely, if `b[1]` is less than `a[1]`, the result is negative, positioning `a` before `b`, effectively demoting smaller values.
	- 遍历Queue来找到多个element
		- `while (!pq.isEmpty()) {...}`
### LinkedList
	- 需要快慢指针
	- 需要比较快和慢的值
	- 然后把快指针插入到已经sort好的排序中
	- 然后用慢指针来更新快指针(快 = 慢.next)

# BFS:
	- Recursion
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
	- Queue
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
# DFS (for loop里面加了之后, recursive call之后别忘了删掉):
	- case w/o SET:
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
	- case w/ SET:
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
- 拿到左右两边, evaluate & 更新 中间值
- 重点: 
	- `while (left < right)` OR `while (left <= right)`, 取决于如何更新 `left` 和 `right`, 以及如何定义搜索的结束条件。两者的选择影响了循环何时结束，以及如何确定最终结果
	- `while (left <= right)` (精确匹配并可能返回索引)
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
	- `while (left < right)` (在区间中查找边界或条件)
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
# DP
- 找最大 - 这个是最后要return input里面的东西, 而不是“关系”
	- 用两个variable来track每轮loop的状态  
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
- 找关系 - 做一个新的list来存住每一个element的状态
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
- 两个entity之间的关系 - 做一个2d matrix来看关系图, bottom up, 最后dp[0][0]
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