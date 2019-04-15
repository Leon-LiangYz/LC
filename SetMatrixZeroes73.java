package MatrixRelated;

public class SetMatrixZeroes73 {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1,0,0},
                {1,0,1},
                {1,1,1},
        };
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        SetMatrixZeroes73 smz73 = new SetMatrixZeroes73();
        smz73.setZeroes(matrix);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
    public void setZeroes(int[][] matrix) {
        //Time : O(R*C), spaze: O(1)
        //先扫matrix，如果遇到0，将该位置所处的第0行和第0列的对应位置都set 0
        //如果本身第0行或第0列已经有0了，那么要将firstRowHasZero和firstRowHasZero设置为true
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            System.out.println("error!");
            return;
        }

        int numOfRows = matrix.length;
        int numOfCols = matrix[0].length;

        boolean firstRowHasZero = false;
        boolean firstColHasZero = false;

        for (int r = 0; r < numOfRows; r++) {
            for (int c = 0; c < numOfCols; c++) {
                if (matrix[r][c] == 0) {
                    matrix[0][c] = 0;
                    matrix[r][0] = 0;
                    if (r == 0) firstRowHasZero = true;
                    if (c == 0) firstColHasZero = true;
                }
            }
        }

        for (int r = 1; r < numOfRows; r++) {
            if (matrix[r][0] == 0) {
                for (int c = 1; c < numOfCols; c++) {
                    matrix[r][c] = 0;
                }
            }
        }

        for (int c = 1; c < numOfCols; c++) {
            if (matrix[0][c] == 0) {
                for (int r = 1; r < numOfRows; r++) {
                    matrix[r][c] = 0;
                }
            }
        }

        if (firstRowHasZero) {
            for (int c = 0; c < numOfCols; c++) {
                matrix[0][c] = 0;
            }
        }

        if (firstColHasZero) {
            for (int r = 0; r < numOfRows; r++) {
                matrix[r][0] = 0;
            }
        }

    }
}
