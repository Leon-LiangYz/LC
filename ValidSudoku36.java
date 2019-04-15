package MatrixRelated;

import java.util.HashSet;

public class ValidSudoku36 {
    public static void main(String[] args) {
        char[] c = new char[]{'a'};
        System.out.println(new String(c));
        char[][] board = new char[][]{
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
        };
        ValidSudoku36 vs36 = new ValidSudoku36();
//        boolean result = vs36.isValidSudoku(board);
//        System.out.println(result);

        boolean result2 = vs36.isValidSudoku2(board);
        System.out.println(result2);

    }
    public boolean isValidSudoku(char[][] board) {
        //Time : O(n^2), O(9 * 9), space O(n)
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) {
            return false;
        }

        for (int i = 0; i < board.length; i++) {
            HashSet<Character> rows = new HashSet<>();
//            for (char c : rows) {
//                System.out.println(c);
//            }
            //System.out.println("111");
            HashSet<Character> cols = new HashSet<>();
            HashSet<Character> cube = new HashSet<>();
            System.out.println("Start print: ");
            for (int j = 0; j < board[0].length; j++) {
                //System.out.println("i: " + i +" " + "j: " + j);
                if (board[i][j] != '.' && !rows.add(board[i][j])) return false;
//                for (char c : rows) {
//                    System.out.println(c);
//                }
//                System.out.println("222");
                if (board[j][i] != '.' && !cols.add(board[j][i])) return false;
//                for (char c : rows) {
//                    System.out.println(c);
//                }
                int rowIndex = 3 * (i / 3);
                int colIndex = 3 * (i % 3);
                System.out.println("rowIndex: " + (rowIndex + j / 3) +" " + "colIndex: " + (colIndex + j % 3));
                if (board[rowIndex + j / 3][colIndex + j % 3] != '.' && !cube.add(board[rowIndex + j / 3][colIndex + j % 3])) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isValidSudoku2(char[][] board) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (board[row][col] == '.') continue;
                if (!isValidSudoku2Helper(board, row, col)) return false;
            }
        }
        return true;
    }
    public boolean isValidSudoku2Helper(char[][] board, int curRow, int curCol) {
        for (int tmpRowIndex = 0; tmpRowIndex < board.length; tmpRowIndex++) {
            if(tmpRowIndex == curRow) continue;
            if (board[tmpRowIndex][curCol] == board[curRow][curCol]) return false;
        }
        for (int tmpColIndex = 0; tmpColIndex < board[0].length; tmpColIndex++) {
            if(tmpColIndex == curCol) continue;
            if (board[curRow][tmpColIndex] == board[curRow][curCol]) return false;
        }
        for (int tmpRowIndex = (curRow / 3) * 3; tmpRowIndex < (curRow / 3 + 1) * 3; tmpRowIndex++) {
            for (int tmpColIndex = (curCol / 3) * 3; tmpColIndex < (curCol / 3 + 1) * 3; tmpColIndex++) {
                if (tmpRowIndex == curRow && tmpColIndex == curCol) continue;;
                if (board[tmpRowIndex][tmpColIndex] == board[curRow][curCol]) return false;
            }
        }
        return true;
    }

//    public boolean isValidSudoku2(char[][] board) {
//        for (int i = 0; i < board.length; i++) {
//            for (int j = 0; j < board[0].length; j++) {
//                if (board[i][j] == '.') continue;
//                if (!valid(board, i, j)) return false;
//            }
//        }
//        return true;
//    }
//
//    public boolean valid(char[][] board, int i, int j) {
//        for (int row = 0; row < board.length; row++) {
//            if (row == i) continue;
//            if (board[row][j] == board[i][j]) return false;
//        }
//        for (int col = 0; col < board[0].length; col++) {
//            if (col == j) continue;
//            if (board[i][col] == board[i][j]) return false;
//        }
//        for (int row = (i / 3) * 3; row < (i / 3 + 1) * 3; row++) {
//            for (int col = (j / 3) * 3; col < (j / 3 + 1) * 3; col++) {
//                if (row == i && col == j) continue;
//                if (board[row][col] == board[i][j]) return false;
//            }
//        }
//        return true;
//    }

}
