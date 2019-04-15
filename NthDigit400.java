package MathRelated;
/*
Find the nth digit of the infinite integer sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...

Note:
n is positive and will fit within the range of a 32-bit signed integer (n < 231).

Example 1:

Input:
3

Output:
3
Example 2:

Input:
11

Output:
0


 */

import org.junit.Test;

public class NthDigit400 {
    @Test
    public void test() {
        int n = 5;
        int res = findNthDigit(n);
        System.out.println(res);
    }

    //Time: < O(n)
    //Space: O(1)

    public int findNthDigit(int n) {
        int len = 1;
        long count = 9;
        int start = 1;
        while (n > len * count) {
            n -= len * count;
            len += 1;
            count *= 10;
            start *= 10;
        }

        start += (n - 1) / len;
        String s = Integer.toString(start);
        return s.charAt((n - 1) % len) - '0';
    }
}
