package StringRelated;
/*
Find the length of the longest substring T of a given string (consists of lowercase letters only) such that every character in T appears no less than k times.

Example 1:

Input:
s = "aaabb", k = 3

Output:
3

The longest substring is "aaa", as 'a' is repeated 3 times.
Example 2:

Input:
s = "ababbc", k = 2

Output:
5

The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.

 */

import org.junit.Test;

import java.util.ArrayList;

import java.util.List;


public class LongestSubstringwithAtLeastKRepeatingCharacters395 {
//    public static void main(String[] args) {
//        String s = "aabccdd";
//        LongestSubstringwithAtLeastKRepeatingCharacters395 lswalkpc395 = new LongestSubstringwithAtLeastKRepeatingCharacters395();
//        int k = 2;
//        int res = lswalkpc395.longestSubstring(s, k);
//        System.out.println(res);
//        lswalkpc395.Test1();
//    }

    @Test
    public void Test1(){
        String s = "aabccdd";
        int k = 2;
        int res = longestSubstring(s, k);
        System.out.println(res);
    }


    public int longestSubstring(String s, int k) {

        if (s == null || s.length() < k || k == 0 ) return 0;
        //int max = 0;
        int[] count = new int[26];
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }
        List<Integer> pos = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (count[s.charAt(i) - 'a'] < k) pos.add(i);
        }
        if (pos.size() == 0) return s.length();//all char in s are at least repeating k times
        pos.add(0, -1);
        pos.add(s.length());
        for (int i = 1; i < pos.size(); i++) {
            int start = pos.get(i-1) + 1;
            int end = pos.get(i);
            int next = longestSubstring(s.substring(start, end), k);
            res = Math.max(res, next);
        }
        return res;

    }

    @Test
    public void Test2(){
        String s = "aacbb";
        int k = 2;
        int res = longestSubstring2(s, k);
        System.out.println(res);
    }

    //Time: O(n)
    //Space: O(1)
    public int longestSubstring2(String s, int k) {
        if (s == null || s.length() < k || k == 0 ) return 0;
        int res = 0;
        for (int maxChar = 1; maxChar <= 3; maxChar++) {
            res = Math.max(res, longestSubstring2Helper(s, k, maxChar));
        }
        return res;

    }
    private int longestSubstring2Helper(String s, int k, int maxChar) {
        int[] count = new int[26];
        int uniqChar = 0, charNoLessThanK = 0;

        int start = 0, end = 0;
        int res = 0;
        while (end < s.length()) {
            if (count[s.charAt(end) - 'a']++ == 0) uniqChar++;
            if (count[s.charAt(end++) - 'a'] == k) charNoLessThanK++;

            while (uniqChar > maxChar) {
                if (count[s.charAt(start) - 'a']-- == k) charNoLessThanK--;
                if (count[s.charAt(start++) - 'a'] == 0) uniqChar--;
            }

            if (uniqChar == maxChar && uniqChar == charNoLessThanK) {
                res = Math.max(res, end - start);
            }
        }

        return res;
    }
}
