package MatrixRelated;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix54 {
    public static void main(String[] args) {
        SpiralMatrix54 sm54 = new SpiralMatrix54();
        int[][] matrix = new int[][]{
                                    {1,2,3,4,5,6,7,8,9,10}, {11,12,13,14,15,16,17,18,19,20}
                                    };
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
        }
        System.out.println();
        List<Integer> result = sm54.spiralOrder(matrix);
        System.out.println(result);
        List<Integer> resultRec = sm54.spiralOrderRecursion(matrix);
        System.out.println(resultRec);
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return res;
        }

        int rowStart = 0, rowEnd = matrix.length - 1;
        int colStart = 0, colEnd = matrix[0].length - 1;

        while (rowStart <= rowEnd && colStart <= colEnd) {
            for (int i = colStart; i <= colEnd; i++) {
                res.add(matrix[rowStart][i]);
            }
            rowStart++;

            for (int i = rowStart; i <= rowEnd; i++) {
                res.add(matrix[i][colEnd]);
            }
            colEnd--;

            if (rowStart <= rowEnd) {
                for (int i = colEnd; i >= colStart; i--) {
                    res.add(matrix[rowEnd][i]);
                }
            }

            rowEnd--;

            if (colStart <= colEnd) {
                for (int i = rowEnd; i >= rowStart; i--) {
                    res.add(matrix[i][colStart]);
                }
            }

            colStart++;
        }
        return res;
    }

    //S2: recursion
    public List<Integer> spiralOrderRecursion(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return res;
        }
        int rowSize = matrix.length;
        int colSize = matrix[0].length;
        spiralOrderRecursionHelper(matrix, rowSize, colSize, 0, res);
        return res;

    }
    private void spiralOrderRecursionHelper(int[][] matrix, int rowSize, int colSize, int offset, List<Integer> res) {
        if (rowSize <= 0 || colSize <= 0) return;
        if (rowSize == 1) {
            for (int i = 0; i < colSize; i++) {
                res.add(matrix[offset][offset + i]);
            }
            return;
        }
        if (colSize == 1) {
            for (int i = 0; i < rowSize; i++) {
                res.add(matrix[offset + i][offset]);
            }
            return;
        }

        for (int i = 0; i < colSize - 1; i++) {
            res.add(matrix[offset][offset + i]);
        }
        for (int i = 0; i < rowSize - 1; i++) {
            res.add(matrix[offset + i][offset + colSize - 1]);
        }
        for (int i = 0; i < colSize - 1; i++) {
            res.add(matrix[offset + rowSize - 1][colSize - 1 + offset - i]);
        }
        for (int i = 0; i < rowSize - 1; i++) {
            res.add(matrix[rowSize - 1 + offset - i][offset]);
        }
        spiralOrderRecursionHelper(matrix, rowSize - 2, colSize - 2, offset + 1, res);
    }

}
