package BFSRelated;


import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class ZeroOneMatrix542 {


    public static void main(String[] args) {
        int[][] matrix = new int[][]{{0, 0, 0}, {0, 1, 0}, {1, 1, 1}};
        ZeroOneMatrix542 zom542 = new ZeroOneMatrix542();
        zom542.printMatrix(matrix);
        System.out.println("------------------------------------");
        zom542.updateMatrix(matrix);
        int[][] result2 = zom542.updateMatrix2(matrix);
        zom542.printMatrix(result2);
        //zom542.printMatrix(result);
        //zom542.printMatrix(matrix);
    }


    //S1: BFS
    //Time: O(n*m)
    //Space: worst case O(n*m)
    public int[][] updateMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return null;
            // Runtime Exception, 不需要在函数名后check
            //throw new IllegalArgumentException();
        }
        int[][] res = new int[matrix.length][matrix[0].length];
        Queue<Integer> queue = new LinkedList<>();
        int rowTot = matrix.length, colTot = matrix[0].length;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    queue.offer(i * colTot + j);
                }
            }
        }
        int minDist = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int curPoint = queue.poll();
                int i = curPoint / colTot, j = curPoint % colTot;
                int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
                for (int[] direction : directions) {
                    int nextI = i + direction[0];
                    int nextJ = j + direction[1];
                    if (nextI >= 0 && nextI < rowTot && nextJ >= 0 && nextJ < colTot && matrix[nextI][nextJ] == 1
                            && res[nextI][nextJ] == 0) {
                        queue.offer(nextI * colTot + nextJ);
                        res[nextI][nextJ] = minDist;
                    }
                }
            }
            minDist++;
        }
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[0].length; j++) {
                System.out.print(res[i][j] + " ");
                if (j == res[0].length - 1) {
                    System.out.println();
                }
            }
        }

            return res;
    }



    public int[][] updateMatrix2(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return null;
            // Runtime Exception, 不需要在函数名后check
            //throw new IllegalArgumentException();
        }
        int[][] result = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    updateMatrixHelper(matrix, i, j, 0, result);
                }
            }
        }
        return result;
    }
    public void updateMatrixHelper(int[][] matrix, int curRow, int curCol, int distance, int[][] result) {
        //System.out.println("curRow: " + curRow + "curCol: " + " "+ curCol);
        if (curRow < 0 || curRow >= matrix.length || curCol < 0 || curCol >= matrix[0].length
                || (result[curRow][curCol] < distance && matrix[curRow][curCol] != 1)) {
            return ;
        }
        if (matrix[curRow][curCol] != 0) {
            result[curRow][curCol] = distance;
        }


        updateMatrixHelper(matrix, curRow - 1, curCol, distance + 1, result);
        updateMatrixHelper(matrix, curRow + 1, curCol, distance + 1, result);
        updateMatrixHelper(matrix, curRow, curCol - 1, distance + 1, result);
        updateMatrixHelper(matrix, curRow, curCol + 1, distance + 1, result);
    }






    private void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
                if (j == matrix[0].length - 1) {
                    System.out.println();
                }
            }
        }
    }
}
