package DPRelated;

import org.junit.Test;

/*
Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.

Example 1:

Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
Output: true
Example 2:

Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
Output: false
 */
public class InterleavingString97 {
    @Test
    public void test() {
        String s1 = "aabcc";
        String s2 = "dbbca";
        String s3 = "aadbbcbcac";
        boolean res = isInterleave(s1, s2, s3);
        System.out.println(res);
    }

    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) return false;

        boolean[][] dp = new boolean[s2.length() + 1][s1.length() + 1]; //dp[0][0] si when s1 and s2 are both empty
        dp[0][0] = true;

        for (int i = 1; i < dp.length; i++) {
            dp[i][0] = dp[i - 1][0] && (s2.charAt(i - 1) == s3.charAt(i - 1));
        }

        for (int i = 1; i < dp[0].length; i++) {
            dp[0][i] = dp[0][i - 1] && (s1.charAt(i - 1) == s3.charAt(i - 1));
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                dp[i][j] = (dp[i - 1][j] && (s2.charAt(i - 1) == s3.charAt(i + j - 1)))
                           || (dp[i][j - 1] && (s1.charAt(j - 1)) == s3.charAt(i + j - 1));
            }
        }
        return dp[s2.length()][s1.length()];
    }
}
