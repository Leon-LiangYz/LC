package DPRelated;

/*
The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon. The dungeon consists of M x N rooms laid out in a 2D grid. Our valiant knight (K) was initially positioned in the top-left room and must fight his way through the dungeon to rescue the princess.

The knight has an initial health point represented by a positive integer. If at any point his health point drops to 0 or below, he dies immediately.

Some of the rooms are guarded by demons, so the knight loses health (negative integers) upon entering these rooms; other rooms are either empty (0's) or contain magic orbs that increase the knight's health (positive integers).

In order to reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.



Write a function to determine the knight's minimum initial health so that he is able to rescue the princess.

For example, given the dungeon below, the initial health of the knight must be at least 7 if he follows the optimal path RIGHT-> RIGHT -> DOWN -> DOWN.

-2 (K)	-3	3
-5	-10	1
10	30	-5 (P)


Note:

The knight's health has no upper bound.
Any room can contain threats or power-ups, even the first room the knight enters and the bottom-right room where the princess is imprisoned.
 */

import org.junit.Test;

public class DungeonGame174 {
    @Test
    public void test() {
        int[][] dungeon = new int[][]{{-2, -3, 3}, {-5, -10, 1}, {10, 30, -5}};
        int res = calculateMinimumHP(dungeon);
        System.out.println(res);
    }

    /*
    -2 -3  3
     -5 -10 1
     10 30 -5


     [7, 5, 2]
     [6, 11, 5]
     [1, 1, 6]

     */
    //从后往前DP
    //time : O(m * n)
    //space : O(m * n)
    public int calculateMinimumHP(int[][] dungeon) {
        if (dungeon == null || dungeon.length == 0 || dungeon[0] == null || dungeon[0].length == 0) {
            return 0;
        }

        int row = dungeon.length;
        int col = dungeon[0].length;
        int[][] dp = new int[row][col];

        dp[row - 1][col - 1] = Math.max(1 - dungeon[row - 1][col - 1], 1);

        //initiate last column
        for (int r = row - 2; r >= 0; r--) {
            dp[r][col - 1] = Math.max(dp[r + 1][col - 1] - dungeon[r][col - 1], 1);
        }
        //initiate last row
        for (int c = col - 2; c >= 0; c--) {
            dp[row - 1][c] = Math.max(dp[row - 1][c + 1] - dungeon[row - 1][c], 1);
        }

        for (int r = row - 2; r >= 0; r--) {
            for (int c = col - 2; c >= 0; c--) {
                int down = Math.max(dp[r + 1][c] - dungeon[r][c], 1);
                int right = Math.max(dp[r][c + 1] - dungeon[r][c], 1);
                dp[r][c] = Math.min(down, right);
            }
        }

        return dp[0][0];
    }



}
