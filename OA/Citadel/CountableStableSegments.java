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

        int start = 0;
        while (start < n - 2) {
            int left = capacity[start];
            int sum = 0;

            int end = start + 1;
            while (end < n) {
                int right = capacity[end];

                if (start + 1 < end) {
                    sum += capacity[end - 1];
                    if (left == right && left == sum) {
                        res++;
                    }
                }

                end++;
            }

            start++;
        }

        return res;
    }

    public static void main(String[] args) {
        int n = 5;
        int[] capacity = { 9, 3, 3, 3, 9 };
        System.out.println(countableStableSegments(n, capacity));
    }

}
