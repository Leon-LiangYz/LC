package StackRelated;

//https://leetcode.com/problems/decode-string/description/
//Given an encoded string, return it's decoded string.
//
//        The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.
//
//        You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.
//
//        Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].
//
//        Examples:
//
//        s = "3[a]2[bc]", return "aaabcbc".
//        s = "3[a2[c]]", return "accaccacc".
//        s = "2[abc]3[cd]ef", return "abcabccdcdcdef".


import java.util.Stack;

public class DecodeString394 {
    public static void main(String[] args) {
        String s = "3[a2[c]]";
        DecodeString394 ds394 = new DecodeString394();
        String res = ds394.decodeString(s);
        System.out.println("Original String : " + s);
        System.out.print("Decoded String: " + res);

    }
    private String decodeString(String s) {
        if (s == null || s.length() == 0) return "";
        Stack<Integer> numStack = new Stack<>();
        Stack<String> strStack = new Stack<>();
        String res = "";
        int index = 0;
        while (index < s.length()) {
            if (Character.isDigit(s.charAt(index))) {
                int num = 0;
                while (Character.isDigit(s.charAt(index))) {
                    num = num * 10 + s.charAt(index) - '0';
                    index++;
                }
                numStack.push(num);
            } else if (s.charAt(index) == '[') {
                strStack.push(res);
                res = "";
                index++;
            } else if (s.charAt(index) == ']') {
                StringBuilder sb = new StringBuilder(strStack.pop());
                int repeat = numStack.pop();
                for (int i = 0; i < repeat; i++) {
                    sb.append(res);
                }
                res = sb.toString();
                index++;
            } else {
                res += s.charAt(index++);
            }
        }
        return res;
    }
}
