package StringRelated;

import java.util.ArrayList;
import java.util.List;

//You are playing the following Flip Game with your friend: Given a string that contains only these two characters: + and -, you and your friend take turns to flip two consecutive "++" into "--". The game ends when a person can no longer make a move and therefore the other person will be the winner.
//
//        Write a function to compute all possible states of the string after one valid move.
//
//        Example:
//
//        Input: s = "++++"
//        Output:
//        [
//        "--++",
//        "+--+",
//        "++--"
//        ]


public class FlipGame293 {
    public static void main(String[] args) {
        String s = "++-";
        System.out.println("Original String" + s);
        FlipGame293 fg293 = new FlipGame293();
        List<String> result = fg293.generatePossibleNextMoves(s);
        for (String tmpStr : result) {
            System.out.println(tmpStr);
        }
        List<String> result2 = fg293.generatePossibleNextMoves(s);
        System.out.print(result2);
    }
    //Using substring, time: O(n)
    private List<String> generatePossibleNextMoves(String s) {
        List<String> res = new ArrayList<>();
        if (s == null || s.length() == 0) return res;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i - 1) == '+' && s.charAt(i) == '+') {
                res.add(s.substring(0, i - 1) + "--" + s.substring(i + 1, s.length()));
            }
        }
        return res;
    }
    //Not using substring, time: O(n)
    private List<String> generatePossibleNextMoves2(String s) {
        List<String> res = new ArrayList<>();
        char[] tmpChar = s.toCharArray();
        if (s == null || s.length() == 0) {
            return res;
        }
        for (int i = 1; i < s.length(); i++) {
            if (tmpChar[i - 1] == '+' && tmpChar[i] == '+') {
                tmpChar[i - 1] = '-';
                tmpChar[i] = '-';
                res.add(new String(tmpChar));
                tmpChar[i - 1] = '+';
                tmpChar[i] = '+';
            }
        }
        return res;
    }
}
