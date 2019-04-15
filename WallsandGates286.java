package BFSRelated;

/*
You are given a m x n 2D grid initialized with these three possible values.

-1 - A wall or an obstacle.
0 - A gate.
INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.

Example:

Given the 2D grid:

INF  -1  0  INF
INF INF INF  -1
INF  -1 INF  -1
  0  -1 INF INF
After running your function, the 2D grid should be:

  3  -1   0   1
  2   2   1  -1
  1  -1   2  -1
  0  -1   3   4

*/


import java.util.LinkedList;
import java.util.Queue;

public class WallsandGates286 {
    public static void main(String[] args) {
        int[][] rooms = new int[][]{
                {Integer.MAX_VALUE, -1, 0, Integer.MAX_VALUE},
                {Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,-1},
                {Integer.MAX_VALUE, -1, Integer.MAX_VALUE, -1},
                {0, -1, Integer.MAX_VALUE,Integer.MAX_VALUE},
        };
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                System.out.print(rooms[i][j] + " ");
                if (j == rooms[0].length - 1) {
                    System.out.println();
                }
            }
        }

        WallsandGates286 wag286 = new WallsandGates286();
        wag286.wallsAndGates(rooms);
        System.out.println();
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                System.out.print(rooms[i][j] + " ");
                if (j == rooms[0].length - 1) {
                    System.out.println();
                }
            }
        }
    }
    //S1: DFS
    //Time: O(n*m)
    //Space: O(n)
    public void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0 || rooms[0] == null || rooms[0].length == 0) {
            // Runtime Exception, 不需要在函数名后check
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j] == 0) {
                    wallsAndGatesHelper(rooms, i, j, 0);
                }
            }
        }
    }
    private void wallsAndGatesHelper(int[][] rooms, int curRow, int curCol, int distance) {
        if (curRow < 0 || curRow >= rooms.length || curCol < 0 || curCol >= rooms[0].length ||
                rooms[curRow][curCol] < distance) {
            return;
        }
        rooms[curRow][curCol] = distance;

        //up
        wallsAndGatesHelper(rooms, curRow - 1, curCol, distance + 1);
        //down
        wallsAndGatesHelper(rooms, curRow + 1, curCol, distance + 1);
        //left
        wallsAndGatesHelper(rooms, curRow, curCol - 1, distance + 1);
        //right
        wallsAndGatesHelper(rooms, curRow, curCol + 1, distance + 1);
    }

    //S2: BFS
    //Time: O(n*m)
    //Space: O(n*m), or O(max(k, n + m)), k is number of zeros(gates)
    public void wallsAndGates2(int[][] rooms) {
        if (rooms == null || rooms.length == 0 || rooms[0] == null || rooms[0].length == 0) {
            // Runtime Exception, 不需要在函数名后check
            throw new IllegalArgumentException();
        }
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j] == 0) {
                    queue.add(new int[]{i, j});
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] curPoint = queue.poll();
            int curRow = curPoint[0];
            int curCol = curPoint[1];

            //up
            if (curRow > 0 && rooms[curRow - 1][curCol] == Integer.MAX_VALUE) {
                rooms[curRow - 1][curCol] = rooms[curRow][curCol] + 1;
                queue.add(new int[]{curRow - 1, curCol});
            }
            //down
            if (curRow < rooms.length - 1 && rooms[curRow + 1][curCol] == Integer.MAX_VALUE) {
                rooms[curRow + 1][curCol] = rooms[curRow][curCol] + 1;
                queue.add(new int[]{curRow + 1, curCol});
            }
            //left
            if (curCol > 0 && rooms[curRow][curCol - 1] == Integer.MAX_VALUE) {
                rooms[curRow][curCol - 1] = rooms[curRow][curCol] + 1;
                queue.add(new int[]{curRow, curCol - 1});
            }
            //down
            if (curCol < rooms[0].length - 1 && rooms[curRow][curCol + 1] == Integer.MAX_VALUE) {
                rooms[curRow][curCol + 1] = rooms[curRow][curCol] + 1;
                queue.add(new int[]{curRow, curCol + 1});
            }
        }
    }
}
