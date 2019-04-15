package BackTrackingRelated;

/*
Given a pattern and a string str, find if str follows the same pattern.

Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty substring in str.

Example 1:

Input: pattern = "abab", str = "redblueredblue"
Output: true
Example 2:

Input: pattern = pattern = "aaaa", str = "asdasdasdasd"
Output: true
Example 3:

Input: pattern = "aabb", str = "xyzabcxzyabc"
Output: false
Notes:
You may assume both pattern and str contains only lowercase letters.


 */

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;

public class WordPatternII291 {
    @Test
    public void test() {
        String pattern = "abba";
        String str = "redblueredblue";

        boolean res = wordPatternMatch(pattern, str);
        System.out.println(res);
    }

    public boolean wordPatternMatch(String pattern, String str) {
        HashMap<Character, String> map = new HashMap<>();
        HashSet<String> set = new HashSet<>();
        return dfs(str, 0, pattern, 0, map, set);
    }

    public boolean dfs(String str, int indStr, String pattern, int indPat,
                                    HashMap<Character, String> map, HashSet<String> set) {
        if (indStr == str.length() && indPat == pattern.length()) {
            return true;
        }

        if (indStr == str.length() || indPat == pattern.length()) {
            return false;
        }

        int strLen = str.length();
        char c = pattern.charAt(indPat);
        if (map.containsKey(c)) {
            int curLen = map.get(c).length();
            if (curLen + indStr > strLen) return false;
            String target = str.substring(indStr, indStr + curLen);
            return target.equals(map.get(c)) ? dfs(str, indStr + curLen, pattern, indPat + 1,
                    map, set) : false;
        }

        for (int i = indStr; i < strLen; i++) {
            String curStr = str.substring(indStr, i + 1);
            if (set.contains(curStr)) {
                continue;
            }
            map.put(c, curStr);
            set.add(curStr);

            if (dfs(str, i + 1, pattern, indPat + 1, map, set)) {
                return true;
            }
            map.remove(c);
            set.remove(curStr);
        }
        return false;
    }
}
