package BFSRelated;
/*
There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.

Given the ball's start position, the destination and the maze, determine whether the ball could stop at the destination.

The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. The start and destination coordinates are represented by row and column indexes.



Example 1:

Input 1: a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (4, 4)

Output: true

Explanation: One possible way is : left -> down -> left -> down -> right -> down -> right.

Example 2:

Input 1: a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (3, 2)

Output: false

Explanation: There is no way for the ball to stop at the destination.



Note:

There is only one ball and one destination in the maze.
Both the ball and the destination exist on an empty space, and they will not be at the same position initially.
The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.
The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100.

 */

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

public class TheMaze490 {
    @Test
    public void test() {
        int[][] maze = new int[][]{{0,0,1,0,0}, {0,0,0,0,0},{0,0,0,1,0},{1,1,0,1,1}, {0,0,0,0,0}};
        int[] start = new int[]{0, 4};
        int[] destination = new int[]{4, 4};
        boolean res = hasPath(maze, start, destination);
        System.out.println(res);
    }

    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        if (maze == null || maze.length == 0 || maze[0] == null || maze[0].length == 0) {
            return false;
        }
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        return dfsHelper(maze, start[0], start[1], destination, visited);
    }
    private boolean dfsHelper(int[][] maze, int curRow, int curCol, int[] destination, boolean[][] visited) {
        if (curRow == destination[0] && curCol == destination[1]) {
            return true;
        }
        if (curRow < 0 || curRow >= maze.length || curCol < 0 || curCol >= maze[0].length
                || visited[curRow][curCol]) {
            return false;
        }

        visited[curRow][curCol] = true;
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        for (int[] dir : directions) {
            int newRow = curRow;
            int newCol = curCol;
            while (isValidDFS(maze, newRow + dir[0], newCol + dir[1])) {
                newRow += dir[0];
                newCol += dir[1];
            }
            if (!visited[newRow][newCol]) {
                if (dfsHelper(maze, newRow, newCol, destination,visited)) {
                    return true;
                }
            }
        }
        return false;
    }
    private boolean isValidDFS(int[][] maze, int curX, int curY) {
        return curX >= 0 && curX < maze.length && curY >= 0
                && curY < maze[0].length && maze[curX][curY] == 0;
    }


//    int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
//
//    private boolean dfs(int[][] maze, int[] current, int[] destination, boolean[][] visited) {
//        if (current[0] == destination[0] && current[1] == destination[1]) return true;
//        int x = current[0], y = current[1];
//        if (x < 0 || y < 0 || x > maze.length || y > maze[0].length || visited[x][y]) return false;
//        visited[x][y] = true;
//        for (int i = 0; i < directions.length; i++) {
//            int xx = x, yy = y;
//            while (xx >= 0 && xx < maze.length && yy >= 0 && yy < maze[0].length && maze[xx][yy] == 0) {
//                xx += directions[i][0]; yy += directions[i][1];
//            }
//            if (dfs(maze, new int[]{xx-directions[i][0], yy-directions[i][1]}, destination, visited)) return true;
//        }
//        return false;
//    }
//
//    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
//        if (maze.length == 0 || maze[0].length == 0) return false;
//        return dfs(maze, start, destination, new boolean[maze.length][maze[0].length]);
//    }


    @Test
    public void test2() {
        int[][] maze = new int[][]{{0,0,1,0,0}, {0,0,0,0,0},{0,0,0,1,0},{1,1,0,1,1}, {0,0,0,0,0}};
        int[] start = new int[]{0, 4};
        int[] destination = new int[]{4, 4};
        boolean res = hasPath2(maze, start, destination);
        System.out.println(res);
    }

    //BFS
    //time : O(m * n)
    //space : O(m * n)

    public boolean hasPath2(int[][] maze, int[] start, int[] destination) {
        if (maze == null || maze.length == 0 || maze[0] == null || maze[0].length == 0) {
            return false;
        }
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[maze.length][maze[0].length];

        int r = start[0], c = start[1];
        visited[r][c] =true;
        queue.offer(new int[]{r, c});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int curX = cur[0], curY = cur[1];
            visited[curX][curY] = true;
            if (curX == destination[0] && curY == destination[1]) {
                return true;
            }
            for (int[] dir : directions) {
                int newX = curX;
                int newY = curY;
                int dX = dir[0];
                int dY = dir[1];
                while (isValid(maze, newX + dX, newY + dY)) {
                    newX += dX;
                    newY += dY;
                }
                if (!visited[newX][newY]) {
                    queue.offer(new int[]{newX, newY});
                }
            }
        }
        return false;
    }
    private boolean isValid(int[][] maze, int curX, int curY) {
        return curX >= 0 && curX < maze.length && curY >= 0
                && curY < maze[0].length && maze[curX][curY] == 0;
    }


}
