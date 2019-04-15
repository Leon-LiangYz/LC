package MathRelated;


//Given a positive integer num, write a function which returns True if num is a perfect square else False.
//
//        Note: Do not use any built-in library function such as sqrt.
//
//        Example 1:
//
//        Input: 16
//        Output: true
//        Example 2:
//
//        Input: 14
//        Output: false


public class ValidPerfectSquare367 {
    public static void main(String[] args) {
        int num = 16;
        ValidPerfectSquare367 vps367 = new ValidPerfectSquare367();
//        boolean result = vps367.isPerfectSquare(num);
//        System.out.print(result);
        boolean result2 = vps367.isPerfectSquare2(num);
        System.out.print(result2);

    }
    //Time: O(log(N))
    private boolean isPerfectSquare(int num) {
        if (num < 0) return false;
        if (num <= 1) return true;
        int low = 1, high = num;
        while (low < high) {
            long mid = low + (high - low) / 2;
            if (mid * mid == num) {
                return true;
            } else if (mid * mid > num) {
                high = (int) mid;
            } else {
                low = (int) mid + 1;
            }
        }
        return false;
    }
    //Time: O(sqrt(n))
    public boolean isPerfectSquare2(int num) {
        if (num < 0) return false;
        if (num <= 1) return true;
        for (int i = 1; i <= num / i; i++) {
            if (i * i == num) return true;
        }
        return false;
    }
}
