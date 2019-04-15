package StringRelated;
/*
Given a string s , find the length of the longest substring t  that contains at most 2 distinct characters.

Example 1:

Input: "eceba"
Output: 3
Explanation: t is "ece" which its length is 3.
Example 2:

Input: "ccaabbb"
Output: 5
Explanation: t is "aabbb" which its length is 5.
 */

import org.junit.Test;

public class LongestSubstringwithAtMostTwoDistinctCharacters159 {

    @Test
    public void test() {
        String s = "ccaabbb";
        int res = lengthOfLongestSubstringTwoDistinct(s);
        System.out.println(res);
    }
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s == null || s.length() == 0) return 0;

        int idx1 = -1, idx2 = -1;
        int c1 = '0', c2 = '0';
        int start = 0, res = 0;

        for (int i = 0; i < s.length(); i++) {
            char tmpChar = s.charAt(i);

            if (tmpChar == c1) {
                idx1 = i;
            } else if (tmpChar == c2) {
                idx2 = i;
            } else {
                if (idx1 < idx2) {
                    start = idx1 + 1;
                    c1 = tmpChar;
                    idx1 = i;
                } else {
                    start = idx2 + 1;
                    c2 = tmpChar;
                    idx2 = i;
                }
            }
            res = Math.max(res, i - start + 1);
        }

        return res;
    }
}
