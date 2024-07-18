package OA.Optiver;

// Each log will have a log ID and timestamp. We want to keep track of these logs, 
// but due to storage limitations, we can only return up to m logs from the last hour when requested.

// Complete the following functions:
//      recordLog(logId,timestamp): Records a new log entry.
            // Each log is represented as an object with a logId and timestamp.
            // The logId is an identifier for each log, and the timestamp is an integer in seconds representing when the log was sent.
            // Logs may be received out of order.
            // The logId is not guaranteed to be unique - the same logId can be used for different logs.

//      getLogs():
            // Returns a comma-separated string of the latest m logIds from the last hour ascending by timestamp.
            // In the event of a timestamp tie,order from earliest received to latest.
            // Return a string of the form "logId1,logId2,logId3,logId4" 
            // where logId4 is the latest timestamp log and logId1 is the earliest timestamp log received <1 hour before logId4'stimestamp.

//      getLogCount():
            // Returns the total number of logs received <1 hour from the most recently stored log timestamp.
            // In the event more than m logs have been received still return the full count of logs.

// Example
// Input
    // 100
    // 10
    // RECORD 1 0
    // RECORD 2 300
    // GET_LOGS
    // COUNT
    // RECORD 3 1200
    // RECORD 1 1800
    // GET_LOGS
    // COUNT
    // RECORD 4 3900
    // GET_LOGS

// Output
    // 1,2
    // 2
    // 1,2,3,1
    // 4
    // 3,1,4

// Explanation
    // RECORD 1 0: The log with ID 1 at timestamp 0 is recorded.
    // RECORD 2 300: The log with ID 2 at timestamp 300 is recorded.
    // GET_LOGS: Retrieves the latest logs within the last hour. The function should return "1,2".
    // COUNT: Returns the count of logs within the last hour. The function should return 2.
    // RECORD 3 1200: The log with ID 3 at timestamp 1200 is recorded.
    // RECORD 1 1800: The log with ID 1 at timestamp 1800 is recorded.
    // GET_LOGS: Retrieves the latest logs within the last hour. The function should return "1,2,3,1".
    // COUNT: Returns the count of logs within the last hour. The function should return 4.
    // RECORD 4 3900: The log with ID 4 at timestamp 3900 is recorded.
    // GET_LOGS: Retrieves the latest logs within the last hour. The function should return "3,1,4".
class RecordLog {

}
