package StringRelated;

import java.util.Stack;
//Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
//
//        Example 1:
//
//        Input: "(()"
//        Output: 2
//        Explanation: The longest valid parentheses substring is "()"
//        Example 2:
//
//        Input: ")()())"
//        Output: 4
//        Explanation: The longest valid parentheses substring is "()()"


public class LongestValidParentheses32 {
    public static void main(String[] args) {
        String s = "(())";
        LongestValidParentheses32 lvp32 = new LongestValidParentheses32();
        int res = lvp32.longestValidParentheses(s);
        System.out.print(res);
    }
    private int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) return 0;
        Stack<Integer> stack = new Stack<>();
        int result = 0, start = -1;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                if (stack.isEmpty()) {
                    start = i;
                } else {
                    stack.pop();
                    if (stack.isEmpty()) {
                        result = Math.max(result, i - start);
                    } else {
                        result = Math.max(result, i - stack.peek());
                    }
                }
            }
        }
        return result;
    }
}
