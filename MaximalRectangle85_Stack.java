package StackRelated;

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

import java.util.Stack;

public class MaximalRectangle85_Stack {
    @Test
    public void test() {
        char[][] matrix = new char[][]{{'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}};
        //matrix = new char[][]{{'1', '0', '1'}, {'0', '1', '1'}};
        int res = maximalRectangle(matrix);
        System.out.println(res);
    }

    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }

        int row = matrix.length;
        int col = matrix[0].length;
        int[] height = new int[col];

        int res = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == '1') {
                    height[j]++;
                } else {
                    height[j] = 0;
                }
                int curArea = maxArea(height);
                res = Math.max(res, curArea);
            }
        }
        return res;
    }
    private int maxArea(int[] height) {
        int len = height.length;
        int res = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i <= len; i++) {
            int curHeight = i < len ? height[i] : 0;
            while (!stack.isEmpty() && height[stack.peek()] > curHeight) {
                int curArea = height[stack.pop()] * (stack.isEmpty() ? i : (i - stack.peek() - 1));
                res = Math.max(res, curArea);
            }
            stack.push(i);
        }
        return res;
    }



    public int maximalRectangle2(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }

        int row = matrix.length;
        int col = matrix[0].length;
        int[] height = new int[col + 1];
        height[col] = 0;

        int res = 0;
        for (int i = 0; i < row; i++) {
            Stack<Integer> stack = new Stack<>();
            for (int j = 0; j <= col; j++) {
                if (j < col) {
                    if (matrix[i][j] == '1') {
                        height[j]++;
                    } else {
                        height[j] = 0;
                    }
                }
                if (stack.isEmpty() || height[stack.peek()] <= height[j]) {
                    stack.push(j);
                } else {
                    while (!stack.isEmpty() && height[stack.peek()] > height[j]) {
                        int curArea = height[stack.pop()] * (stack.isEmpty() ? j : (j - stack.peek() - 1));
                        res = Math.max(res, curArea);
                    }
                    stack.push(j);
                }
            }
        }
        return res;
    }
}
