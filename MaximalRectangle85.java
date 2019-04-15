package DPRelated;
/*
Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

Example:

Input:
[
  ["1","0","1","0","0"],
  ["1","0","1","1","1"],
  ["1","1","1","1","1"],
  ["1","0","0","1","0"]
]
Output: 6

 */

import org.junit.Test;

import java.util.Arrays;

public class MaximalRectangle85 {

    @Test
    public void test() {
        char[][] matrix = new char[][]{{'1', '0', '1', '0', '0'},
                                       {'1', '0', '1', '1', '1'},
                                       {'1', '1', '1', '1', '1'},
                                       {'1', '0', '0', '1', '0'}};
        matrix = new char[][]{{'1', '0', '1'}, {'0', '1', '1'}};
        int res = maximalRectangle(matrix);
        System.out.println(res);
    }

    //DP

    /*
     left[] ：从左到右，出现连续‘1’的string的第一个座标
     right[] ：从右到左, 出现连续‘1’的string的最后一个座标，
     height[] ： 从上到下的高度。
     res ： (right[j] - left[j]) * heights[j]

     */

    /*
    height:
     1 0 1 0 0
     2 0 2 1 1
     3 1 3 2 2
     4 0 0 3 0
     left:
     0 0 2 0 0
     0 0 2 2 2
     0 0 2 2 2
     0 0 0 3 0
     right:
     1 5 3 5 5
     1 5 3 5 5
     1 5 3 5 5
     1 5 5 4 5

     */

    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }
        int row = matrix.length;
        int col = matrix[0].length;

        //left[i] is the first position when there is consecutive '0'
        int[] left = new int[col];
        //right[i] is the last position when there is consecutive '0'
        int[] right = new int[col];
        Arrays.fill(right, col);
        //height
        int[] height = new int[col];

        int res = 0;
        for (int i = 0; i < row; i++) {
            int curLeft = 0, curRight = col;
            //calculate left
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == '1') {
                    left[j] = Math.max(curLeft, left[j]);
                } else {
                    left[j] = 0;
                    curLeft = j + 1;
                }
            }

            //calculate right
            for (int j = col - 1; j >=0; j--) {
                if (matrix[i][j] == '1') {
                    right[j] = Math.min(curRight, right[j]);
                } else {
                    right[j] = col;
                    curRight = j;
                }
            }

            //calculate height
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == '1') {
                    height[j]++;
                } else {
                    height[j] = 0;
                }
            }

            for (int j = 0; j < col; j++) {
                res = Math.max(res, (right[j] - left[j]) * height[j]);
            }
        }
        return res;
    }

    /*
    1, 0, 1
    1, 1, 1
     */
}
