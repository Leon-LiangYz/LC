package DPRelated;
/*
'A' -> 1
'B' -> 2
...
'Z' -> 26
Given a non-empty string containing only digits, determine the total number of ways to decode it.

Example 1:

Input: "12"
Output: 2
Explanation: It could be decoded as "AB" (1 2) or "L" (12).
Example 2:

Input: "226"
Output: 3
Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).

 */


import org.junit.Test;

public class DecodeWays91 {

    @Test
    public void test() {
        String s = "226";
        int res = numDecodings(s);
        System.out.println(res);
    }

    /*
    dp[i] = dp[i-1]  if S[i-1] is a valid char
       or   = dp[i-1]+ dp[i-2]  if S[i-1] and S[i-2] together is still a valid char.
     */

    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int len = s.length();
        int[] dp = new int[len + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) != '0' ? 1 : 0;
        for (int i = 2; i <= s.length(); i++) {
            int first = Integer.valueOf(s.substring(i - 1, i));
            int second = Integer.valueOf(s.substring(i - 2, i));

            if (1 <= first && first <= 9) {
                dp[i] += dp[i - 1];
            }
            if (10 <= second && second <= 26) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[len];
    }

    @Test
    public void test2() {
        String s = "220";
        int res = numDecodings2(s);
        System.out.println(res);
    }

    public int numDecodings2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int c1 = 1;
        int c2 = 1;

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                c1 = 0;
            }
            if (s.charAt(i - 1) == '1' || s.charAt(i - 1) == '2' && s.charAt(i) <= '6') {
                c1 = c1 + c2;
                c2 = c1 - c2; // c2 now is old c1
            } else {
                c2 = c1;
            }
        }
        return c1;
    }
}
