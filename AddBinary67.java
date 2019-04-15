package StringRelated;

/*
Given two binary strings, return their sum (also a binary string).

The input strings are both non-empty and contains only characters 1 or 0.

Example 1:

Input: a = "11", b = "1"
Output: "100"
Example 2:

Input: a = "1010", b = "1011"
Output: "10101"

 */

import org.junit.Test;

public class AddBinary67 {

    @Test
    public void test() {
        String a = "11";
        String b = "1";
        String res = addBinary(a, b);
        System.out.println(res);
    }
    //time : O(max(m,n));
    //space : O(n);

    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int indA = a.length() - 1;
        int indB = b.length() - 1;
        int carry = 0;
        while (indA >= 0 || indB >= 0) {
            int sum = carry;
            if (indA >= 0) {
                sum += a.charAt(indA--) - '0';
            }
            if (indB >= 0) {
                sum += b.charAt(indB--) - '0';
            }
            sb.append(sum % 2);
            carry = sum / 2;
        }
        if (carry != 0) {
            sb.append(carry);
        }
        return sb.reverse().toString();
    }
}
