package OA.Optiver;

// In this problem, you will be given a list of processes, 
// each with a unique ID PID, a start time S - which is the earliest the process can start, 
// and an end time E - which is the latest the process can end. 
// You will also be given a list of inter-process dependencies, PID1 -> PID2 
// indicating that PID1 must start strictly before PID2 can start, and PID2 must end strictly before PID1 can end. 
// Time for the week is divided into integer time units that start from 1 and go up to 10^6 (inclusive).

// The scheduler can only schedule processes to start or stop at the start of a time unit, for example at 1, 2, 1000, 999999 but not at 1.5, 700.1, etc.

// Class Description
// Your task is to implement the Scheduler class which has a constructor and a PrintSchedule method.
// The class constructor has the following parameters:
//      processes[0, ..., N-1]: an array of start-time and end-time pairs
//      dependencies[0, ..., M-1]: an array of pid1 and pid2 pairs

// The PrintSchedule method does not take any parameters.

// PrintSchedule should output the final schedule as N lines, 
// one for each process in ascending order of PID. 
// Each line (i where 1 ≤ i ≤ N) should contain two integers: S, E 
// - the time at which the ith process (i.e., the process that has PID = i) starts and the time at which it ends respectively.

// In the case where a correct schedule is not possible, just output 'IMPOSSIBLE' instead of outputting the N lines.

// Note: In a correct schedule, every process is able to run for at least one time unit.

// Example
// Input
// 1
// 3 2
// 100 2100
// 110 2200
// 1 2
// 3 2

// Output
// 100 2100
// 201 2999
// 200 2330

// Explanation
// From the input:

// N = 3, M = 2
// (S1, E1) = (100, 2100), (S2, E2) = (110, 2200), (S3, E3) = (200, 2330)
// Process 2 depends on processes 1 and 3
// Since processes 1 and 3 have no dependencies, they can be scheduled to run at T=100 and T=200 respectively
// Process 2 can only start strictly after Process 3, so it can be scheduled to run at T=201
// Process 1 should end at 2100, so Process 2 should end strictly before it and the latest it can end is 2099
// Process 3 ends at 2330 according to its end time
class Trading {

}
