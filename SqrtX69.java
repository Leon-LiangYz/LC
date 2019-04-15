package MathRelated;

import org.junit.Test;

public class SqrtX69 {
    @Test
    public void test() {
        int x = 8;
        int res = mySqrt(x);
        System.out.println(res);
    }
    public int mySqrt(int x) {
        if (x <= 0) return 0;
        int low = 1, high = x;
        while (low <= high) {
            long mid = low + (high - low) / 2;

            if (mid * mid == x) {
                return (int) mid;
            } else if (mid * mid < x) {
                low = (int) mid + 1;
            } else {
                high = (int) mid - 1;
            }
        }
        if (high * high < x) {
            return high;
        }
        return low;
    }
}
