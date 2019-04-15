package StringRelated;

//Given a string, find the length of the longest substring T that contains at most k distinct characters.
//
//        Example 1:
//
//        Input: s = "eceba", k = 2
//        Output: 3
//        Explanation: T is "ece" which its length is 3.
//        Example 2:
//
//        Input: s = "aa", k = 1
//        Output: 2
//        Explanation: T is "aa" which its length is 2.

import java.util.HashMap;
import java.util.HashSet;

public class LongestSubstringwithAtMostKDistinctCharacters340 {
    public static void main(String[] args) {
        String s = "eceeeeba";
        System.out.println("Original String: " + s);
        int k = 2;
        LongestSubstringwithAtMostKDistinctCharacters340 lswamkdc340 = new LongestSubstringwithAtMostKDistinctCharacters340();
        //int ans = lswamkdc340.lengthOfLongestSubstringKDistinct(s, k);
        //System.out.print(ans);
        int ans2 = lswamkdc340.lengthOfLongestSubstringKDistinct2(s, k);
        System.out.print(ans2);
    }

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() == 0 || k < 1) return 0;
        HashMap<Character, Integer> map = new HashMap<>();
        int res = 0;
        int slow = 0, fast = 0;
        while (fast < s.length()) {
            if (map.containsKey(s.charAt(fast))) {
                map.put(s.charAt(fast), map.get(s.charAt(fast)) + 1);
            } else {
                map.put(s.charAt(fast), 1);
            }


            while (map.size() > k) {
                map.put(s.charAt(slow), map.get(s.charAt(slow)) - 1);
                if (map.get(s.charAt(slow)) == 0) {
                    map.remove(s.charAt(slow));
                }
                slow++;
            }
            res = Math.max(res, fast - slow + 1);
            fast++;
        }
        return res;
    }
    public int lengthOfLongestSubstringKDistinct2(String s, int k) {
        //Time: O(n), space: O(1)
        if (s == null || s.length() == 0 || k < 1) return 0;
        int res = 0, num = 0, j = 0;
        int[] count = new int[256];
        for (int i = 0; i < s.length(); i++) {
            if (count[s.charAt(i)]++ == 0) num++;
            if (num > k) {
                while (--count[s.charAt(j++)] > 0);
                num--;
            }
            res = Math.max(res, i - j + 1);
        }
        return res;
    }


}
