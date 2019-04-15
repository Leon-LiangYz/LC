package StringRelated;

public class StringtoIntegerATOI8 {
    public static void main(String[] args) {
        String s = "   abes";
        StringtoIntegerATOI8 stia8 = new StringtoIntegerATOI8();
        int res = stia8.myAtoi(s);
        System.out.print(res);
    }
    private int myAtoi(String s) {
        s = s.trim();
        //System.out.println(s);
        if (s == null || s.length() == 0) return 0;
        int sign = 1, idx = 0;
        long result = 0;
        char firChar = s.charAt(0);
        if (firChar == '+') {
            idx++;
        } else if (firChar == '-') {
            sign = -1;
            idx++;
        }
        while (idx < s.length()) {
            if (!Character.isDigit(s.charAt(idx))) {
                //System.out.println(sign);
                return (int)result * sign;
            }
            result = result * 10 + s.charAt(idx) - '0';
            if (result > Integer.MAX_VALUE && sign == 1) return Integer.MAX_VALUE;
            if (result > Integer.MAX_VALUE && sign == -1) return Integer.MIN_VALUE;
            idx++;
        }
        return (int)result * sign;
    }
}
