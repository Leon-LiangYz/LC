package DPRelated;

import org.junit.Test;

public class PaintHouseII265 {

    @Test
    public void test() {
        int[][] cost = new int[][]{{1, 5, 3}, {2, 9, 4}};
        int res = minCostII(cost);
        System.out.println(res);
    }
    //Time: O(nk)
    //Space: O(1)
    public int minCostII(int[][] costs) {
        if (costs == null || costs.length == 0 || costs[0].length == 0) {
            return 0;
        }
        int n = costs.length;
        int k = costs[0].length;

        //min1 represents the index of the 1st smallest cost painting the current house
        int min1 = -1, min2 = -1;
        int last1, last2;

        for (int i = 0; i < n; i++) {
            last1 = min1;
            last2 = min2;
            min1 = -1;
            min2 = -1;
            for (int j = 0; j < k; j++) {
                if (j != last1) {
                    costs[i][j] += last1 < 0 ? 0 : costs[i - 1][last1];
                } else {
                    costs[i][j] += last2 < 0 ? 0 : costs[i - 1][last2];
                }

                //update min1 and min2
                if (min1 < 0 || costs[i][j] < costs[i][min1]) {
                    min2 = min1;
                    min1 = j;
                } else if (min2 < 0 || costs[i][j] < costs[i][min2]) {
                    min2 = j;
                }
            }
        }
        return costs[n - 1][min1];
    }
}
