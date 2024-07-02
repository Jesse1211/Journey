// magine that you have a time machine. You are given an array years. 
// You start in the year years[0]. 
// First, you want to travel to years[1], then to years[2], and so on. 
// Your task is to calculate the time required to visit all the years from the list in order.

// The time required to travel from the year A to the year B is calculated as follows:
// 0 hours if 𝐴 = 𝐵
// 1 hour if 𝐴 < 𝐵 (going forwards in time)
// 2 hours if A > B (going backwards in time)

// For years = [2000, 1990, 2005, 2050], the output should be solution(years) = 4.
// First, you go from 2000 to 1990, which requires 2 hours.
// Then you go from 1990 to 2005, which requires 1 hour.
// Then you go from 2005 to 2050, which requires 1 hour.
// In total, you need 2+1+1=4 hours.

// For years = [2000, 2021, 2005], the output should be solution(years) = 3.
// First, you go from 2000 to 2021, which requires 1 hour.
// Then you go from 2021 to 2005, which requires 2 hours.
// In total, you need  1+2=3 hours.

class Solution {
    public int timeMachine(int[] years) {
        int res = 0;
        for (int i = 0; i < years.length - 1; i++) {
            if (years[i] < years[i + 1]) {
                res += 1;
            } else if (years[i] > years[i + 1]) {
                res += 2;
            }
        }
        return res;
    }

    public void main(String[] args) {
        int[] years = { 2000, 1990, 2005, 2050 };
        System.out.println(timeMachine(years));
    }
}