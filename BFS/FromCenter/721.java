package BFS.FromCenter;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, List<String>> emailToEmail = new HashMap<>(); // email : [email]
        Map<String, String> emailToName = new HashMap<>(); // email : name

        for (List<String> list : accounts) {
            String name = list.get(0);
            String rootEmail = list.get(1);
            for (int i = 1; i < list.size(); i++) {
                String email = list.get(i);

                if (!emailToEmail.containsKey(email)) {
                    emailToEmail.put(email, new ArrayList<>());
                }
                if (!emailToEmail.containsKey(rootEmail)) {
                    emailToEmail.put(rootEmail, new ArrayList<>());
                }

                emailToEmail.get(email).add(rootEmail);
                emailToEmail.get(rootEmail).add(email);
                emailToName.put(email, name);
            }
        }

        List<List<String>> res = new ArrayList<>();
        Set<String> visited = new HashSet<>();

        for (String email : emailToEmail.keySet()) {
            if (visited.contains(email)) {
                continue;
            }

            List<String> emailList = new ArrayList<>();
            emailList.add(email);

            Queue<String> q = new ArrayDeque<>();
            q.offer(email);
            visited.add(email);
            while (!q.isEmpty()) {
                String cur = q.poll();
                for (String next : emailToEmail.get(cur)) {
                    if (visited.contains(next)) {
                        continue;
                    }

                    visited.add(next);
                    emailList.add(next);
                    q.offer(next);
                }
            }

            Collections.sort(emailList);
            emailList.add(0, emailToName.get(email));
            res.add(emailList);
        }
        return res;
    }
}