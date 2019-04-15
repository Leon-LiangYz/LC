package BitRelated;


import org.junit.Test;

/*
Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.

Example 1:

Input: [5,7]
Output: 4
Example 2:

Input: [0,1]
Output: 0
 */
public class BitwiseANDofNumbersRange201 {

    @Test
    public void test(){
        int m = 5;
        int n = 7;
        int res = rangeBitwiseAnd(m, n);
        System.out.println(res);
    }
    //找相同的prefix 比特位
    //Time: O(1)
    public int rangeBitwiseAnd(int m, int n) {
        if (m == n) return m & n;
        int i = 0;
        while (m != n) {
            m >>= 1;
            n >>= 1;
            i++;
        }
        return m << i;
    }



    @Test
    public void test2(){
        int m = 5;
        int n = 7;
        int res = rangeBitwiseAnd2(m, n);
        System.out.println(res);
    }
    //brutal force O(n)
    public int rangeBitwiseAnd2(int m, int n) {
        if (m == n) return m & n;

        int val1 = m, val2 = m + 1;
        int res = 0;
        while (val2 <= n) {
            res = val1 & val2;
            val2++;
            val1 = res;
        }

        return res;
    }
}
