---
authors:
  - Jesse
title: Dynamic Programming
status: pending
source: CodeBlog
---

## DP

### 类型

1. 计数 - 方式 / 方法 数量 来满足要求
2. 最大 / 小 值 - 最大 / 长 数字
   - coin change
3. 存在性 - true / false

### 四步

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

### Cases

#### 找最大 - 这个是最后要 return input 里面的东西, 而不是“关系”

- 用两个 variable 来 track 每轮 loop 的状态

```JAVA
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

#### 找关系

- 做一个新的 list 来存住每一个 element 的状态

```JAVA
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

```JAVA
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

139
