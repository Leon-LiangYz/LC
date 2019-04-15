package StringRelated;

import java.util.HashMap;

public class MinimumWindowSubstring76 {
    public static void main(String[] args) {
        String S = "EFGHJOBCJJCBBIAABC";
        String T = "ABC";
        MinimumWindowSubstring76 mws76 = new MinimumWindowSubstring76();
        String res = mws76.minWindow(S, T);
        System.out.print(res);
    }
    private String minWindow(String s, String t) {
        if (s == null || s.length() == 0 || t == null || t.length() == 0) return "";
        HashMap<Character, Integer> memo = new HashMap<>();
        for (Character c : t.toCharArray()) {
            if (!memo.containsKey(c)) {
                memo.put(c, 1);
            } else {
                memo.put(c, memo.get(c) + 1);
            }
        }
        int counter = t.length();
        int minResult = Integer.MAX_VALUE;
        int startInd = 0;
        for (int i = 0, j = 0; i < s.length(); i++) {
            if (!memo.containsKey(s.charAt(i)) && !memo.containsKey(s.charAt(j))) {
                j++;
                continue;
            }
            if (memo.containsKey(s.charAt(i))) {
                if (memo.get(s.charAt(i)) > 0) {
                    counter--;
                }
                memo.put(s.charAt(i), memo.get(s.charAt(i)) - 1);
            }
            //memo.put(s.charAt(i), memo.get(s.charAt(i)) - 1);
            while (counter == 0) {
                if (i - j + 1 < minResult) {
                    minResult = i - j + 1;
                    startInd = j;
                }
                if (memo.containsKey(s.charAt(j))) {
                    memo.put(s.charAt(j), memo.get(s.charAt(j)) + 1);
                    if (memo.get(s.charAt(j)) > 0) {
                        counter++;
                    }
                }
                j++;
            }
        }
        return (minResult == Integer.MAX_VALUE) ? "" : s.substring(startInd, startInd + minResult);

    }
}
