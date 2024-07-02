// 你是一名行政助理，手里有两位客户的空闲时间表：slots1 和 slots2，以及会议的预计持续时间 duration，请你为他们安排合适的会议时间。

// 「会议时间」是两位客户都有空参加，并且持续时间能够满足预计时间 duration 的 最早的时间间隔。

// 如果没有满足要求的会议时间，就请返回一个 空数组。

// 「空闲时间」的格式是 [start, end]，由开始时间 start 和结束时间 end 组成，表示从 start 开始，到 end 结束。

// 题目保证数据有效：同一个人的空闲时间不会出现交叠的情况，也就是说，对于同一个人的两个空闲时间 [start1, end1] 和 [start2, end2]，要么 start1 > end2，要么 start2 > end1。

import java.util.Arrays;

class Solution {
	public int[] minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
		Arrays.sort(slots1, (a, b) -> a[0] - b[0]);
		Arrays.sort(slots2, (a, b) -> a[0] - b[0]);
		int i = 0, j = 0;
		while (i < slots1.length && j < slots2.length) {
			int start = Math.max(slots1[i][0], slots2[j][0]);
			int end = Math.min(slots1[i][1], slots2[j][1]);
			if (end - start >= duration) {
				return new int[] { start, start + duration };
			}
			if (slots1[i][1] < slots2[j][1]) {
				i++;
			} else {
				j++;
			}
		}
		return new int[0];
	}

	// for an interval like this: int[][][] schedules = { {{5, 6}, {1, 2}, {3, 4}},
	// {{7, 8}, {5, 6}, {6, 7}}, {{2, 3}, {0, 1}, {4, 5}} }; each employer has their
	// schedule. we want to find the earliest time that every one is free to hold a
	// meeting with length k public int meeting(int[][][] schedule, int k){}
	public int meeting(int[][][] schedules, int k) {
		int n = schedules.length;
		// many employers w/ many schedules
		int[][] starts = new int[n][];
		int[][] ends = new int[n][];
		for (int i = 0; i < n; i++) {
			int m = schedules[i].length;
			starts[i] = new int[m];
			ends[i] = new int[m];
			for (int j = 0; j < m; j++) {
				starts[i][j] = schedules[i][j][0];
				ends[i][j] = schedules[i][j][1];
			}
		}
		Arrays.sort(starts, (a, b) -> a[0] - b[0]);
		Arrays.sort(ends, (a, b) -> a[0] - b[0]);
		int i = 0, j = 0;
		while (i < n && j < n) {
			int start = Math.max(starts[i][0], ends[j][0]);
			int end = Math.min(starts[i][1], ends[j][1]);
			if (end - start >= k) {
				return start;
			}
			if (starts[i][1] < ends[j][1]) {
				i++;
			} else {
				j++;
			}
		}
		return -1;
	}
}