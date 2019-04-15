package BFSRelated;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

public class TheMazeII505 {
    @Test
    public void test() {
        int[][] maze = new int[][]{{0,0,1,0,0}, {0,0,0,0,0},{0,0,0,1,0},{1,1,0,1,1}, {0,0,0,0,0}};
        int[] start = new int[]{0, 4};
        int[] destination = new int[]{4, 4};
        int res = shortestDistance(maze, start, destination);
        System.out.println(res);
    }

    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        if (maze == null || maze.length == 0 || maze[0] == null || maze[0].length == 0) {
            return 0;
        }
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(start);
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        int res = 0;



        return 0;



    }
}
