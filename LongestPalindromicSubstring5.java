package StringRelated;

public class LongestPalindromicSubstring5 {
    public static void main(String[] args) {
        String s = "cbcd";
        LongestPalindromicSubstring5 lps5 = new LongestPalindromicSubstring5();
        String res = lps5.longestPalindrome(s);
        System.out.println(res);
        String res2 = lps5.longestPalindrome(s);
        System.out.println(res2);
    }
    //S1: 基于中心扩散 O(n^2)
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            //to scan for substring starts from having odd length
            int len1 = expand(s, i, i);
            ////to scan for substring starts from having even length
            int len2 = expand(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }
    private int expand(String s, int left, int right) {
        int lb = left, rb = right;
        while (lb >= 0 && rb < s.length() && s.charAt(lb) == s.charAt(rb)) {
            lb--;
            rb++;
        }
        return rb - lb - 1;
    }
    //S2: DP
    //Time: O(n^2). space: O(n)
    public String longestPalindrome2(String s) {
        if(s == null || s.length() == 0) return s;
             String res = "";
             boolean[][] dp = new boolean[s.length()][s.length()];
             int max = 0;
             for(int j = 0; j < s.length(); j++){
                 for(int i = 0; i <= j; i++){
                     dp[i][j] = s.charAt(i) == s.charAt(j) && ((j - i <= 2) || dp[i + 1][j - 1]);
                     if(dp[i][j]){
                         if(j - i + 1 > max){
                             max = j - i + 1;
                             res = s.substring(i, j + 1);
                         }
                     }
                 }
             }
             return res;
    }
}
