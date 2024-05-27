package OA;

// https://www.fastprep.io/problems/tiktok-maximize-upgraded-servers
class Solution {
    public int[] maximizeUpgradedServers(int[] num_servers, int[] money, int[] upgrade, int[] sell) {
        // num_servers num of server - money get - upgrade money cost - sell 价值
        int n = num_servers.length;
        int fund = 0;
        for (int i = 0; i < n; i++) {
            int num_server = num_servers[i];
            fund += money[i];
            int num_update = 0;
            while (num_server-- > 0) {
                System.out.println(fund);
                if (fund < upgrade[i]) {
                    fund += sell[i];
                } else {
                    fund -= upgrade[i];
                    num_update += 1;
                }
            }
            num_servers[i] = num_update;
        }
        return num_servers;
    }

}
