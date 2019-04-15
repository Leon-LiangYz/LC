package DFSRelated;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

//Time: O(2^n)
//Space: O(n)
public class RemoveInvalidParentheses301 {
    public static void main(String[] args) {
        String s = "";
        RemoveInvalidParentheses301 rip301 = new RemoveInvalidParentheses301();
        List<String> result = rip301.removeInvalidParentheses(s);
        System.out.println(result);
    }
    public List<String> removeInvalidParentheses(String s) {
        HashSet<String> set = new HashSet<>();
        if (s == null || s.length() == 0) {
            List<String> res = new ArrayList<>();
            res.add("");
            return res;
        }
        //Min "(" & min ")" need to be removed
        int rmL = 0, rmR = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                rmL++;
            } else if (c == ')') {
                if (rmL != 0) rmL--;
                else rmR++;
            } else {
                continue;
            }
        }
        dfsHelper(s, 0, rmL, rmR, 0, set, new StringBuilder());
        return new ArrayList<String>(set);
    }
    private void dfsHelper(String s, int index, int rmL, int rmR, int open, HashSet<String> set, StringBuilder sb) {
        if (index == s.length() && rmL == 0 && rmR == 0 && open == 0) {
            set.add(sb.toString());
            return;
        }
        if (index >= s.length() || rmL < 0 || rmR < 0 || open < 0) {
            return;
        }
        char tmpChar = s.charAt(index);
        if (tmpChar == '(') {
            //remove '('
            dfsHelper(s, index + 1, rmL - 1, rmR, open, set, sb);
            //keep '('
            dfsHelper(s, index + 1, rmL , rmR, open + 1, set, sb.append(tmpChar));
        } else if (tmpChar == ')') {
            //remove ')'
            dfsHelper(s, index + 1, rmL, rmR - 1, open, set, sb);
            //keep ')'
            dfsHelper(s, index + 1, rmL, rmR, open - 1, set, sb.append(tmpChar));
        } else {
            dfsHelper(s, index + 1, rmL, rmR, open, set, sb.append(tmpChar));
        }
        sb.deleteCharAt(sb.length() - 1);
    }
}
