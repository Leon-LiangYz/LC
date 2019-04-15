package StackRelated;


import java.util.HashSet;
import java.util.Stack;

public class EvaluateReversePolishNotation150 {
    public static void main(String[] args) {
        String[] tokens = new String[]{"0", "3", "/", "55", "+", "7", "+"};
        for (String s : tokens) {
            System.out.print(s + " ");
        }
        System.out.println();
        EvaluateReversePolishNotation150 erpn150 = new EvaluateReversePolishNotation150();
        int result = erpn150.evalRPN(tokens);
        System.out.print(result);
        System.out.println();
        System.out.println();
        int[] test = new int[]{3,5,5,7,1,2,6,8,6,9};
        System.out.print("Original array: ");
        for (int i = 0; i < test.length; i++) {
            System.out.print(test[i] + " ");
        }
        System.out.println();
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < test.length; i++) {
            if (!set.contains(test[i])) {
                set.add(test[i]);
            } else {
                continue;
            }
        }
        System.out.print("After adding in hashset: ");
        for (Integer num : set) {
            System.out.print(num + " ");
        }
        char[] testChar = new char[]{'d','c', 'c', 'n'};
        HashSet<Character> charSet = new HashSet<>();
        for (int i = 0; i < testChar.length; i++) {
            if (!set.contains(testChar[i])) {
                charSet.add(testChar[i]);
            } else {
                continue;
            }
        }
        for (Character c : charSet) {
            System.out.print(c + " ");
        }
    }

    private int evalRPN(String[] tokens) {
        Stack<Integer> tmpStack = new Stack<>();
        for (String s : tokens) {
            if (s.equals("+")) {
                tmpStack.push(tmpStack.pop() + tmpStack.pop());
            } else if (s.equals("-")) {
                int secNum = tmpStack.pop();
                int firNum = tmpStack.pop();
                tmpStack.push(firNum - secNum);
            } else if (s.equals("*")) {
                tmpStack.push(tmpStack.pop() * tmpStack.pop());
            } else if (s.equals("/")) {
                int secNum = tmpStack.pop();
                int firNum = tmpStack.pop();
                tmpStack.push(firNum / secNum);
            } else {
                tmpStack.push(Integer.valueOf(s));
            }
        }
        return tmpStack.pop();
    }
}
