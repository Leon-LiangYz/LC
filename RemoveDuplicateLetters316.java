package StringRelated;
/*
Given a string which contains only lowercase letters, remove duplicate letters so that every letter appear once and only once. You must make sure your result is the smallest in lexicographical order among all possible results.

Example 1:

Input: "bcabc"
Output: "abc"
Example 2:

Input: "cbacdcbc"
Output: "acdb"
 */

import org.junit.Test;

import java.util.HashMap;


public class RemoveDuplicateLetters316 {

    @Test
    public void test() {
        String s = "cbacdcbc";

        String res = removeDuplicateLetters(s);
        System.out.println(res);
    }

    //Time: O(n)
    //Space: O(distinct char counts)
    public String removeDuplicateLetters(String s) {
        if (s == null || s.length() == 0) return "";
        if (s.length() == 1) return s;

        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), i);
        }
        char[] res = new char[map.size()];

        int start = 0, end = findLastMinPos(map);

        for (int i = 0; i < res.length; i++) {
            char minChar = 'z' + 1;
            for (int k = start; k <= end; k++) {
                if (map.containsKey(s.charAt(k)) && s.charAt(k) < minChar) {
                    minChar = s.charAt(k);
                    start = k + 1;
                }
            }
            res[i] = minChar;
            map.remove(minChar);
            if (s.charAt(end) == minChar) {
                end = findLastMinPos(map);
            }
        }
        return new String(res);
    }

    private int findLastMinPos(HashMap<Character, Integer> map) {
        int min = Integer.MAX_VALUE;
        for (int pos : map.values()) {
            if (pos < min) {
                min = pos;
            }
        }
        return min;
    }
}
