package DFSRelated;

import java.util.ArrayList;
import java.util.List;

public class GeneralizedAbbreviation320 {
    public static void main(String[] args) {
        String word = "wa";
        GeneralizedAbbreviation320 ga320 = new GeneralizedAbbreviation320();
        List<String> result = ga320.generateAbbreviations(word);
        System.out.println(result);
    }
    public List<String> generateAbbreviations(String word) {
        List<String> res = new ArrayList<>();
        if (word == null || word.length() == 0) return res;
        dfs(word, res, 0, new StringBuilder(), 0);
        return res;
    }
    public void dfs(String word, List<String> res, int index, StringBuilder sb, int count) {
        int sbCurLen = sb.length();
        if (index == word.length()) {
            if (count != 0) {
                sb.append(count);
            }
            res.add(sb.toString());
            //return;
        } else {
            //choose to abbreviate s.charAt(index)
            dfs(word, res, index + 1, sb, count + 1);

            //choose not to abbreviate s.charAt(index)
            if (count > 0) sb.append(count);
            sb.append(word.charAt(index));
            dfs(word, res, index + 1, sb, 0);
        }
        sb.setLength(sbCurLen);
    }

}
