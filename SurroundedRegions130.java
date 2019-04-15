package BFSRelated;

/*
Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

Example:

X X X X
X O O X
X X O X
X O X X
After running your function, the board should be:

X X X X
X X X X
X X X X
X O X X
Explanation:

Surrounded regions shouldn’t be on the border, which means that any 'O' on the border of the board are not flipped to 'X'. Any 'O' that is not on the border and it is not connected to an 'O' on the border will be flipped to 'X'. Two cells are connected if they are adjacent cells connected horizontally or vertically.


 */

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

public class SurroundedRegions130 {

    //BFS
    @Test
    public void test() {
        char[][] board = new char[][]{{'X', 'O', 'X', 'X'},
                                      {'O', 'X', 'O', 'X'},
                                      {'X', 'O', 'X', 'O'},
                                      {'O', 'X', 'O', 'X'},
                                      {'X', 'O', 'X', 'O'},
                                      {'O', 'X', 'O', 'X'}};
        System.out.println("Before:");
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }

        solve(board);

        System.out.println("After:");
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }
    //S1: BFS
    //Time: O(m*n)
    //Space: O(1)
    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) return;

        Queue<int[]> queue = new LinkedList<>();

        int rowTot = board.length;
        int colTot = board[0].length;


        //find O in first and last columns
        for (int r = 0; r < rowTot; r++) {
            if (board[r][0] == 'O') {
                board[r][0] = '1';
                queue.offer(new int[]{r, 0});
            }

            if (board[r][colTot - 1] == 'O') {
                board[r][colTot - 1] = '1';
                queue.offer(new int[]{r, colTot - 1});
            }
        }

        for (int c = 0; c < colTot; c++) {
            if (board[0][c] == 'O') {
                board[0][c] = '1';
                queue.offer(new int[]{0, c});
            }

            if (board[rowTot - 1][c] == 'O') {
                board[rowTot - 1][c] = '1';
                queue.offer(new int[]{rowTot - 1, c});
            }
        }

        solvBFS(board, rowTot, colTot, queue);

        for (int r = 0; r < rowTot; r++) {
            for (int c = 0; c < colTot; c++) {
                if (board[r][c] == 'O') board[r][c] = 'X';
                if (board[r][c] == '1') board[r][c] = 'O';
            }
        }


    }
    private void solvBFS(char[][] board, int rowTot, int colTot, Queue<int[]> queue) {
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) return;

        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            int row = pos[0], col = pos[1];

            //{-1, 0}
            if (row - 1 >= 0 && board[row - 1][col] == 'O') {
                board[row - 1][col] = '1';
                queue.offer(new int[]{row - 1, col});
            }
            //{1, 0}
            if (row + 1 < rowTot && board[row + 1][col] == 'O') {
                board[row + 1][col] = '1';
                queue.offer(new int[]{row + 1, col});
            }
            //{0, -1}
            if (col - 1 >= 0 && board[row][col - 1] == 'O') {
                board[row][col - 1] = '1';
                queue.offer(new int[]{row, col - 1});
            }
            //{0, 1}
            if (col + 1 < colTot && board[row][col + 1] == 'O') {
                board[row][col + 1] = '1';
                queue.offer(new int[]{row, col + 1});
            }
        }
    }


    //DFS
    @Test
    public void test2() {
        char[][] board = new char[][]{{'X', 'O', 'X', 'X'},
                {'O', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'O'},
                {'O', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'O'},
                {'O', 'X', 'O', 'X'}};
        System.out.println("Before:");
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }

        solve(board);

        System.out.println("After:");
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

    //S2: DFS
    //Time: O(m*n)
    //Space: O(m*n) system space

    public void solve2(char[][] board) {
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) return;


        int rowTot = board.length;
        int colTot = board[0].length;


        //find O in first and last columns
        for (int r = 0; r < rowTot; r++) {
            if (board[r][0] == 'O') {
                solveDFS(board, r, 0);
            }

            if (board[r][colTot - 1] == 'O') {
                solveDFS(board, r, colTot - 1);
            }
        }

        for (int c = 0; c < colTot; c++) {
            if (board[0][c] == 'O') {
                solveDFS(board, 0, c);
            }

            if (board[rowTot - 1][c] == 'O') {
                solveDFS(board, rowTot - 1, c);
            }
        }



        for (int r = 0; r < rowTot; r++) {
            for (int c = 0; c < colTot; c++) {
                if (board[r][c] == 'O') board[r][c] = 'X';
                if (board[r][c] == '1') board[r][c] = 'O';
            }
        }


    }
    private void solveDFS(char[][] board, int row, int col) {
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) return;
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length || board[row][col] != 'O') {
            return;
        }
        board[row][col] = '1';


        solveDFS(board, row - 1, col);
        solveDFS(board, row + 1, col);
        solveDFS(board, row, col - 1);
        solveDFS(board, row , col + 1);

    }

}
