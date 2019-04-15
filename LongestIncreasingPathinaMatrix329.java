package MatrixRelated;

public class LongestIncreasingPathinaMatrix329 {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1,0,0},
                {1,0,1},
                {1,1,1},
        };
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        LongestIncreasingPathinaMatrix329 lipiam329 = new LongestIncreasingPathinaMatrix329();
        int result = lipiam329.longestIncreasingPath(matrix);
        System.out.println(result);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
    //Time: O(m*n), 虽然在两层for loops中，但是每一次dfs实际都赋了值，所以其实之后的loop中都是hit到cache了
    //space: O(m*n)
    //DP不太好，因为状态依赖分支太多，规律性不强，如果一定要DP,需要排序，time变为mnlog(mn)
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            System.out.println("error!");
            return 0;
        }
        int res = 0;
        int rowTotal = matrix.length;
        int colTotal = matrix[0].length;
        int[][] cache = new int[rowTotal][colTotal];

        for (int r = 0; r < rowTotal; r++) {
            for (int c = 0; c < colTotal; c++) {
                int max = dfs(matrix, Integer.MIN_VALUE, r, c, rowTotal, colTotal, cache);
                res = Math.max(res, max);
            }
        }
        return res;
    }
    private int dfs(int[][] matrix, int curMin, int curRow, int curCol,
                    int rowTotal, int colTotal, int[][] cache) {
        if (curRow < 0 || curCol < 0 || curRow >= rowTotal || curCol >= colTotal || matrix[curRow][curCol] <= curMin) {
            return 0;
        }

        if (cache[curRow][curCol] != 0) {
            return cache[curRow][curCol];
        }

        curMin = matrix[curRow][curCol];

        int toUp = dfs(matrix, curMin, curRow - 1, curCol, rowTotal, colTotal, cache) + 1;
        int toDown = dfs(matrix, curMin, curRow + 1, curCol, rowTotal, colTotal, cache) + 1;
        int toLeft = dfs(matrix, curMin, curRow, curCol - 1, rowTotal, colTotal, cache) + 1;
        int toRight = dfs(matrix, curMin, curRow, curCol + 1, rowTotal, colTotal, cache) + 1;

        int curMax = Math.max(toUp, Math.max(toDown, Math.max(toLeft, toRight)));
        cache[curRow][curCol] = curMax;
        return curMax;
    }
}
