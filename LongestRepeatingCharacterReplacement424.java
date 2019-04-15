package ArrayRelated;

/*
Given a string that consists of only uppercase English letters, you can replace any letter in the string with another letter at most k times. Find the length of a longest substring containing all repeating letters you can get after performing the above operations.

Note:
Both the string's length and k will not exceed 104.

Example 1:

Input:
s = "ABAB", k = 2

Output:
4

Explanation:
Replace the two 'A's with two 'B's or vice versa.
Example 2:

Input:
s = "AABABBA", k = 1

Output:
4

Explanation:
Replace the one 'A' in the middle with 'B' and form "AABBBBA".
The substring "BBBB" has the longest repeating letters, which is 4.

 */

import org.junit.Test;

public class LongestRepeatingCharacterReplacement424 {

    @Test
    public void test() {
        String s = "JCYACADAER";
        int k = 2;
        int res = characterReplacement(s, k);
        System.out.println(res);
    }

    public int characterReplacement(String s, int k) {
        if (s == null || s.length() == 0 || k < 0) {
            return 0;
        }

        char maxChar = '\0';
        int[] charMap = new int[26];
        int start = 0, res = 0;

        for (int i = 0; i < s.length(); i++) {
            char tmpChar = s.charAt(i);
            charMap[tmpChar - 'A']++;
            if (maxChar == '\0' || charMap[tmpChar - 'A'] > charMap[maxChar - 'A']) {
                maxChar = tmpChar;
            }

            while (i - start + 1 - charMap[maxChar - 'A'] > k) {
                char startChar = s.charAt(start++);
                charMap[startChar - 'A']--;

                if (maxChar == startChar) {
                    for (char c = 'A'; c <= 'Z'; c++) {
                        if (charMap[c - 'A'] > charMap[maxChar - 'A']) {
                            maxChar = c;
                        }
                    }
                }
            }
            res = Math.max(i - start + 1, res);
        }
        return res;
    }
}
