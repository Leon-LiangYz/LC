package DFSRelated;

/*
Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.

Note:

The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.
Example 1:

Input:
s = "catsanddog"
wordDict = ["cat", "cats", "and", "sand", "dog"]
Output:
[
  "cats and dog",
  "cat sand dog"
]
Example 2:

Input:
s = "pineapplepenapple"
wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
Output:
[
  "pine apple pen apple",
  "pineapple pen apple",
  "pine applepen apple"
]
Explanation: Note that you are allowed to reuse a dictionary word.
Example 3:

Input:
s = "catsandog"
wordDict = ["cats", "dog", "sand", "and", "cat"]
Output:
[]

*/

import java.util.*;

public class WordBreakII140 {
    public static void main(String[] args) {
        String s = "aaabaa";
        String[] Dict = new String[]{"aaaa", "aaa", "aa"};
        List<String> wordDict = new ArrayList<>();
        for (String str : Dict) {
            wordDict.add(str);
        }
        WordBreakII140 wbII140 = new WordBreakII140();
        List<String> res = wbII140.wordBreak(s, wordDict);
        System.out.println(res);
    }
    //Time: exponential
    //Space: O(n)
    public List<String> wordBreak(String s, List<String> wordDict) {
        HashSet<String> set = new HashSet<>();
        for (String str : wordDict) {
            set.add(str);
        }
        //cache[i] means whether from i to the res characters will be added to the result list
        //although we add cache here, it does not reduce case when all characters combination in strings can be found
        //in dictionary
        //so adding cache here only reduce constant factor
        //the big O time complexity is still exponential
        boolean[] cache = new boolean[s.length() + 1];
        Arrays.fill(cache, true);
        List<String> res = new ArrayList<>();
        wordBreakHelper(s, set, res, 0, new StringBuilder(), cache);
        return res;
    }
    private void wordBreakHelper(String s, HashSet<String> dict, List<String> res, int start, StringBuilder sb,
                                 boolean[] cache) {
        if (start == s.length()) {
            res.add(sb.toString());
            return;
        }
        int curResSize = res.size();
        for (int i = start + 1; i <= s.length(); i++) {
            int sbCurLen = sb.length();
            String tmpStr = s.substring(start, i);
            if (dict.contains(tmpStr) && cache[i]) {
                if (sb.length() > 0) {
                    sb.append(" " + tmpStr);
                } else {
                    sb.append(tmpStr);
                }
                wordBreakHelper(s, dict, res, i, sb, cache);
                sb.setLength(sbCurLen);
            }
        }
        if (curResSize == res.size()) {
            cache[start] = false;
        }
    }
//    public List<String> wordBreak(String s, List<String> wordDict) {
//        List<String> res = new ArrayList<>();
//        int len = s.length();
//        boolean[] m = new boolean[len + 1];
//        Arrays.fill(m, true);
//        HashSet<String> dict = new HashSet<>();
//        for (String word : wordDict) {
//            dict.add(word);
//        }
//        wordBreakHelper(res, new StringBuilder(), s, 0, dict, m);
//        return res;
//    }
//    private void wordBreakHelper(List<String> res, StringBuilder path, String s, int idx, HashSet<String> dict,
//                                 boolean[] m) {
//        int len = s.length();
//        if (idx == len) {
//            res.add(path.toString());
//            return;
//        }
//        int curSize = res.size();
//        for (int i = idx + 1; i <= s.length(); i++) {
//            String str = s.substring(idx, i);
//            if (dict.contains(str) && m[i]) {
//                int lenPath = path.length();
//                if (lenPath == 0) {
//                    path.append(str);
//                } else {
//                    path.append(" " + str);
//                }
//                wordBreakHelper(res, path, s, i , dict, m);
//                path.setLength(lenPath);
//            }
//        }
//        if (curSize == res.size()) {
//            m[idx] = false;
//        }
//    }
}
