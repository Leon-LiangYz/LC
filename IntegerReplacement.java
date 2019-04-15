package BitRelated;

/*
Given a positive integer n and you can do operations as follow:

If n is even, replace n with n/2.
If n is odd, you can replace n with either n + 1 or n - 1.
What is the minimum number of replacements needed for n to become 1?

Example 1:

Input:
8

Output:
3

Explanation:
8 -> 4 -> 2 -> 1
Example 2:

Input:
7

Output:
4

Explanation:
7 -> 8 -> 4 -> 2 -> 1
or
7 -> 6 -> 3 -> 2 -> 1
 */

import org.junit.Test;

public class IntegerReplacement {

    @Test
    public void test() {
        int n = 5;
        int res = integerReplacement(n);
        System.out.println(res);
    }
    //Bit

    /*
    如果倒数第二位是0, 那么n - 1的操作比n+1的操作能消掉更多的1
    1001 + 1 = 1010
    1001 - 1 = 1000
    如果倒数第二位是1, 那么n + 1的操作比n-1的操作能消掉更多的1
    1011 + 1 = 1100
    1111 + 1 = 10000

     */

    //Bit
//    time : 接近O(logn)
//    space : O(1)
    public int integerReplacement(int n) {
        long nLong = n;
        int res = 0;
        while (nLong != 1) {
            if (nLong % 2 == 0) {
                nLong >>= 1;
            } else {
                if (nLong == 3) {//3需要单独判断，否则下面会被加到4，这样就多了一步
                    res += 2;
                    break;
                }
                nLong = (nLong & 2) == 2 ? nLong + 1 : nLong - 1;
            }
            res++;
        }
        return res;
    }



    @Test
    public void test2() {
        int n = 5;
        int res = integerReplacement2(n);
        System.out.println(res);
    }


    public int integerReplacement2(int n) {
        if (n == Integer.MAX_VALUE) return 32;
        int res = 0;
        while (n != 1) {
            if (n % 2 == 0) {
                n /= 2;
            } else {
                if ( (n + 1) % 4 == 0 && (n - 1 != 2)) {
                    n++;
                } else n--;
            }
            res++;
        }
        return res;
    }
}
