package StringRelated;
/*
Implement strStr().

Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

Example 1:

Input: haystack = "hello", needle = "ll"
Output: 2
Example 2:

Input: haystack = "aaaaa", needle = "bba"
Output: -1
Clarification:

What should we return when needle is an empty string? This is a great question to ask during an interview.

For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to C's strstr() and Java's indexOf().


 */

public class ImplementstrStr28 {
    public static void main(String[] args) {
        String haystack = "hello";
        String needle = "ll";
        ImplementstrStr28 is28 = new ImplementstrStr28();
        int result = is28.strStr(haystack, needle);
        System.out.println(result);
    }
    //Time: O(n*m)
    public int strStr(String haystack, String needle) {
        if (haystack == null || needle == null) {
            return  -1;
        }
        int hsLen = haystack.length();
        int ndLen = needle.length();

        for (int i = 0; i <= hsLen - ndLen; i++) {
            int j = 0;
            for (j = 0; j < ndLen; j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) break;
            }
            if (j == needle.length()) return i;
        }
        return -1;
    }
}
