package hard.dp;

// Problem https://leetcode.com/problems/paint-house-iii/
// Solution https://leetcode.com/problems/paint-house-iii/solution/
public class PaintHouseIII {

    final int MAX_COST = 1000001;
    Integer[][][] memo = new Integer[100][100][21];
    public int helper(int idx, int[] houses, int[][] cost,int target, int prevColor,int neigh,Integer[][][] dp)
    {
        if(idx==houses.length || neigh>target)
        {
            if(neigh==target)
                return 0;
            return Integer.MAX_VALUE;
        }
        if(dp[idx][prevColor][neigh]!=null)
            return dp[idx][prevColor][neigh];
        int minCost = Integer.MAX_VALUE;

        if(houses[idx]==0)
        {
            for(int j = 0;j<cost[idx].length;j++)
            {
                int minCostHere = Integer.MAX_VALUE;

                if(j+1==prevColor) // Painting the house with the same colour as that of the previous one.
                    minCostHere = helper(idx+1,houses,cost,target,prevColor,neigh,dp);

                else // Painting the house with a different color and incrementing the neighbour count.
                    minCostHere = helper(idx+1,houses,cost,target,j+1,neigh+1,dp);

                if(minCostHere!=Integer.MAX_VALUE)
                    minCostHere+=cost[idx][j];

                minCost = Math.min(minCostHere,minCost);
            }
        }
        else
        {
            if(houses[idx]==prevColor)
                minCost = helper(idx+1,houses,cost,target,prevColor,neigh,dp);
            else
                minCost = helper(idx+1,houses,cost,target,houses[idx],neigh+1,dp);
        }

        return dp[idx][prevColor][neigh] = minCost;

    }
    public int minCost1(int[] houses, int[][] cost, int m, int n, int target) {

        Integer[][][] dp = new Integer[m][n+1][target+1];
        int ans = helper(0,houses,cost,target,0,0,dp);
        return ans==Integer.MAX_VALUE?-1:ans;
    }

    public int minCost2(int[] houses, int[][] cost, int m, int n, int target) {
        return minCostRec(houses, cost, m, n, 0, 0, 0, target);
    }

    // Confusing to me why my solution doesn't work ?
    public int minCostRec(int[] houses, int[][] cost, int m, int n, int idx, int prevColor, int sets, int target) {
        if(idx == m)
            return sets == target ? 0 : Integer.MAX_VALUE;
        if(sets > target)
            return Integer.MAX_VALUE;

        int min = Integer.MAX_VALUE;
        // House Previously Painted
        if(houses[idx] != 0) {
            int newNeighborhoodCount = sets + (houses[idx] != prevColor ? 1 : 0);
            min = minCostRec(houses, cost, m, n, idx+1, houses[idx], newNeighborhoodCount, target);
        }
        // House not painted yet
        else {
            for(int i=1;i<=n;i++) {
                int newNeighborhoodCount = sets + (i != prevColor ? 1 : 0);
                min = Math.min(min, cost[idx][i-1] + minCostRec(houses, cost, m, n, idx+1, i, newNeighborhoodCount, target));
            }
        }
        return min;
    }

    int findMinCost(int[] houses, int[][] cost, int targetCount, int currIndex,
                    int neighborhoodCount, int prevHouseColor) {
        if (currIndex == houses.length) {
            // If all houses are traversed, check if the neighbor count is as expected or not
            return neighborhoodCount == targetCount ? 0 : MAX_COST;
        }

        if (neighborhoodCount > targetCount) {
            // If the neighborhoods are more than the threshold, we can't have target neighborhoods
            return MAX_COST;
        }

        // We have already calculated the answer so no need to go into recursion
        if (memo[currIndex][neighborhoodCount][prevHouseColor] != null) {
            return memo[currIndex][neighborhoodCount][prevHouseColor];
        }

        int minCost = MAX_COST;
        // If the house is already painted, update the values accordingly
        if (houses[currIndex] != 0) {
            int newNeighborhoodCount = neighborhoodCount + (houses[currIndex] != prevHouseColor ? 1 : 0);
            minCost =
                    findMinCost(houses, cost, targetCount, currIndex + 1, newNeighborhoodCount, houses[currIndex]);
        } else {
            int totalColors = cost[0].length;

            // If the house is not painted, try every possible color and store the minimum cost
            for (int color = 1; color <= totalColors; color++) {
                int newNeighborhoodCount = neighborhoodCount + (color != prevHouseColor ? 1 : 0);
                int currCost = cost[currIndex][color - 1]
                        + findMinCost(houses, cost, targetCount, currIndex + 1, newNeighborhoodCount, color);
                minCost = Math.min(minCost, currCost);
            }
        }

        // Return the minimum cost and also storing it for future reference (memoization)
        return minCost;
    }

    public int minCost(int[] houses, int[][] cost, int m, int n, int target) {
        int answer = findMinCost(houses, cost, target, 0, 0, 0);
        // Return -1 if the answer is MAX_COST as it implies no answer possible
        return answer == MAX_COST ? -1 : answer;
    }
}
