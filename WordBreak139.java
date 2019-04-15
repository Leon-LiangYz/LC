package DFSRelated;


/*
Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

Note:

The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.
Example 1:

Input: s = "leetcode", wordDict = ["leet", "code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".
Example 2:

Input: s = "applepenapple", wordDict = ["apple", "pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
             Note that you are allowed to reuse a dictionary word.
Example 3:

Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
Output: false

 */

import java.util.*;

public class WordBreak139 {
    public static void main(String[] args) {
        WordBreak139 wb139 = new WordBreak139();
        String s = "leetcode";
        String[] dictionary = new String[]{"leet", "code"};
        List<String> wordDict = new ArrayList<>();
        for (String str : dictionary) {
            wordDict.add(str);
        }
        boolean res = wb139.wordBreak(s, wordDict);
        System.out.println(res);

        boolean resDP = wb139.wordBreakDP(s, wordDict);
        System.out.println(resDP);
    }
    //DFS, 可以转化为DP
    //Time: N^2, 加了记忆化存储
    //Space: O(n), length of s
    public boolean wordBreak(String s, List<String> wordDict) {
        if (s == null) return false;
        if (wordDict == null || wordDict.size() == 0) return false;
        HashSet<String> dictionary = new HashSet<>();
        for (String str : wordDict) {
            dictionary.add(str);
        }
        Boolean[] cache = new Boolean[s.length() + 1];

        return wordBreakHelper(s, dictionary, 0, cache);
    }
    private boolean wordBreakHelper(String s, HashSet<String> dictionary, int start, Boolean[] cache) {
        if (start == s.length()) {
            return true;
        }
        if (cache[start] != null) return cache[start];
        for (int i = start + 1; i <= s.length(); i++) {
            String tmpStr = s.substring(start, i);
            if (dictionary.contains(tmpStr)) {
                if (wordBreakHelper(s, dictionary, i, cache)) {
                    cache[start] = true;
                    return true;
                }
            }

        }
        cache[start] = false;
        return false;
    }

    //S2: DP
    public boolean wordBreakDP(String s, List<String> wordDict) {
        if (s == null) return false;
        //dp[i] represents if we can form the string, whose index from 0 to i - 1, with words from dict
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        if (wordDict == null || wordDict.size() == 0) return false;
        HashSet<String> dictionary = new HashSet<>();
        for (String str : wordDict) {
            dictionary.add(str);
        }
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] == true && dictionary.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }

        }
        return dp[s.length()];
    }
    public boolean wordBreakDP2(String s, List<String> wordDict) {
        if (s == null) return false;
        //dp[i] represents if we can form the string, whose index from 0 to i, with words from dict
        boolean[] dp = new boolean[s.length()];
        //dp[0] = true;
        if (wordDict == null || wordDict.size() == 0) return false;
        HashSet<String> dictionary = new HashSet<>();
        for (String str : wordDict) {
            dictionary.add(str);
        }
        for (int i = 0; i < s.length(); i++) {
            //String leftStr = s.substring(0, i + 1);
            if (dictionary.contains(s.substring(0, i + 1))) {
                dp[i] = true;
            } else {
                for (int j = 0; j < i; j++) {

                    if (dp[j] == true && dictionary.contains(s.substring(j + 1, i + 1))) {
                        dp[i] = true;
                        break;
                    }
                }
            }
        }
        return dp[s.length() - 1];
    }

}
