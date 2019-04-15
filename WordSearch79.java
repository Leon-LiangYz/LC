package MatrixRelated;

public class WordSearch79 {
    public static void main(String[] args) {
//        boolean[] test = new boolean[3];
//        test[0] = true;
//        int index = 0;
//        System.out.println(index);
//        if(test[++index]) {
//            System.out.println(index);
//        }
//        System.out.println(index);

        char[][] board = new char[][]{
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}
        };
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        String word = "ABCCED";
        System.out.println("String: " + word);
        WordSearch79 ws79 = new WordSearch79();
        boolean result = ws79.exist(board, word);
        System.out.println(result);
    }

    private boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) {
            return false;
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (existHelper(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }
    private boolean existHelper(char[][] board, String word, int curRow, int curCol, int start) {
        if (start >= word.length()) return true;
        if (curRow < 0 || curRow >= board.length || curCol < 0 || curCol >= board[0].length) {
            return false;
        }
        if (board[curRow][curCol] == word.charAt(start)) {
            char tmpChar = board[curRow][curCol];
            board[curRow][curCol] = '#';
            boolean res = existHelper(board, word, curRow + 1, curCol, start + 1) ||
                          existHelper(board, word, curRow - 1, curCol, start + 1) ||
                          existHelper(board, word, curRow, curCol + 1, start + 1) ||
                          existHelper(board, word, curRow, curCol - 1, start + 1);
            board[curRow][curCol] = tmpChar;
            return res;
        }
        return false;
    }
}
