package DPRelated;

public class MinimumPathSum64 {
    public static void main(String[] args) {
        int[][] grid = new int[][]{ {1,3}, {6, 2}};
        MinimumPathSum64 mps64 = new MinimumPathSum64();
        int res = mps64.minPathSum(grid);
        System.out.println(res);

        int res2 = mps64.minPathSum2(grid);
        System.out.println(res2);
    }
    //Time: O(m*n)
    //Space: O(m*n)
    public int minPathSum(int[][] grid) {
        if (grid == null) return 0;
        int row = grid.length;
        int col = grid[0].length;
        int[][] dp = new int[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i == 0 && j == 0) {
                    dp[0][0] = grid[0][0];
                    continue;
                } else if (i == 0) {
                    dp[i][j] = grid[i][j] + dp[i][j - 1];
                }else if (j == 0) {
                    dp[i][j] = grid[i][j] + dp[i - 1][j];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
                }
            }
        }
        return dp[row - 1][col - 1];
    }

    //S2:
    //TIme: O(m*n)
    //Space: O(m)
    //dp(j)=grid(i,j)+min(dp(j),dp(j+1))
    public int minPathSum2(int[][] grid) {
        if (grid == null) return 0;
        int row = grid.length;
        int col = grid[0].length;
        int[] dp = new int[col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i == 0 && j == 0) {
                    dp[0] = grid[0][0];
                } else if (i == 0) {
                    dp[j] = grid[i][j] + dp[j - 1];
                }else if (j == 0) {
                    dp[j] = grid[i][j] + dp[j];
                } else {
                    dp[j] = Math.min(dp[j], dp[j - 1]) + grid[i][j];
                }
            }
        }
        return dp[col - 1];
    }
}
