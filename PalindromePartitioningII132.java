package DPRelated;

/*
Given a string s, partition s such that every substring of the partition is a palindrome.

Return the minimum cuts needed for a palindrome partitioning of s.

Example:

Input: "aab"
Output: 1
Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.

 */





import org.junit.Test;

public class PalindromePartitioningII132 {

    @Test
    public void test() {
        String s = "aab";
        int res = minCut(s);
        System.out.println(res);

    }
    //Time: O(n^2)
    //Space: O(n^2)
    public int minCut(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        //min cut from 0 to current position
        int[] cut = new int[s.length()];

        //if palindrome from i to j
        boolean[][] isPalindrome = new boolean[s.length()][s.length()];

        for (int i = 0; i < s.length(); i++) {
            int tmpMin = i;
            for (int j = 0; j <= i; j++) {
                if (s.charAt(j) == s.charAt(i) && (i - j < 2 || isPalindrome[j + 1][i - 1])) {
                    isPalindrome[j][i] = true;
                    tmpMin = j == 0 ? 0 : Math.min(tmpMin, cut[j - 1] + 1); //think of "aab
                }
            }
            cut[i] = tmpMin;
        }
        return cut[s.length() - 1];
    }
}
