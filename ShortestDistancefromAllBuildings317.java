package BFSRelated;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestDistancefromAllBuildings317 {
    public static void main(String[] args) {
        int[][] grid  = new int[][]{{1,0,2,0,1}, {0,0,0,0,0}, {0,0,1,0,0}};
        ShortestDistancefromAllBuildings317 sdfab317 = new ShortestDistancefromAllBuildings317();
        sdfab317.printMatrix(grid);
        int res = sdfab317.shortestDistance(grid);
        System.out.println(res);
    }
    //BFS
    //Time : O(n * m)
    //Space: O(n * m)
    //Follow up: LC 296 what if no wall
    public int shortestDistance(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return -1;
            // Runtime Exception, 不需要在函数名后check
            //throw new IllegalArgumentException();
        }
        int[][] counter = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    shortestDistanceHelper(grid, i, j, counter);
                }
            }
        }
        int minLen = Integer.MAX_VALUE;
        for (int i = 0; i < counter.length; i++) {
            for (int j = 0; j < counter[0].length; j++) {
                if ( grid[i][j] == 0 && counter[i][j] > 0) {
                    minLen = Math.min(minLen, counter[i][j]);
                }
            }
        }
        if (minLen == Integer.MAX_VALUE) {
            //throw new IllegalArgumentException();
            return -1;
        } else {
            return minLen;
        }
        //return minLen == Integer.MAX_VALUE ? throw new IllegalArgumentException()  : minLen;
    }

    //BFS
    //Time : O(n * m)
    //Space: O(n * m)
    private void shortestDistanceHelper(int[][] grid, int curRow, int curCol, int[][] counter) {
        int rowTot = grid.length;
        int colTot = grid[0].length;
        boolean[][] visited = new boolean[rowTot][colTot];
        int[][] directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{curRow, curCol});
        int distance = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] curPoint = queue.poll();
                counter[curPoint[0]][curPoint[1]] += distance;
                for (int[] direction : directions) {
                    int nextI = curPoint[0] + direction[0];
                    int nextJ = curPoint[1] + direction[1];
                    if (nextI >= 0 && nextI < rowTot && nextJ >= 0 && nextJ < colTot
                            && grid[nextI][nextJ] == 0 && visited[nextI][nextJ] == false) {
                        queue.offer(new int[]{nextI, nextJ});
                        visited[nextI][nextJ] = true;
                    }
                }
            }
            distance++;
        }
        // Set value of 2 for all zeros which cannot reach out to all ones
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0 && !visited[i][j]) {
                    grid[i][j] = 2;
                }
            }
        }
    }
    private void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
                if (j == matrix[0].length - 1) {
                    System.out.println();
                }
            }
        }
    }
}
