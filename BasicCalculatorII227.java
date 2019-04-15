package StackRelated;

/*
Implement a basic calculator to evaluate a simple expression string.

The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.

Example 1:

Input: "3+2*2"
Output: 7
Example 2:

Input: " 3/2 "
Output: 1
Example 3:

Input: " 3+5 / 2 "
Output: 5
Note:

You may assume that the given expression is always valid.
Do not use the eval built-in library function.
 */

import org.junit.Test;

import java.util.Stack;


public class BasicCalculatorII227 {
    @Test
    public void test() {
        String s = "1*2";
        int res = calculate(s);
        System.out.println(res);

    }

    //S1: using stack
    //Time: O(n)
    //Space: O(n)
    public int calculate(String s) {
        if (s == null || s.length() == 0) return 0;
        s = s.trim().replaceAll(" +", "");
        Stack<Integer> stack = new Stack<>();
        int res = 0;
        char sign = '+';
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                num = s.charAt(i) - '0';
                while (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                    num = num * 10 + s.charAt(i + 1) - '0';
                    i++;
                }
            }
            if (!Character.isDigit(s.charAt(i)) && s.charAt(i) != ' ' || i == s.length() - 1) {
                if (sign == '+') stack.push(num);
                if (sign == '-') stack.push(-num);
                if (sign == '*') stack.push(stack.pop() * num);
                if (sign == '/') stack.push(stack.pop() / num);
                sign = s.charAt(i);
                num = 0;
            }
        }
        for (int i : stack) {
            res += i;
        }
        return res;
    }



    @Test
    public void test2() {
        String s = "1*2";
        int res = calculate2(s);
        System.out.println(res);

    }

    //S2: not using stack
    //Time: O(n)
    //Space: O(1)
    public int calculate2(String s) {
        if (s == null || s.length() == 0) return 0;
        int res = 0;
        int preVal = 0;
        char sign = '+';
        int start = 0;
        while (start < s.length()) {
            int curVal = 0;

            while (start < s.length() && Character.isDigit(s.charAt(start))) {
                curVal = curVal * 10 + s.charAt(start) - '0';
                start++;
            }

            if (sign == '+') {
                res += preVal;
                preVal = curVal;
            } else if (sign == '-') {
                res += preVal;
                preVal = -curVal;
            } else if (sign == '*') {
                preVal *= curVal;
            } else if (sign == '/') {
                preVal /= curVal;
            }

            if (start < s.length()) {
                sign = s.charAt(start);
                start++;
            }
        }
        res += preVal;
        return res;
    }
}
