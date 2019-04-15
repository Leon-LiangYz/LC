package StringRelated;

/*
Given a pattern and a string str, find if str follows the same pattern.

Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.

Example 1:

Input: pattern = "abba", str = "dog cat cat dog"
Output: true
Example 2:

Input:pattern = "abba", str = "dog cat cat fish"
Output: false
Example 3:

Input: pattern = "aaaa", str = "dog cat cat dog"
Output: false
Example 4:

Input: pattern = "abba", str = "dog dog dog dog"
Output: false

 */

import org.junit.Test;

import java.util.HashMap;

public class WordPattern290 {

    @Test
    public void test() {
        String patter = "abba";
        String str = "dog dog dog dog";

        boolean res = wordPattern(patter, str);
        System.out.println(res);
    }
    //Time: O(n * max(word length of str))
    //space: O(n), n is the length of str array
    public boolean wordPattern(String pattern, String str) {
        if (str == null || str.length() == 0) return false;
        HashMap<Character, String> map = new HashMap<>();
        String[] strArr = str.split(" ");
        int patInd = 0, strArrInd = 0;
        int patLen = pattern.length();
        int strArrLen = strArr.length;

        while (patInd < patLen && strArrInd < strArrLen) {
            if (!map.containsKey(pattern.charAt(patInd))) {
                if (map.containsValue(strArr[strArrInd])) {
                    return false;
                }
                map.put(pattern.charAt(patInd), strArr[strArrInd]);

            } else {
                if (!strArr[strArrInd].equals(map.get(pattern.charAt(patInd)))) {
                    return false;
                }
            }
            patInd++;
            strArrInd++;
        }
        if (patInd == patLen && strArrInd == strArrLen) {
            return true;
        }
        return false;

    }
}
