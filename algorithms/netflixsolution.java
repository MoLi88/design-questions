// private class Service {
//   int profit;
//   int cpu;
//   Service(int profit, int cpu) {
//     this.cpu = cpu;
//     this.profit = profit;
//   }
// }

// naive solution O(2^m) m is total available service
private static List<Application> result = new LinkedList<Service>();
private int currentProfit = 0;
public static List<Service> helper(int availableCPU, List<Service> services, List<Service> chosenServices, int index, int currentProfit) {
  if (availableCPU < 0) return;
  if (currentProfit > maxProfit) {
    result = new LinkedList<Service>(service);
    maxProfit = currentProfit;
  }
  Service curr = services.get(index);
  int cpu = curr.cpu;
  int profit = curr.profit;
  chooseService(availableCPU, services, chosenServices, index+1, currentProfit); // not choose this service
  if (availableCPU >= cpu) {
    chosenServices.add(curr);
    chooseService(availableCPU-cpu, services, chosenServices, index+1, currentProfit+profit); //choose this service
  }
}

public static List<Service> chooseService(int N, List<Service> services) {
  chooseService(N, services, new LinkedList<Service>, 0, 0);
  return result;
}

//DP + DFS solution O(N * m) m is total available service
public static List<Service> chooseService(int N, List<Service> services) {
  List<Application> result = new LinkedList<Service>();
  int m = services.size();
  int[][] dp = new int[m+1][N+1];
  for (int i = 0; i <= m; i++) { // loop through all services, i == 0 ==> no service
    for (int j = 0; j <= N; j++) {
      if (i == 0 || j == 0) {
        dp[i][j] = 0;
      } else {
        Service service = services.get(i-1);
        int cpu = service.cpu;
        int profit = service.profit;
        dp[i][j] = Math.max(profit + dp[i - 1][j - cpu], dp[i - 1][j]); //knapsack dp
      }
    }
  }

  // use dfs to trace back
  int maxProfit = dp[m][N]; 
  int j = N;
  for (i = m; i > 0 && maxProfit > 0; i--) {
    if (maxProfit == dp[i - 1][j]) {//not selected dp[i-1][w]
      continue; 
    } else { //selected profit + dp[i - 1][j - cpu]
      Service service = services.get(i-1);
      result.add(service);
      maxProfit = maxProfit - service.profit; 
      j = j - service.cpu; 
    }
  }
}