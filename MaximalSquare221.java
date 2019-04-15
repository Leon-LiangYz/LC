package DPRelated;

public class MaximalSquare221 {
    public static void main(String[] args) {
        MaximalSquare221 ms221 = new MaximalSquare221();
        char[][] matrix = new char[][]{{'0', '1', '1'},
                                       {'1', '1', '1'},
                {'1', '1', '1'},{'1', '1', '1'},{'1', '1', '1'},};
//        int res = ms221.maximalSquare(matrix);
//        System.out.println(res);

        int res2 = ms221.maximalSquare2(matrix);
        System.out.println(res2);

    }

    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;

        int[][] dp = new int[matrix.length][matrix[0].length];
        int[] max = new int[]{0};
        maximalSquareHelper(matrix, dp, max);
        return max[0] * max[0];
    }
    private void maximalSquareHelper(char[][] matrix, int[][] dp, int[] max) {
        int row = matrix.length;
        int col = matrix[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = matrix[i][j] - '0';
                } else if (matrix[i][j] == '1'){
                    int up = dp[i - 1][j];
                    int upLeft = dp[i - 1][j - 1];
                    int left = dp[i][j - 1];
                    dp[i][j] = Math.min(Math.min(up, left), upLeft) + 1;
                }
                if (dp[i][j] > max[0]) {
                    max[0] = dp[i][j];
                }
            }
        }
    }

    public int maximalSquare2(char[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        int row = matrix.length;
        int col = matrix[0].length;
        int[] dp = new int[col];
        int max = 0;
        int pre = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
               int temp = dp[j];
               if (i == 0 || j == 0) {
                   dp[j] = matrix[i][j] - '0';
               } else if (matrix[i][j] == '1') {
                   dp[j] = Math.min(Math.min(dp[j - 1], pre), dp[j]) + 1;

               } else {
                   dp[j] = 0;
               }
               pre = temp;
               if (dp[j] > max) {
                    max = dp[j];
               }
            }
        }
        return max * max;
    }
}
