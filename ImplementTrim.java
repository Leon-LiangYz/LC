package StringRelated;

/*
Remove leading/trailing/ and duplicate space with one remaining
 */

public class ImplementTrim {
    public static void main(String[] args) {
        String s = "you will  ";
        ImplementTrim it = new ImplementTrim();
        String res1 = it.removeTrailingAndEndingZerosAndKeepTheSpaceAfterWord(s);
        System.out.println(res1 + "!");
        String res2 = it.removeTrailingAndEndingZerosAndKeepTheSpaceBeforeWord(s);
        System.out.println(res2 + "!");
    }
    //S1: always keep the space after word
    private String removeTrailingAndEndingZerosAndKeepTheSpaceAfterWord (String s) {
        int slow = 0, fast = 0;
        char[] chars = s.toCharArray();
        for (fast = 0; fast < s.length(); fast++) {
            if (chars[fast] == ' ' && (fast == 0 || chars[fast - 1] == ' ' )) {
                continue;
            } else {
                chars[slow++] = chars[fast];
            }
        }
        if (slow == 0) return "";
        return chars[slow - 1] == ' ' ? new String(chars, 0, slow - 1) : new String(chars, 0, slow);
    }


    //S2: always keep the space before word
    private String removeTrailingAndEndingZerosAndKeepTheSpaceBeforeWord(String s) {
        int slow = 0;
        int fast = 0;
        char[] chars = s.toCharArray();
        for (fast = 0; fast < chars.length; fast++) {
            if (chars[fast] == ' ' && (fast == chars.length - 1 || chars[fast + 1] == ' ')) {
                continue;
            } else {
                chars[slow++] = chars[fast];
            }
        }
        if (slow == 0) return "";
        //return chars[0] == ' ' ? new String(chars, 1, slow - 1) : new String(chars, 0, slow);
        return new String(chars, 1, slow - 1);

    }
}
