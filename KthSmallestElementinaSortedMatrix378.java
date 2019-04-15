package HeapAndPriorityQueueRelated;

/*
Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.

Note that it is the kth smallest element in the sorted order, not the kth distinct element.

Example:

matrix = [
   [ 1,  5,  9],
   [10, 11, 13],
   [12, 13, 15]
],
k = 8,

return 13.
Note:
You may assume k is always valid, 1 ≤ k ≤ n2.


 */

import org.junit.Test;

import java.util.HashSet;
import java.util.PriorityQueue;

public class KthSmallestElementinaSortedMatrix378 {

    @Test
    public void test() {
        int[][] matrix = new int[][]{{1, 3}, {2, 4},{3, 6}};
        //int[][] matrix = new int[][]{{1, 3, 5}, {6, 7, 12}, {11, 13, 14}};
        int k = 6;
        int res = kthSmallest(matrix, k);
        System.out.println(res);

    }
    //利用题目给的特性和min heap, O(klog(min(k, m+n)))
    public int kthSmallest(int[][] matrix, int k) {
        //c.c
        int row = matrix.length;
        int col = matrix[0].length;
        HashSet<Cell> visited = new HashSet<>();
        PriorityQueue<Cell> minHeap = new PriorityQueue<>((a, b) -> (a.val - b.val));

        Cell c = new Cell(0, 0, matrix[0][0]);
        minHeap.offer(c);
        visited.add(c);

        while (--k > 0) {
            Cell cur = minHeap.poll();
            int curRow = cur.x;
            int curCol = cur.y;

            if (curRow + 1 < row) {
                Cell temp = new Cell(curRow + 1, curCol, matrix[curRow + 1][curCol]);
                if (!visited.contains(temp)) {
                    minHeap.offer(temp);
                    visited.add(temp);
                }
            }

            if (curCol + 1 < col) {
                Cell temp = new Cell(curRow, curCol + 1, matrix[curRow][curCol + 1]);
                if (!visited.contains(temp)) {
                    minHeap.offer(temp);
                    visited.add(temp);
                }
            }
        }


        return minHeap.peek().val;
    }

    class Cell {
        int x;
        int y;
        int val;

        public Cell(int r, int c, int val) {
            this.x = r;
            this.y = c;
            this.val = val;
        }

        //很重要，否则hashset无法正确去重
        @Override
        public int hashCode(){
            return this.x * 31 + this.y;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof Cell) {
                Cell c = (Cell)o;
                return this.x == c.x && this.y == c.y;
            } else {
                return false;
            }
        }
    }
}
