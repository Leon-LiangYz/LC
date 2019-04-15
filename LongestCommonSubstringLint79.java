package StringRelated;

/*
Given two strings, find the longest common substring.

Return the length of it.

Example
Given A = "ABCD", B = "CBCE", return 2.

Challenge
O(n x m) time and memory.

Notice
The characters in substring should occur continuously in original string. This is different with subsequence.
 */

import org.junit.Test;



public class LongestCommonSubstringLint79 {

    @Test
    public void test1 () {
        String str1 = "ABCBDEEGGKHLVGBHGVABCDE";
        String str2 = "CBCE";
        int res = longestCommonSubstring(str1, str2);
        System.out.println(res);

    }
    public int longestCommonSubstring(String A, String B) {
        // write your code here
        if (A == null || B == null || A.length() == 0 || B.length() == 0) return 0;
        int lenA = A.length(), lenB = B.length();
        int[][] dp = new int[lenA + 1][lenB + 1];
        int res = 0;

        for (int i = 1; i <= lenA; i++) {
            for (int j = 1; j <= lenB; j++) {
                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = 0;
                }
                if (dp[i][j] > res) res = dp[i][j];
            }
        }
        return res;
    }

    @Test
    public void test2() {
        String str1 = "abcd";
        String str2 = "eacb";
        int res = longestCommonSubstring2(str1, str2);
        System.out.println(res);

    }
    public int longestCommonSubstring2(String A, String B) {
        // write your code here
//        if (A == null || B == null || A.length() == 0 || B.length() == 0) return 0;
//        int lenA = A.length(), lenB = B.length();
//        int minLen = Math.min(lenA, lenB);
//        int[] dp = new int[lenB + 1];
//        int pre = 0;
//        int res = 0;
//
//        for (int i = 1; i <= lenA; i++) {
//            for (int j = 1; j <= lenB; j++) {
//                int temp = dp[j];
//                if (A.charAt(i - 1) == B.charAt(j - 1)) {
//                    dp[j] = Math.max(Math.max(dp[j - 1], pre), dp[j]) + 1;
//                }
//                pre = temp;
//                if (dp[j] > res) res = dp[j];
//            }
//        }
//        return res;
        int n = A.length();
        int m = B.length();
        int f[][] = new int[n + 1][m + 1];
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){
                f[i][j] = Math.max(f[i - 1][j], f[i][j - 1]);
                if(A.charAt(i - 1) == B.charAt(j - 1))
                    f[i][j] = f[i - 1][j - 1] + 1;
            }
        }
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < m + 1; j++) {
                System.out.print(f[i][j]);
            }
            System.out.println();
        }
        return f[n][m];
    }
}
