package BackTrackingRelated;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//https://leetcode.com/problems/combination-sum-ii/description/

public class CombinationSumII40 {
    public static void main(String[] args) {
        int[] candidates = new int[]{1,1,2,3};
        int target = 3;
        CombinationSumII40 csII40 = new CombinationSumII40();
        List<List<Integer>> result = csII40.combinationSum2(candidates, target);
        System.out.println(result);
    }
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (candidates == null || candidates.length == 0) return result;
        Arrays.sort(candidates);
        List<Integer> temp = new ArrayList<>();
        combinationSum2Helper(candidates, target, result, temp, 0);
        return result;
    }
    private void combinationSum2Helper(int[] candidates, int target, List<List<Integer>> result, List<Integer> temp,
                                       int start) {
        if (target < 0) return;
        if (target == 0) {
            result.add(new ArrayList<Integer>(temp));
            return;
        }


        for (int i = start; i < candidates.length; i++) {
            if (i != start && candidates[i] == candidates[i - 1]) {
                continue;
            }
            if (target < candidates[i]) {
                break;
            }
            temp.add(candidates[i]);
            System.out.println(i);
            combinationSum2Helper(candidates, target - candidates[i], result, temp, i + 1);
            temp.remove(temp.size() - 1);
        }
    }

}

