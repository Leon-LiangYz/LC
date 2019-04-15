package BitRelated;
import org.junit.Test;

public class FindtheDifference389 {
    String s;
    String t;
    public FindtheDifference389() {
        s = "a";
        t = "ab";
    }

    @Test
    public void test1() {
        char c = findTheDifference(s, t);
        System.out.println(c);
    }
    public char findTheDifference(String s, String t) {
        if (t.length() == 1) return t.charAt(0);
        char c = t.charAt(t.length() - 1);
        for (int i = 0; i < s.length(); i++) {
            c ^= s.charAt(i);
            c ^= t.charAt(i);
        }
        return c;
    }
}
