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

- Binary Search
	- 拿到左右两边, evaluate & 更新 中间值
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
