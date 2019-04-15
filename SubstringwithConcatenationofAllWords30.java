package StringRelated;

//You are given a string, s, and a list of words, words, that are all of the same length. Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.
//
//        Example 1:
//
//        Input:
//        s = "barfoothefoobarman",
//        words = ["foo","bar"]
//        Output: [0,9]
//        Explanation: Substrings starting at index 0 and 9 are "barfoor" and "foobar" respectively.
//        The output order does not matter, returning [9,0] is fine too.
//        Example 2:
//
//        Input:
//        s = "wordgoodstudentgoodword",
//        words = ["word","student"]
//        Output: []

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Time: O(n*m), space: O(n)
public class SubstringwithConcatenationofAllWords30 {
    public static void main(String[] args) {
        String s = "goodgoodbestword";
        String[] words = new String[]{"word","good","best","good"};
        SubstringwithConcatenationofAllWords30 swcaw30 = new SubstringwithConcatenationofAllWords30();
        List<Integer> result = swcaw30.findSubstring2(s, words);
        System.out.print(result);
    }
    private List<Integer> findSubstring(String s, String[] words) {
        if (s == null || s.length() == 0 || words == null || words.length == 0) return new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        HashMap<String, Integer> memo = new HashMap<>();
        for (String str : words) {
            if (memo.containsKey(str)) {
                memo.put(str, memo.get(str) + 1);
            } else {
                memo.put(str, 1);
            }
        }
        for (Map.Entry e : memo.entrySet()) {
            System.out.print(e);
        }
        int wordsLen = words.length;
        int wordLen = words[0].length();
        for (int i = 0; i <= s.length() - wordLen * wordsLen; i++) {
            HashMap<String, Integer> backup = new HashMap<>(memo);
            int counter = wordsLen;
            int start = i;
            while (counter > 0) {
                String tmpStr = s.substring(start, start + wordLen);
                if (!backup.containsKey(tmpStr) || backup.get(tmpStr) <= 0) {
                    break;
                }
                backup.put(tmpStr, backup.get(tmpStr) - 1);
                start += wordLen;
                counter--;
            }
            if (counter == 0) {
                result.add(i);
            }
        }
        return  result;
    }
    private List<Integer> findSubstring2(String s, String[] words) {
        if (s == null || s.length() == 0 || words == null || words.length == 0) return new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        HashMap<String, Integer> memo = new HashMap<>();

        for (String str : words) {
            if (memo.containsKey(str)) {
                memo.put(str, memo.get(str) + 1);
            } else {
                memo.put(str, 1);
            }
        }

        int wordLen = words[0].length();
        int wordsLen = words.length;

        for (int i = 0; i <= s.length() - wordLen * wordsLen; i++) {
            HashMap<String, Integer> temp = new HashMap<>(memo);
            int counter = wordsLen;
            int curStart = i;
            while (counter > 0) {
                String curStr = s.substring(curStart, curStart + wordLen);
                if (temp.containsKey(curStr)) {
                    temp.put(curStr, temp.get(curStr) - 1);
                    counter--;
                    if (temp.get(curStr) == 0) {
                        temp.remove(curStr);
                    }
                    curStart += wordLen;
                } else {
                    break;
                }
            }
            if (counter == 0) {
                result.add(i);
            }


        }
        return result;
    }
}
