package BFSRelated;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent, the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges.

Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.

Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.

Note:
The order of returned grid coordinates does not matter.
Both m and n are less than 150.
Example:

Given the following 5x5 matrix:

  Pacific ~   ~   ~   ~   ~
       ~  1   2   2   3  (5) *
       ~  3   2   3  (4) (4) *
       ~  2   4  (5)  3   1  *
       ~ (6) (7)  1   4   5  *
       ~ (5)  1   1   2   4  *
          *   *   *   *   * Atlantic

Return:

[[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with parentheses in above matrix).

*/


public class PacificAtlanticWaterFlow417 {
    public static void main(String[] args) {
        //int[][] matrix = new int[][]{{1, 2, 2, 3, 5}, {3, 2, 3, 4, 4}, {2, 4, 5, 3, 1}, {6, 7, 1, 4, 5}, {5, 1, 1, 2, 4}};
        int[][] matrix = new int[][]{{1}};
        PacificAtlanticWaterFlow417 pawf417 = new PacificAtlanticWaterFlow417();
        List<int[]> result = pawf417.pacificAtlantic(matrix);
        for (int[] arr : result) {
            System.out.print(arr[0] + ", " + arr[1]);
            System.out.println();
        }

    }
    //BFS
    //Time: O(n*m)
    //Space: O(n+m) ????
    public List<int[]> pacificAtlantic(int[][] matrix) {
        List<int[]> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return res;
        }
        Queue<int[]> queue = new LinkedList<>();
        int rowTot = matrix.length;
        int colTot = matrix[0].length;

        //whether the point can reach Pacific
        boolean[][] pacific = new boolean[rowTot][colTot];
        //whether the point can reach Atlantic
        boolean[][] atlantic = new boolean[rowTot][colTot];

        //all points at the very left column which can go to pacific no matter what
        for (int i = 0; i < rowTot; i++) {
            queue.offer(new int[]{i, 0});
            pacific[i][0] = true;
        }
        //all points at the very first row which can go to pacific no matter what
        for (int j = 1; j < colTot; j++) {
            queue.offer(new int[]{0, j});
            pacific[0][j] = true;
        }

        bfs(matrix, queue, pacific, atlantic, res);

        //all points at the very last column which can go to Atlantic no matter what
        for (int i = 0; i < rowTot; i++) {
            queue.offer(new int[]{i, colTot - 1});
            atlantic[i][colTot - 1] = true;
        }

        //all points at the very last row which can go to Atlantic no matter what
        for (int j = 0; j < colTot - 1; j++) {
            queue.offer(new int[]{rowTot - 1, j});
            atlantic[rowTot - 1][j] = true;
        }
        bfs(matrix, queue, atlantic, pacific, res);

        return res;
    }
    private void bfs(int[][] matrix, Queue<int[]> queue, boolean[][] selfSet, boolean[][] otherSet, List<int[]> res) {
        int rowTot = matrix.length;
        int colTot = matrix[0].length;
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!queue.isEmpty()) {
            int[] curPoint = queue.poll();
            int curRow = curPoint[0];
            int curCol = curPoint[1];
            if (otherSet[curRow][curCol]) {
                res.add(new int[]{curRow, curCol});
            }

            for (int[] direction : directions) {
                int nextRow = curRow + direction[0];
                int nextCol = curCol + direction[1];
                if (nextRow < 0 || nextRow >= rowTot || nextCol < 0 || nextCol >= colTot
                        || matrix[curRow][curCol] > matrix[nextRow][nextCol]) {
                    continue;
                }
                if (!selfSet[nextRow][nextCol]) {
                    queue.add(new int[]{nextRow, nextCol});
                    selfSet[nextRow][nextCol] = true;
                }
            }
        }
    }
}
