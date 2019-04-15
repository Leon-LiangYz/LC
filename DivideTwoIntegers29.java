package BinarySearchRelated;

/*
Given two integers dividend and divisor, divide two integers without using multiplication, division and mod operator.

Return the quotient after dividing dividend by divisor.

The integer division should truncate toward zero.

Example 1:

Input: dividend = 10, divisor = 3
Output: 3
Example 2:

Input: dividend = 7, divisor = -3
Output: -2
Note:

Both dividend and divisor will be 32-bit signed integers.
The divisor will never be 0.
Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. For the purpose of this problem, assume that your function returns 231 − 1 when the division result overflows.

 */

import org.junit.Test;

public class DivideTwoIntegers29 {

    @Test
    public void test() {
        int dividend = -1;
        int dividor = 1;
        int res = divide(dividend, dividor);
        System.out.println(res);
    }

    //Time: O(logn)
    //Space: O(logn)
    public int divide(int dividend, int dividor) {
        if (dividend == 0) {
            return 0;
        }
        int sign = 1;
        if ((dividend < 0 && dividor > 0) ||  (dividend > 0 && dividor < 0)) {
            sign = -1;
        }
        long lAbsDividend = Math.abs((long)dividend);
        long lAbsDividor = Math.abs((long)dividor);
        long lRes = getResult(lAbsDividend, lAbsDividor);
        int res = 0;
        if (lRes > Integer.MAX_VALUE) {
            res = sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        } else {
            res = (int)(sign * lRes);
        }
        return res;
    }

    private long getResult (long dividend, long dividor) {
        if (dividend < dividor) return 0;
        long sum = dividor;
        long multiplicator = 1;
        while ((sum + sum) <= dividend) {
            sum += sum;
            multiplicator += multiplicator;
            //multiplicator++;
        }
        return multiplicator + getResult(dividend - sum, dividor);
    }
}
