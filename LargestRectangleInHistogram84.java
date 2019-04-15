package StackRelated;

import java.util.Stack;

public class LargestRectangleInHistogram84 {
    public static void main(String[] args) {
        int[] heights = new int[]{1,2,3};
        System.out.print("Original array: ");
        for (Integer num : heights) {
            System.out.print(num + " ");
        }
        System.out.println();
        LargestRectangleInHistogram84 LRIH84 = new LargestRectangleInHistogram84();
        int res = LRIH84.largestRectangleArea(heights);
        System.out.println();
        System.out.print("Result: " + res);
    }
    private int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) return 0;
        int res = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i <= heights.length; i++) {
            int hi = i == heights.length ? 0 : heights[i];
            System.out.print("hi: " + hi + " ");
            while (!stack.isEmpty() && hi < heights[stack.peek()]) {
                int height = heights[stack.pop()];
                int start = stack.isEmpty() ? -1 : stack.peek();
                int area = height * (i - start - 1);
                System.out.println(area);
                res = Math.max(res, area);
            }
            stack.push(i);
        }
        return res;
    }

    //S2:
     public int largestRectangleArea2(int[] heights) {
         if (heights == null || heights.length == 0) return 0;
         int res = 0;
         for (int i = 0; i < heights.length; i++) {
             int minHeight = Integer.MAX_VALUE;
             for (int j = i; j < heights.length; j++) {
                 minHeight = Math.min(minHeight, heights[j]);
                 res = Math.max(res, minHeight * (j - i + 1));
             }
         }
         return res;
     }
}
