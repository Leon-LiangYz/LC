package StringRelated;

//You are playing the following Flip Game with your friend: Given a string that contains only these two characters: + and -, you and your friend take turns to flip two consecutive "++" into "--". The game ends when a person can no longer make a move and therefore the other person will be the winner.
//
//        Write a function to determine if the starting player can guarantee a win.
//
//        Example:
//
//        Input: s = "++++"
//        Output: true
//        Explanation: The starting player can guarantee a win by flipping the middle "++" to become "+--+".
//        Follow up:
//        Derive your algorithm's runtime complexity.

import java.util.HashMap;
import java.util.List;


public class FlipGameII294 {
    public static void main(String[] args) {
        String s = "++-";
        System.out.println("Original String: " + s);
        FlipGameII294 fgII294 = new FlipGameII294();
        boolean result = fgII294.canWin(s);
        System.out.print(result);

    }
    //Time: Exponential, 加pruning之后，如果本身全是"+",那么造成了worst case 仍未exponential
    //Space: O(n)
    public boolean canWin(String s) {
        if (s == null || s.length() == 0) return false;
        HashMap<String, Boolean> memo = new HashMap<>();
        return canWinHelper(s, memo);

    }
    private boolean canWinHelper(String s, HashMap<String, Boolean> memo) {
        if (memo.containsKey(s)) return memo.get(s);
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == '+' && s.charAt(i + 1) == '+') {
                String opponent = s.substring(0, i) + "--" + s.substring(i + 2);
                if (!canWinHelper(opponent, memo)) {
                    memo.put(s, true);
                    return true;
                }
            }
        }
        memo.put(s, false);
        return false;
    }

}
