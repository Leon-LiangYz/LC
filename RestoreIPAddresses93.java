package DFSRelated;

import java.util.ArrayList;
import java.util.List;

public class RestoreIPAddresses93 {
    public static void main(String[] args) {
        RestoreIPAddresses93 ripa93 = new RestoreIPAddresses93();
        String s = "1203";
        List<String> result = ripa93.restoreIpAddresses(s);
        for (String str : result) {
            System.out.println(str);
        }
        System.out.println(result);
    }
    //Time: O(1), 因为字符串最长只有12位
    //Space: system space O(n)
    public List<String> restoreIpAddresses(String s) {
        //0 to 255, 2^8
        //32 bit, 4 bytes
        List<String> res = new ArrayList<>();
        if (s == null || s.length() == 0 || s.length() > 12 || s.length() < 4) {
            throw new IllegalArgumentException("Invalid Input");
            //return res;
        }
        dfs(res, s, new StringBuilder(), 0, 0);
        //helper(res, s, new StringBuilder(), 0, 0);
        return res;
    }
    private void dfs(List<String> res, String s, StringBuilder path, int startPos, int sectionCount) {

        if (sectionCount == 4) {
            if(startPos == s.length()) {
                res.add(path.toString());
                return;
            }
        }

        for (int i = startPos; i < s.length(); i++) {
            String tmpStr = s.substring(startPos, i + 1);
            if (tmpStr .length() > 3 || tmpStr.length() > 1 && tmpStr.charAt(0) == '0'
                    || Integer.valueOf(tmpStr) > 255) {
                return;
            }
            int pathCurLen = path.length();
            path.append(tmpStr);
            if (sectionCount < 3) {
                path.append(".");
            }
            dfs(res, s, path, i + 1, sectionCount + 1);
            path.setLength(pathCurLen);
        }
    }
    private void helper(List<String> res, String s, StringBuilder sb, int pos, int count) {
        if(pos == s.length() && count == 3) {
            res.add(sb.toString());
            return;
        }

        for(int i = pos; i < s.length(); i++) {
            String t = s.substring(pos, i+1);
            if(t.length() > 3 || t.length() > 1 && t.charAt(0) == '0' || Integer.valueOf(t) > 255) {
                break;
            }
            int len = sb.length();

            sb.append(t);
            if(i+1 != s.length()) {
                sb.append(".");
                helper(res, s, sb, i+1, count+1);
            } else {
                helper(res, s, sb, i+1, count);
            }
            sb.setLength(len);
        }
    }

}
