package BackTrackingRelated;

import java.util.ArrayList;
import java.util.List;

public class FactorCombinations254 {
    public static void main(String[] args) {
        FactorCombinations254 fc254 = new FactorCombinations254();
        int n = 36;
        List<List<Integer>> res = fc254.getFactors(n);
        System.out.println(res);
        for (List<Integer> tmpList : res) {
            System.out.println(tmpList);
        }
    }
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> result = new ArrayList<>();
        getFactorsHelper(n, result, new ArrayList<>(), 2);
        return  result;
    }
    private void getFactorsHelper(int n, List<List<Integer>> result, List<Integer> temp, int start) {
        if (n == 1) {
            if (temp.size() > 1) {
                result.add(new ArrayList<>(temp));
                return;
            }

        }
        for (int i = start; i <= n; i++) {
            if (n % i == 0) {
                temp.add(i);
                getFactorsHelper(n / i, result, temp, i);
                temp.remove(temp.size() - 1);
            }
        }
    }

}
