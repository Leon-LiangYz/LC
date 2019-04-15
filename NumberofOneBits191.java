package BitRelated;

public class NumberofOneBits191 {
    public static void main(String[] args) {
        int n = 5;
        NumberofOneBits191 nob191 = new NumberofOneBits191();
        int res = nob191.hammingWeight(n);
        System.out.println(res);
    }

    public int hammingWeight(int n) {
        int res = 0;
        while (n != 0) {
            n &= (n - 1); //This operation will turn the lowest 1 to 0
            res++;
        }
        return res;
    }
}
