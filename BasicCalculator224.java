package StackRelated;

//Implement a basic calculator to evaluate a simple expression string.
//
//        The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .
//
//        Example 1:
//
//        Input: "1 + 1"
//        Output: 2
//        Example 2:
//
//        Input: " 2-1 + 2 "
//        Output: 3
//        Example 3:
//
//        Input: "(1+(4+5+2)-3)+(6+8)"
//        Output: 23
//        Note:
//        You may assume that the given expression is always valid.
//        Do not use the eval built-in library function.


import java.util.Stack;

public class BasicCalculator224 {
    public static void main(String[] args) {
        String s = "1+(1+109)-2+(16-76)";
        BasicCalculator224 bc224 = new BasicCalculator224();
        int res = bc224.calculate(s);
        System.out.println("Input string: " + s + " = ?");
        System.out.print(res);
    }
    private int calculate(String s) {
        Stack<Integer> tmpStack = new Stack<>();
        int sign = 1; // 1: "+", -1 : "-"
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                int num = s.charAt(i) - '0';
                while (i < s.length() - 1 && Character.isDigit(s.charAt(i + 1))) {
                    num = num * 10 + s.charAt(i + 1) - '0';
                    i++;
                }
                result += num * sign;
            } else if (s.charAt(i) == '+') {
                sign = 1;
            } else if (s.charAt(i) == '-') {
                sign = -1;
            } else if (s.charAt(i) == '(') {
                tmpStack.push(result);
                tmpStack.push(sign);
                result = 0;
                sign = 1;
            } else if (s.charAt(i) == ')') {
                //result = result * sign         + tmpStack.pop();
                result = result * tmpStack.pop() + tmpStack.pop();
            }
        }
        return result;
    }
}
