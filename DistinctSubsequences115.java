package DPRelated;
/*
Given a string S and a string T, count the number of distinct subsequences of S which equals T.

A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).

Example 1:

Input: S = "rabbbit", T = "rabbit"
Output: 3
Explanation:

As shown below, there are 3 ways you can generate "rabbit" from S.
(The caret symbol ^ means the chosen letters)

rabbbit
^^^^ ^^
rabbbit
^^ ^^^^
rabbbit
^^^ ^^^
Example 2:

Input: S = "babgbag", T = "bag"
Output: 5
Explanation:

As shown below, there are 5 ways you can generate "bag" from S.
(The caret symbol ^ means the chosen letters)

babgbag
^^ ^
babgbag
^^    ^
babgbag
^    ^^
babgbag
  ^  ^^
babgbag
    ^^^
 */





import org.junit.Test;

public class DistinctSubsequences115 {
    @Test
    public void test() {
        String s = "rabbbit";
        String t = "rabbit";
        int res = numDistinct(s, t);
        System.out.println(res);
    }

    /*
          r  a  b  b  i  t
       1, 0, 0, 0, 0, 0, 0
     r 1, 1, 0, 0, 0, 0, 0
     a 1, 1, 1, 0, 0, 0, 0
     b 1, 1, 1, 1, 0, 0, 0
     b 1, 1, 1, 2, 1, 0, 0
     b 1, 1, 1, 3, 3, 0, 0
     i 1, 1, 1, 3, 3, 3, 0
     t 1, 1, 1, 3, 3, 3, 3

     */


    //Time: O(m*n)
    //Space: O(1)
    public int numDistinct(String s, String t) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int[][] dp = new int[s.length() + 1][t.length() + 1];
        for (int i = 0; i < s.length(); i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= t.length(); j++) {
                if (s.charAt(i - 1) != t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1];
                }
            }
        }
        return dp[s.length()][t.length()];
    }
}
