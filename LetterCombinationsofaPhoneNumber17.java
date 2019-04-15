package BackTrackingRelated;

import java.util.*;

public class LetterCombinationsofaPhoneNumber17 {
    public static void main(String[] args) {
        String digits = "23";
        LetterCombinationsofaPhoneNumber17 lcpn17 = new LetterCombinationsofaPhoneNumber17();
        ArrayList<String> res = lcpn17.letterCombinations(digits);
        System.out.print(res);
        System.out.println();
        String s = "";
        System.out.println(s.length());

        String[] mapping = new String[]{"0","1","abc"};
        String letters = mapping[digits.charAt(0) - '0'];
        System.out.println(letters);

        ArrayList<String> res2 = lcpn17.letterCombinations2(digits);
        System.out.println(res2);

        List<String> res3 = lcpn17.letterCombinations3(digits);
        System.out.println(res3);

        String tmp = "aabbcc";
        String tmp2 = "123";
        char c1 = '9';
        char[] charArray = {'9', '0'};
        System.out.println(tmp + tmp2);
        System.out.println(tmp + c1);
        System.out.println(String.valueOf(c1));
        System.out.println(Arrays.toString(charArray));
        System.out.println(String.valueOf(charArray));

    }
    private ArrayList<String> letterCombinations(String digits) {
        ArrayList<String> result = new ArrayList<String>();

        if (digits == null || digits.equals("")) {
            return result;
        }

        HashMap<Character, char[]> map = new HashMap<Character, char[]>();
        map.put('0', new char[] {});
        map.put('1', new char[] {});
        map.put('2', new char[] { 'a', 'b', 'c' });
        map.put('3', new char[] { 'd', 'e', 'f' });
        map.put('4', new char[] { 'g', 'h', 'i' });
        map.put('5', new char[] { 'j', 'k', 'l' });
        map.put('6', new char[] { 'm', 'n', 'o' });
        map.put('7', new char[] { 'p', 'q', 'r', 's' });
        map.put('8', new char[] { 't', 'u', 'v'});
        map.put('9', new char[] { 'w', 'x', 'y', 'z' });

        StringBuilder sb = new StringBuilder();
        letterCombinationsHelper(map, digits, sb, result);

        return result;
    }
    private void letterCombinationsHelper(HashMap<Character, char[]> map, String digits,
                                          StringBuilder sb, ArrayList<String> result) {
        if (sb.length() == digits.length()) {
            result.add(sb.toString());
            return;
        }
        for (Character c : map.get(digits.charAt(sb.length()))) {
            sb.append(c);
            letterCombinationsHelper(map, digits, sb, result);
            sb.deleteCharAt(sb.length() - 1);
        }

    }
    private ArrayList<String> letterCombinations2(String digits) {
        ArrayList<String> res = new ArrayList<String>();
        if (digits == null || digits.length() == 0) return res;
        String[] mapping = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        letterCombinationsHelper2(res, digits, new StringBuilder(), 0, mapping);
        return res;

    }
    private void letterCombinationsHelper2(ArrayList<String> res, String digits,
                                          StringBuilder sb, int index, String[] mapping) {
        if (index == digits.length()) {
            res.add(sb.toString());
            return;
        }
        String letters = mapping[digits.charAt(index) - '0'];
        for (int i = 0; i < letters.length(); i++) {
            sb.append(letters.charAt(i));
            letterCombinationsHelper2(res, digits, sb, index + 1, mapping);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
    public List<String> letterCombinations3(String digits) {
        LinkedList<String> res = new LinkedList<>();
        if (digits == null || digits.length() == 0) return res;
        String[] mapping = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        res.add("");

        for (int i = 0; i < digits.length(); i++) {
            int num = digits.charAt(i) - '0';
            while (res.peek().length() == i) {
                String tmp = res.remove();
                for (Character c : mapping[num].toCharArray()) {
                    res.add(tmp + c);
                }
            }
        }
        return res;
    }
}
