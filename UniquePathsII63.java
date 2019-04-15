package DPRelated;

public class UniquePathsII63 {
    public static void main(String[] args) {
        UniquePathsII63 up63 = new UniquePathsII63();
        int[][] obstacleGrid = new int[][]{{0,0,0},{0,1,0},{0,0,0}};
        int res = up63.uniquePathsWithObstacles(obstacleGrid);
        System.out.println(res);
    }
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null) return 0;
        int row = obstacleGrid.length;
        int col = obstacleGrid[0].length;
        int[][] dp = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i == 0 && j == 0) continue;

                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = -1;
                    continue;
                }
                if (i == 0) {
                    dp[i][j] = Math.max(0, dp[i][j - 1]);
                    if (dp[i][j] >= 0) {
                        dp[i][j] += 1;
                    }
                } else if (j == 0) {
                    dp[i][j] = Math.max(0, dp[i - 1][j]);
                    if (dp[i][j] > 0) {
                        dp[i][j] += 1;
                    }
                } else {
                    int top = Math.max(0, dp[i - 1][j]);
                    int left = Math.max(0, dp[i][j - 1]);
                    if (top == 0 && left == 0) {
                        dp[i][j] = 0;
                    } else if (top == 0) {
                        dp[i][j] = left + 1;
                    } else {
                        dp[i][j] = top + 1;
                    }

                }
            }
        }
        return dp[row - 1][col - 1];
    }
}
