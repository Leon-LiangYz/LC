package DFSRelated;

import java.util.ArrayList;
import java.util.List;


//Time: 4^n, exponential
//Space: system space O(n), n is length of num
public class ExpressionAddOperators282 {
    public static void main(String[] args) {
        ExpressionAddOperators282 eao282 = new ExpressionAddOperators282();
        String s = "123";
        long curVal = Long.parseLong(s.substring(0, 2));
        System.out.println(curVal);
        int target = 6;
        List<String> res = eao282.addOperators(s, target);
        System.out.println(res);
    }
    public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<>();
        if (num == null || num.length() == 0) {
            return res;
        }
        helper(num, target, res, new StringBuilder(), 0, 0, 0);
        return res;
    }
    private void helper(String num, int target, List<String> res, StringBuilder sb,
                        int startPos, long prevResult, long preVal) {
        if (startPos == num.length()) {
            if (prevResult == target) {
                res.add(sb.toString());
                return;
            }
        }
        for (int i = startPos; i < num.length(); i++) {
            if (num.charAt(startPos) == '0' && i != startPos) break;
            long curVal = Long.parseLong(num.substring(startPos, i + 1));
            int curLevelLen = sb.length();
            if (startPos == 0) {
                helper(num, target, res, sb.append(curVal), i + 1, curVal, curVal);
                sb.setLength(curLevelLen);
            } else {
                helper(num, target, res, sb.append("+").append(curVal), i + 1, prevResult + curVal, curVal);
                sb.setLength(curLevelLen);
                helper(num, target, res, sb.append("-").append(curVal), i + 1, prevResult - curVal, -curVal);
                sb.setLength(curLevelLen);
                //when doing multiplication, we force to give up the priority "+" of previous level
                //for example: in previous level, it was 2 + 3.... = 5
                //in current level, because it's 2 + 3 * 6, we have to use (5 - 3) to get 2
                helper(num, target, res, sb.append("*").append(curVal),
                        i + 1, prevResult - preVal + preVal * curVal, preVal * curVal);
                //helper(num, target, res, sb.append("*").append(curVal), i + 1, prev  * curVal, curVal);
                sb.setLength(curLevelLen);
            }
        }

    }
}
