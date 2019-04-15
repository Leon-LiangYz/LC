package DFSRelated;

/*
Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.
The matching should cover the entire input string (not partial).

Note:

s could be empty and contains only lowercase letters a-z.
p could be empty and contains only lowercase letters a-z, and characters like . or *.
Example 1:

Input:
s = "aa"
p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
Example 2:

Input:
s = "aa"
p = "a*"
Output: true
Explanation: '*' means zero or more of the precedeng element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
Example 3:

Input:
s = "ab"
p = ".*"
Output: true
Explanation: ".*" means "zero or more (*) of any character (.)".
Example 4:

Input:
s = "aab"
p = "c*a*b"
Output: true
Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore it matches "aab".
Example 5:

Input:
s = "mississippi"
p = "mis*is*p*."
Output: false
 */

public class RegularExpressionMatching10 {
    public static void main(String[] args) {
        String s = "is";
        String p = "*i";
        RegularExpressionMatching10 rem10 = new RegularExpressionMatching10();
        boolean res = rem10.isMatch2(s, p);
        System.out.println(res);

    }
    //S1: without pruning
    //Time: exponential
    //Space:
    // public boolean isMatch(String s, String p) {
    //     if (s == null || p == null) return false;
    //     return isMatchHelper(s, p, 0, 0);
    // }
    // private boolean isMatchHelper(String s, String p, int indS, int indP) {
    //     if (indP == p.length()) { // base case
    //         return indS == s.length();
    //     } else if (indP == p.length() - 1 || p.charAt(indP + 1) != '*') { // not '*'
    //         if (indS < s.length() && (s.charAt(indS) == p.charAt(indP) || p.charAt(indP) == '.')) {
    //             return isMatchHelper(s, p, indS + 1, indP + 1);
    //         } else {
    //             return false;
    //         }
    //     }else { //is '*'
    //         int i = indS - 1;
    //         while (i < s.length() && (i < indS || p.charAt(indP) == '.' || p.charAt(indP) == s.charAt(i))) {
    //             if (isMatchHelper(s, p, i + 1, indP + 2)) {
    //                 return true;
    //             }
    //             i++;
    //         }
    //         return false;
    //     }
    // }

    //S2: with pruning
    //time: O((m^2) * n), m is length of s, n is length of p
    //Space: O(m*n)
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) return false;
        Boolean[][] cache = new Boolean[s.length() + 1][p.length() + 1];// for pruning
        return isMatchHelper(s, p, 0, 0, cache);
    }
    private boolean isMatchHelper(String s, String p, int indS, int indP, Boolean[][] cache) {
        if (cache[indS][indP] != null) {
            return cache[indS][indP];
        }
        if (indP == p.length()) { // base case
            return indS == s.length();
        } else if (indP == p.length() - 1 || p.charAt(indP + 1) != '*') { // not '*'
            if (indS < s.length() && (s.charAt(indS) == p.charAt(indP) || p.charAt(indP) == '.')) {
                cache[indS][indP] = isMatchHelper(s, p, indS + 1, indP + 1, cache);
            } else {
                cache[indS][indP] = false;
                return false;
            }
        }else { //is '*'
            int i = indS - 1;
            while (i < s.length() && (i < indS || p.charAt(indP) == '.' || p.charAt(indP) == s.charAt(i))) {
                if (isMatchHelper(s, p, i + 1, indP + 2, cache)) {
                    cache[indS][indP] = true;
                    return true;
                }
                i++;
                cache[indS][indP] = false;
            }
        }
        return cache[indS][indP];
    }


    //DP

    /*
      0,  1,  2
      i   s

  0 *

  1 i        f

  2          t
     */

    public boolean isMatch2(String text, String pattern) {
        boolean[][] dp = new boolean[text.length() + 1][pattern.length() + 1];
        dp[text.length()][pattern.length()] = true;

        for (int i = text.length(); i >= 0; i--){
            for (int j = pattern.length() - 1; j >= 0; j--){
                boolean first_match = (i < text.length() &&
                        (pattern.charAt(j) == text.charAt(i) ||
                                pattern.charAt(j) == '.'));
                if (j + 1 < pattern.length() && pattern.charAt(j+1) == '*'){
                    dp[i][j] = dp[i][j+2] || first_match && dp[i+1][j];
                } else {
                    dp[i][j] = first_match && dp[i+1][j+1];
                }
            }
        }
        return dp[0][0];
    }
}
