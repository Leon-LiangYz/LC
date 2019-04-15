package MatrixRelated;

import java.util.PriorityQueue;

public class KthSmallestElementinaSortedMatrix378 {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1,2},
                {105,195},

        };
        int k = 3;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        KthSmallestElementinaSortedMatrix378 ksesm378 = new KthSmallestElementinaSortedMatrix378();
        int result = ksesm378.kthSmallest(matrix, k);
        System.out.println(result);

        int result2 = ksesm378.kthSmallest2(matrix, k);
        System.out.println("Result 2: " + result2);
    }
    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<Tuple> tuplePQ = new PriorityQueue<>(matrix[0].length, (a, b) -> (a.val - b.val));
        for (int i = 0; i < matrix[0].length; i++) {
            tuplePQ.offer(new Tuple(0, i, matrix[0][i]));
        }
        for (int i = 0; i < k - 1; i++) {
            Tuple tmpTuple = tuplePQ.poll();
            if (tmpTuple.x == matrix.length - 1) continue;
            tuplePQ.offer(new Tuple(tmpTuple.x + 1, tmpTuple.y, matrix[tmpTuple.x + 1][tmpTuple.y]));
        }
        return tuplePQ.poll().val;
    }

    class Tuple {
        int x, y, val;
        public Tuple (int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }
    }

    //S2: Binary Search: 数个数
    //需要知道matrix中的min and max value后求中间值，数有多少个比中间值小的数的个数
    //Time: O(n * log(max val of matrix - min val of matrix), space O(1),
    public int kthSmallest2(int[][] matrix, int k) {
        int rowLen = matrix.length;
        int colLen = matrix[0].length;
        int left = matrix[0][0];
        int right = matrix[rowLen - 1][colLen -1];
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            int num = count(matrix, mid);
            if (num >= k) right = mid;
            else left = mid;
        }
        if (count(matrix, right) <= k - 1) return right;
        return left;
    }
    private int count(int[][] matrix, int target) {
        int res = 0;
        int rowInd = matrix.length - 1, colInd = 0;
        while (rowInd >= 0 && colInd < matrix[0].length) {
            if (matrix[rowInd][colInd] < target) {
                res += rowInd + 1;
                colInd++;
            } else {
                rowInd--;
            }
        }
        return res;
    }


}
