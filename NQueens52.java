package BackTrackingRelated;

import org.junit.Test;

public class NQueens52 {
    @Test
    public void test() {
        int n = 4;
        int res = totalNQueens(n);

        System.out.println(res);
    }

    public int totalNQueens(int n) {
        if (n <= 0) return 0;
        boolean[] cols = new boolean[n];
        boolean[] diags = new boolean[2 * n];
        boolean[] antiDiags = new boolean[2 * n];
        int[] res = new int[1];
        totalNQueensHelper(n, 0, cols, diags, antiDiags, res);
        return res[0];
    }

    private void totalNQueensHelper(int n, int curRow, boolean[] cols, boolean[] diags, boolean[] antiDiags, int[] res) {
        if (curRow == n) {
            res[0]++;
            return;
        }

        for (int curCol = 0; curCol < n; curCol++) {
            int id1 = curCol - curRow + n;
            int id2 = curCol + curRow;

            if (cols[curCol] || diags[id1] || antiDiags[id2]) {
                continue;
            }
            cols[curCol] = true;
            diags[id1] = true;
            antiDiags[id2] = true;
            totalNQueensHelper(n, curRow + 1, cols, diags, antiDiags, res);
            cols[curCol] = false;
            diags[id1] = false;
            antiDiags[id2] = false;
        }
    }
}
