package DPRelated;

import org.junit.Test;

import java.util.Arrays;

public class PerfectSquares279 {

    @Test
    public void test() {
        int n = 13;
        int res = numSquares(n);
        System.out.println(res);
    }
    //time : O(n * sqrt(n))
    //space: O(n)
    public int numSquares(int n) {
        if (n == 0) return 0;

        int[] res = new int[n + 1];
        Arrays.fill(res, Integer.MAX_VALUE);
        res[0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                res[i] = Math.min(res[i], res[i - j * j] + 1);
            }
        }
        return res[n];
    }
}
