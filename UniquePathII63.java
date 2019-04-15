package DPRelated;

/*
A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

Now consider if some obstacles are added to the grids. How many unique paths would there be?


An obstacle and empty space is marked as 1 and 0 respectively in the grid.

Note: m and n will be at most 100.

Example 1:

Input:
[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
Output: 2
Explanation:
There is one obstacle in the middle of the 3x3 grid above.
There are two ways to reach the bottom-right corner:
1. Right -> Right -> Down -> Down
2. Down -> Down -> Right -> Right
 */

import org.junit.Test;

public class UniquePathII63 {
    @Test
    public void test() {
        int[][] obstacleGrid = new int[][]{{0,0,0},{0,1,0},{0,0,0}};
        int res = uniquePathsWithObstacles(obstacleGrid);
        System.out.println(res);
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null) return 0;
        int row = obstacleGrid.length;
        int col = obstacleGrid[0].length;

        int[] dp = new int[col];
        dp[0] = 1;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[j] = 0;
                } else if (j > 0) {
                    dp[j] += dp[j - 1];
                }
            }
        }
        return dp[col - 1];
    }

    @Test
    public void test2() {
        int[][] obstacleGrid = new int[][]{{1},{0}};
        for (int i = 0; i < obstacleGrid.length; i++) {
            for (int j = 0; j < obstacleGrid[0].length; j++) {
                System.out.print(obstacleGrid[i][j] + " ");
            }
            System.out.println();
        }
        int res = uniquePathsWithObstacles2(obstacleGrid);
        System.out.println(res);
    }

    public int uniquePathsWithObstacles2(int[][] obstacleGrid) {
        return 0;
    }
}
