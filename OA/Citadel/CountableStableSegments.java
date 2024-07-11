package OA.Citadel;

/*
 * You have n servers, each with a capacity of capacity[i]. 
 * A contiguous subsegment [l, r] of servers is said to be stable 
 * if capacity[l] + capacity[r] = sum[l+1, r-1]. In other words, 
 * the capacity of the servers at the endpoints of the segment should 
 * be equal to the sum of the capacities of all the interior servers. 
 * You need to find the number of stable subsegments of length 3 or more.
 * 
 * Example
 * Given n = 5 and capacity = [9, 3, 3, 3, 9]:
 * The stable subsegments are:
 * [3, 3, 3]: 3 = 3 = 3
 * [9, 3, 3, 3, 9]: 9 = 9 = 3 + 3 + 3
 * Hence, the number of stable subsegments is 2.
 */
class CountableStableSegments {
    public static int countableStableSegments(int n, int[] capacity) {
        int res = 0;
        for (int i = 0; i < n - 2; i++) {
            int sum = capacity[i] + capacity[i + 2];
            if (sum % 2 == 0 && sum / 2 == capacity[i + 1]) {
                res++;
            }
        }
        return res;
    }

}
