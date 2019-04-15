package GraphRelated;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumberOfIslandsII305 {
    @Test
    public void test() {
        int m = 3, n = 3;
        int[][] positions = new int[][]{{0, 0}, {0, 1}, {1, 2}, {2, 1}};
        List<Integer> res = numIslands2(m, n, positions);
        System.out.println(res);
    }

    //Union Find
    //Time: O(m * n + L) ???
    //Space: O(m * n)
    int[][] dirs = new int[][]{{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> res = new ArrayList<>();
        if (m <= 0 || n <= 0) return res;

        int[] roots = new int[m * n]; // one root is one island
        Arrays.fill(roots, -1);

        int count = 0;
        for (int[] pair : positions) {
            int position = n * pair[0] + pair[1]; //二维化成一维
            roots[position] = position;
            count++;

            //traverser all directions
            for (int[] dir : dirs) {
                int newX = pair[0] + dir[0];
                int newY = pair[1] + dir[1];
                int neighborPos = n * newX + newY;
                if (newX < 0 || newX >= m || newY < 0 || newY >= n || roots[neighborPos] == -1) {
                    continue;
                }

                int neighborRoot = findRoot(roots, neighborPos);
                if (position != neighborRoot) { //如果root不相同，需要union到一起
                    roots[position] = neighborRoot;
                    position = neighborRoot;
                    count--;
                }
            }
            res.add(count);
        }
        return res;
    }

    private int findRoot(int[] roots, int neighborPos) {
        while (roots[neighborPos] != neighborPos) {
            roots[neighborPos] = roots[roots[neighborPos]]; // path compression
            neighborPos = roots[neighborPos];
        }
        return neighborPos;
    }


}
