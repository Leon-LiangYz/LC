package BackTrackingRelated;

/*
Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.

Note:

All numbers will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:

Input: k = 3, n = 7
Output: [[1,2,4]]
Example 2:

Input: k = 3, n = 9
Output: [[1,2,6], [1,3,5], [2,3,4]]

 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CombinationSumIII216 {

    @Test
    public void test() {
        int k = 1;
        int n = 9;
        List<List<Integer>> res = combinationSum3(k, n);
        System.out.println(res);
    }

//    time : O(2^n)
//    space : O(n);

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        if (k <= 0) return res;
        dfs(k, n, 1, res, new ArrayList<>());
        return res;
    }

    private void dfs(int k, int n, int start, List<List<Integer>> res, List<Integer> temp){
        if (n < 0) return;
        if (temp.size() == k) {
            if (n == 0) {
                res.add(new ArrayList<>(temp));
            }
            return;
        }

        for (int i = start; i <= 9; i++) {
            if (i <= n) {
                temp.add(i);
                dfs(k, n - i, i+ 1, res, temp);
                temp.remove(temp.size() - 1);
            } else {
                break;
            }
        }
    }
}
