package MatrixRelated;

public class RotateImage48 {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9},
        };
        RotateImage48 ri48 = new RotateImage48();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j]);
                if (j == matrix[0].length - 1) {
                    System.out.println();
                }
            }
        }
        ri48.rotate(matrix);

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j]);
                if (j == matrix[0].length - 1) {
                    System.out.println();
                }
            }
        }
    }
    public void rotate(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return;
        }

        for (int r = 0; r < matrix.length; r++) {
            for (int c = r; c < matrix[0].length; c++) {
                int tmpVal = matrix[r][c];
                matrix[r][c] = matrix[c][r];
                matrix[c][r] = tmpVal;
            }
        }

        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[0].length / 2; c++) {
                int tmpVal = matrix[r][c];
                matrix[r][c] = matrix[r][matrix[0].length - c - 1];
                matrix[r][matrix[0].length - c - 1] = tmpVal;
            }
        }
    }
}
