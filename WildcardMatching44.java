package DFSRelated;
/*
Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*'.

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).
The matching should cover the entire input string (not partial).

Note:

s could be empty and contains only lowercase letters a-z.
p could be empty and contains only lowercase letters a-z, and characters like ? or *.
Example 1:

Input:
s = "aa"
p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
Example 2:

Input:
s = "aa"
p = "*"
Output: true
Explanation: '*' matches any sequence.
Example 3:

Input:
s = "cb"
p = "?a"
Output: false
Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
Example 4:

Input:
s = "adceb"
p = "*a*b"
Output: true
Explanation: The first '*' matches the empty sequence, while the second '*' matches the substring "dce".
Example 5:

Input:
s = "acdcb"
p = "a*c?b"
Output: false
 */

import org.junit.Test;

public class WildcardMatching44 {

    @Test
    public void test() {
        String s = "aa";
        String p = "*";
        boolean res = isMatch(s, p);
        System.out.println(res);
    }

//    public boolean isMatch(String s, String p) {
//        Boolean[][] cache = new Boolean[s.length() + 1][p.length() + 1];
//        return helper(s,p, cache,0,0);
//
//    }
//
//
//    boolean helper(String s, String p, Boolean[][] cache,int indS, int indP) {
//        if (cache[indS][indP] != null) return cache[indS][indP];
//        if(indP == p.length()) return indS == s.length();
//
//        if(p.charAt(indP) == '*') {
//            while(indP < p.length() && p.charAt(indP) == '*') {
//                indP++;   // Move the index at p to a non-start char.
//            }
//            while(indS < s.length()) {
//                if(helper(s, p, cache,indS, indP)) {
//                    cache[indS][indP] = true;
//                    return true; // Find one match, return true.
//                }
//                indS++; // Try the next one.
//            }
//            cache[indS][indP] = helper(s, p, cache,indS, indP);
//            return cache[indS][indP];
//        }else if(indS < s.length() && (p.charAt(indP) == '?' || s.charAt(indS) == p.charAt(indP))){
//            cache[indS][indP] = helper(s, p, cache,indS + 1, indP + 1);
//            return cache[indS][indP];
//        }else{
//            return false;
//        }
//    }


    //dfs
    //Time: O(m*n * (m + n)) ??
    //Space: O(m*n)
    public boolean isMatch(String s, String p) {
        if (s == null || s.length() == 0) return false;
        if (p == null || p.length() == 0) {
            if (s == null || s.length() == 0) {
                return true;
            } else {
                return false;
            }
        }

        Boolean[][] cache = new Boolean[s.length() + 1][p.length() + 1];

        return isMatchHelper(s, p, cache, 0, 0);
    }
    private boolean isMatchHelper(String s, String p, Boolean[][] cache, int indS, int indP) {
        if (cache[indS][indP] != null) {
            return cache[indS][indP];
        }

        if (indP == p.length()) {
            return indS == s.length();
        } else if (p.charAt(indP) != '*') {
            if (indS < s.length() && (s.charAt(indS) == p.charAt(indP) || p.charAt(indP) == '?')) {
                cache[indS][indP] = isMatchHelper(s, p, cache, indS + 1, indP + 1);
            } else {
                cache[indS][indP] = false;
                return false;
            }

        } else { // p.charAt(indP) is '*'
            while (indP < p.length() && p.charAt(indP) == '*') {
                indP++;
            }

            while (indS < s.length()) {
                 if(isMatchHelper(s, p, cache, indS, indP)){
                     cache[indS][indP] = true;
                     return true;
                 }
                 indS++;
            }
            cache[indS][indP] = isMatchHelper(s, p, cache, indS, indP);
        }
        return cache[indS][indP];
    }



    @Test
    public void test2() {
        String s = "acdeb";
        String p = "*a*b";
        boolean res = isMatch2(s, p);
        System.out.println(res);
    }

    public boolean isMatch2(String s, String p) {
        int indS = 0, indP = 0;
        int starPos = -1;
        int matchingStar = 0; // the position of s matches '*' in p

        while (indS < s.length()) {
            if (indP < p.length() && (s.charAt(indS) == p.charAt(indP) || p.charAt(indP) == '?')) {
                indP++;
                indS++;
            } else if (indP < p.length() && p.charAt(indP) == '*') {
                starPos = indP;
                matchingStar = indS;
                indP++;
            } else if (starPos != -1) {
                indP = starPos + 1;
                matchingStar++;
                indS = matchingStar;
            } else {
                return false;
            }
        }
        while (indP < p.length() && p.charAt(indP) == '*') {
            indP++;
        }
        return indP == p.length();
    }
}
