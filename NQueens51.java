package BackTrackingRelated;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class NQueens51 {

    @Test
    public void test() {
        int n = 4;
        List<List<String>> res = solveNQueens(n);
        System.out.println(res);
    }

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        if (n <= 0) return res;
        int[] memo = new int[n]; //memorize the column position of Q at each row
        helper(res, memo, 0);
        return res;
    }
    public void helper(List<List<String>> res, int[] queens, int pos) {
        if (pos == queens.length) {
            addSolution(res, queens);
            return;
        }

        for (int i = 0; i < queens.length; i++) {
            queens[pos] = i;
            if (isValid(queens, pos)) {
                helper(res, queens, pos + 1);
            }
        }
    }

    public boolean isValid(int[] queens, int pos) {
        for (int i = 0; i < pos; i++) {
            if (queens[pos] == queens[i]) { // check if current Q is attacked by previous Qs in the same col
                return false;
            } else if (Math.abs(queens[pos] - queens[i]) == Math.abs(pos - i)) {// check if current Q is attacked by previous Qs from the same diagonal
                return false;
            }
        }
        return true;
    }

    public void addSolution(List<List<String>> res, int[] queens) {
        List<String> tmpList = new ArrayList<>();
        for (int i = 0; i < queens.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < queens.length; j++) {
                if (queens[i] == j) {
                    sb.append('Q');
                } else {
                    sb.append('.');
                }
            }
            tmpList.add(sb.toString());
        }
        res.add(tmpList);
    }

}
