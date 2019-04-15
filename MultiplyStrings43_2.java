package StringRelated;

/*
Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.

Example 1:

Input: num1 = "2", num2 = "3"
Output: "6"
Example 2:

Input: num1 = "123", num2 = "456"
Output: "56088"
Note:

The length of both num1 and num2 is < 110.
Both num1 and num2 contain only digits 0-9.
Both num1 and num2 do not contain any leading zero, except the number 0 itself.
You must not use any built-in BigInteger library or convert the inputs to integer directly.
 */

import org.junit.Test;

public class MultiplyStrings43 {
    @Test
    public void test() {
        String num1 = "123";
        //System.out.println(Integer.valueOf('3'));
        String num2 = "456";
        String res = multiply(num1, num2);
        System.out.println(res);
    }

    //Time: O(n * m)
    //Space: O(1)
    //https://leetcode.com/problems/multiply-strings/discuss/17605/Easiest-JAVA-Solution-with-Graph-Explanation
    public String multiply(String num1, String num2) {
        if (num1 == null || num1.length() == 0 || num2 == null || num2.length() == 0) {
            return "0";
        }

        int[] digits = new int[num1.length() + num2.length()];
        for (int i = num1.length() - 1; i >=0; i--) {
            for (int j = num2.length() - 1; j >= 0; j--) {
                int product = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                //int product2 = (Integer.valueOf(num1.charAt(i))) * (Integer.valueOf(num2.charAt(j)));
                int pos1 = i + j; //十位
                int pos2 = pos1 + 1; //个位
                int sum = product + digits[pos2];

                digits[pos1] += sum / 10;
                digits[pos2] = sum % 10;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < digits.length; i++) {
            if (sb.length() == 0 && digits[i] == 0) {
                continue;
            }
            sb.append(digits[i]);
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }
}
