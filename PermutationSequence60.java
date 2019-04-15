package BackTrackingRelated;

import java.util.ArrayList;
import java.util.List;

public class PermutationSequence60 {
    public static void main(String[] args) {
        PermutationSequence60 ps60 = new PermutationSequence60();
        int n = 3, k = 3;
        String res = ps60.getPermutation(n, k);
        System.out.println(res);
    }
    public String getPermutation(int n, int k) {
        if (n < 1 || n > 9) return "";
        List<Integer> tmpList = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            tmpList.add(i);
        }
        int[] factorialCombination = new int[n];
        factorialCombination[0] = 1;

        for (int i = 1; i < n; i++) {
            factorialCombination[i] = i * factorialCombination[i - 1];
        }

        k--;
        StringBuilder sb = new StringBuilder();
        for (int i = n - 1; i >= 0; i--) {
            int index = k / factorialCombination[i];
            sb.append(tmpList.get(index));
            tmpList.remove(index);
            k %= factorialCombination[i];
        }
        return sb.toString();
    }
}
