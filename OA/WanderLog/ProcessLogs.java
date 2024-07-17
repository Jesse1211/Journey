package OA.WanderLog;

import java.util.*;

class ProcessLogs {

    /*
     * Complete the 'processLogs' function below.
     *
     * The function is expected to return a STRING_ARRAY.
     * The function accepts following parameters:
     * 1. STRING_ARRAY logs
     * 2. INTEGER maxSpan
     */

    // Example
    // n = 7

    // logs = ["30 99 sign-in", "30 105 sign-out", "12 100 sign-in", "20 80
    // sign-in", "12 120 sign-out", "20 101 sign-out", "21 110 sign-in"]

    // maxSpan = 20
    // The users with id's 30 and 12 were not signed in for more than maxSpan = 20
    // seconds. In sorted numerical order, the return array is ["12", "30"].

    public static List<String> processLogs(List<String> logs, int maxSpan) {
        Map<String, Integer> signInMap = new HashMap<>();
        Map<String, Integer> signOutMap = new HashMap<>();

        for (String log : logs) {
            String[] logArr = log.split(" ");
            String id = logArr[0];
            int time = Integer.parseInt(logArr[1]);
            String action = logArr[2];

            if (action.equals("sign-in")) {
                signInMap.put(id, time);
            } else if (action.equals("sign-out")) {
                signOutMap.put(id, time);
            }
        }

        List<String> res = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : signOutMap.entrySet()) {
            String id = entry.getKey();
            Integer signOutTime = entry.getValue();
            Integer signInTime = signInMap.get(id);

            if (signInTime != null && signOutTime != null && signOutTime - signInTime >= 0
                    && signOutTime - signInTime <= maxSpan) {
                res.add(id);
            }
        }

        Collections.sort(res, (a, b) -> Integer.parseInt(a) - Integer.parseInt(b));
        return res;
    }

}